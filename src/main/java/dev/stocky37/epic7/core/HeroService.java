package dev.stocky37.epic7.core;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.google.common.base.Converter;
import dev.stocky37.epic7.json.Hero;
import dev.stocky37.epic7.json.StatsJsonConverter;
import dev.stocky37.epic7.repr.EquipInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class HeroService {
	private static final Converter<Stats, JsonObject> statsConverter = StatsJsonConverter.instance();

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

	public Hero getHero(String id) {
		return new Hero(heroCache.synchronous().get(id));
	}

	public JsonObject getAwakenedStats(String id, int stars, int level, int awakening) {
		return statsConverter.convert(getLevelledHero(id, stars, level, awakening).getAwakenedStats());
	}

	public JsonObject equipHero(String id, EquipInput input) {
		final LevelledHero hero = getLevelledHero(
			id,
			input.stars(),
			input.level(),
			input.awakening()
		);
		return Json.createObjectBuilder()
			.add("stats", statsConverter.convert(hero.getAwakenedStats().apply(input.getGearStats())))
			.add("gearSets", convertSetsToJson(input.getCompleteGearSets()))
			.build();
	}

	private LevelledHero getLevelledHero(String id, int stars, int level, int awakening) {
		return ImmutableLevelledHero.builder()
			.hero(getHero(id))
			.stars(stars)
			.level(level)
			.awakening(awakening)
			.build();
	}

	private JsonArrayBuilder convertSetsToJson(List<GearSet> sets) {
		return Json.createArrayBuilder(sets.stream().map(GearSet::getId).collect(Collectors.toList()));
	}
}
