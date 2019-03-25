package dev.stocky37.epic7.core;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import dev.stocky37.epic7.json.StatsJsonTransform;
import dev.stocky37.epic7.repr.EquipInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

	private final Function<Map<Stat, BigDecimal>, JsonObject> statTransform = StatsJsonTransform.getInstance();

	public JsonArray getHeroes() {
		return listsCache.synchronous().get("heroes", heroesLookup);
	}

	public JsonObject getHero(final String id) {
		return heroCache.synchronous().get(id);
	}

	public Map<Stat, BigDecimal> getAwakenedStats(final String id, int stars, int level, int awakening) {
		return new HeroJsonWrapper(getHero(id), stars, level, awakening).getAwakenedBaseStats();
	}

	public JsonObject equipHero(String id, EquipInput input) {
		final HeroJsonWrapper hero = new HeroJsonWrapper(
			getHero(id),
			input.stars(),
			input.level(),
			input.awakening()
		);
		return Json.createObjectBuilder()
			.add("stats", stats(input.getGearStats()))
			.add("gearSets", sets(input.getCompleteGearSets()))
			.build();
	}

	private JsonObject stats(Map<Stat, BigDecimal> stats) {
		return StatsJsonTransform.getInstance().apply(stats);
	}

	private JsonArray sets(List<GearSet> sets) {
		final JsonArrayBuilder builder = Json.createArrayBuilder();
		sets.forEach(set -> builder.add(set.getId()));
		return builder.build();
	}
}
