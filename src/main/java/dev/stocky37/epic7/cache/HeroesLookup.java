package dev.stocky37.epic7.cache;

import dev.stocky37.epic7.EpicSevenDbApi;
import dev.stocky37.epic7.json.HeroTransform;
import dev.stocky37.epic7.json.Unwrapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.function.Function;

@Named("cache.heroes.lookup")
@ApplicationScoped
public class HeroesLookup implements Function<String, JsonArray> {

	@Inject
	@RestClient
	EpicSevenDbApi api;

	@Override
	public JsonArray apply(String s) {
		final JsonArrayBuilder builder = Json.createArrayBuilder();
		new Unwrapper()
				.andThen(arr -> arr.getValuesAs(JsonObject.class))
				.apply(api.getHeroes())
				.stream()
				.map(HeroTransform.getInstance())
				.forEach(builder::add);
		return builder.build();
	}
}
