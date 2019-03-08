package dev.stocky37.epic7.service;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.function.Function;

@ApplicationScoped
public class HeroService {
	@Inject
	@Named("cache.lists")
	AsyncCache<String, JsonArray> listsCache;

	@Inject
	@Named("cache.hero")
	AsyncLoadingCache<String, JsonObject> heroCache;

	@Inject
	@Named("cache.heroes.lookup")
	Function<String, JsonArray> heroesLookup;

	public JsonArray getHeroes() {
		return listsCache.synchronous().get("heroes", heroesLookup);
	}

	public JsonObject getHero(String id) {
		return heroCache.synchronous().get(id);
	}
}
