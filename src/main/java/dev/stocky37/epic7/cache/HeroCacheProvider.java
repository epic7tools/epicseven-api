package dev.stocky37.epic7.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.time.Duration;

public class HeroCacheProvider {

	@Inject
	HeroCacheLoader cacheLoader;

	@Produces @ApplicationScoped
	AsyncLoadingCache<String, JsonObject> provideCache() {
		return Caffeine.newBuilder()
				.expireAfterWrite(Duration.ofHours(2))
				.buildAsync(cacheLoader);
	}
}
