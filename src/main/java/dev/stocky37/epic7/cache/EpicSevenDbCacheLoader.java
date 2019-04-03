package dev.stocky37.epic7.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import dev.stocky37.epic7.json.Unwrapper;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.json.JsonObject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.function.Function;

public abstract class EpicSevenDbCacheLoader implements CacheLoader<String, JsonObject> {

	private static final Function<JsonObject, JsonObject> NOOP = a -> a;

	private final Function<JsonObject, JsonObject> transformer;

	public EpicSevenDbCacheLoader() {
		this(NOOP);
	}

	protected EpicSevenDbCacheLoader(Function<JsonObject, JsonObject> transformer) {
		this.transformer = transformer;
	}

	@Nullable
	@Override
	public JsonObject load(@NonNull String key) throws Exception {
		try {
			return new Unwrapper()
				.andThen(arr -> arr.getJsonObject(0))
				.andThen(transformer)
				.apply(loadFromApi(key));
		} catch (WebApplicationException e) {
			if(e.getResponse().getStatus() == 418) {
				throw new NotFoundException(e.getResponse());
			} else {
				throw e;
			}
		}
	}

	protected abstract JsonObject loadFromApi(@NonNull String key) throws Exception;

}
