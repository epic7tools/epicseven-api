package dev.stocky37.epic7.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import dev.stocky37.epic7.repr.StatValue;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class HeroJsonWrapper {
	private final JsonObject json;
	private final int stars;
	private final int level;
	private final int awakening;

	public HeroJsonWrapper(JsonObject json, int stars, int level, int awakening) {
		this.json = json;
		this.stars = stars;
		this.level = level;
		this.awakening = awakening;
	}


	public Map<StatType, BigDecimal> getBaseStats() {
		final Map<StatType, BigDecimal> stats = Maps.newEnumMap(StatType.class);
		final JsonObject baseStats = getBaseStatObj(stars, level);
		baseStats.forEach((key, value) -> {
			if(!key.equalsIgnoreCase("cp")) {
				final StatType stat = StatType.fromId(key);
				final BigDecimal num = new BigDecimal(value.toString());
				stats.put(stat, num);
			}
		});
		return ImmutableMap.copyOf(stats);
	}

	public Collection<JsonObject> getAwakenings() {
		final JsonArray awakenings = json.getJsonArray("awakening");
		return ImmutableList.copyOf(awakenings.getValuesAs(JsonObject.class).subList(0, awakening)
			.stream()
			.limit(awakening)
			.collect(Collectors.toList()));
	}

	public Map<StatType, BigDecimal> getAwakenedBaseStats() {
		final Map<StatType, BigDecimal> baseStats = getBaseStats();
		final Map<StatType, BigDecimal> newBaseStats = Maps.newEnumMap(baseStats);
		final Collection<JsonObject> awakenings = getAwakenings();
		awakenings.forEach(awakening -> awakening.getJsonArray("statsIncrease")
			.getValuesAs(JsonObject.class)
			.forEach(increase -> {
				// get all stat: value key pairs for each statsIncrease array entry
				increase.forEach((key, node) -> {
					final StatType stat = StatType.fromId(key);
					final BigDecimal value = new BigDecimal(node.toString());

					// if we are not working with a percentage stat
					// AND the value is not an integer, assume that we are adding
					// a percentage of the base stats
					if(!stat.isPercentage() && value.stripTrailingZeros().scale() >= 1) {
						newBaseStats.merge(stat, baseStats.get(stat).multiply(value), BigDecimal::add) ;
					} else {
						newBaseStats.merge(stat, value, BigDecimal::add) ;
					}

				});
			}));
		return ImmutableMap.copyOf(newBaseStats);
	}

	private static Map<StatType, BigDecimal> calculateStats(
		Map<StatType, BigDecimal> baseStats,
		Iterable<StatValue> statModifiers
	) {
		final Map<StatType, BigDecimal> stats = Maps.newEnumMap(baseStats);

		statModifiers.forEach(mod -> {
			final BigDecimal existingValue = stats.get(mod.getStat());
			final StatType stat = mod.getStat().getBaseStatType();

			// stat has no other base stat, just add on to the existing value
			if(stat == mod.getStat()) {
				stats.put(stat, existingValue.add(mod.getValue()));
			} else {
				stats.put(stat, existingValue.add(baseStats.get(stat).multiply(mod.getValue())));
			}
		});

		return ImmutableMap.copyOf(stats);
	}

	private JsonObject getBaseStatObj(int stars, int level) {
		switch(stars) {
			case 5:
				return json.getJsonObject("stats").getJsonObject("lv50FiveStarNoAwaken");
			case 6:
				return json.getJsonObject("stats").getJsonObject("lv60SixStarNoAwaken");
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof HeroJsonWrapper)) {
			return false;
		}
		HeroJsonWrapper that = (HeroJsonWrapper) o;
		return Objects.equal(json, that.json);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(json);
	}

	@Override
	public String toString() {
		return json.toString();
	}
}
