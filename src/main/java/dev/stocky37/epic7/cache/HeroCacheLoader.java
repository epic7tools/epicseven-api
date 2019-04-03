package dev.stocky37.epic7.cache;

import dev.stocky37.epic7.client.EpicSevenDbApi;
import dev.stocky37.epic7.json.HeroTransform;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

@ApplicationScoped
public class HeroCacheLoader extends EpicSevenDbCacheLoader {

	@Inject
	@RestClient
	@SuppressWarnings("CdiInjectionPointsInspection")
	EpicSevenDbApi api;

	public HeroCacheLoader() {
		super(HeroTransform.getInstance());
	}

	@Override
	protected JsonObject loadFromApi(@NonNull String id) {
		return api.getHero(id);
	}
}