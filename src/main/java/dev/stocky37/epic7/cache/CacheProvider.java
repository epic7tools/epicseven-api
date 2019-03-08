package dev.stocky37.epic7.cache;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.time.Duration;

public class CacheProvider {

	@Inject
	HeroCacheLoader cacheLoader;

	@Produces @ApplicationScoped @Named("cache.hero")
	AsyncLoadingCache<String, JsonObject> provideCache() {
		return Caffeine.newBuilder()
				.expireAfterWrite(Duration.ofHours(2))
				.buildAsync(cacheLoader);
	}

	@Produces @ApplicationScoped @Named("cache.lists")
	AsyncCache<String, JsonArray> provideApiListsCache() {
		return Caffeine.newBuilder()
				.expireAfterWrite(Duration.ofHours(2))
				.buildAsync();
	}
}
