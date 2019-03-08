package dev.stocky37.epic7.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import dev.stocky37.epic7.client.EpicSevenDbApi;
import dev.stocky37.epic7.json.HeroTransform;
import dev.stocky37.epic7.json.Unwrapper;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

@ApplicationScoped
public class HeroCacheLoader implements CacheLoader<String, JsonObject> {

	@Inject
	@RestClient
	EpicSevenDbApi api;

	@Nullable
	@Override
	public JsonObject load(@NonNull String id) {
		return new Unwrapper()
				.andThen(arr -> arr.getJsonObject(0))
				.andThen(HeroTransform.getInstance())
				.apply(api.getHero(id));
	}
}