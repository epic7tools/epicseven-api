package dev.stocky37.epic7.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

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


	public Map<Stat, BigDecimal> getBaseStats() {
		final Map<Stat, BigDecimal> stats = Maps.newEnumMap(Stat.class);
		final JsonObject baseStats = getBaseStatObj(stars, level);
		baseStats.forEach((key, value) -> {
			if(!key.equalsIgnoreCase("cp")) {
				final Stat stat = Stat.fromId(key);
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

	public Map<Stat, BigDecimal> getAwakenedBaseStats() {
		final Map<Stat, BigDecimal> baseStats = getBaseStats();
		final Map<Stat, BigDecimal> newBaseStats = Maps.newEnumMap(baseStats);
		final Collection<JsonObject> awakenings = getAwakenings();
		awakenings.forEach(awakening -> awakening.getJsonArray("statsIncrease")
			.getValuesAs(JsonObject.class)
			.forEach(increase -> {
				// get all stat: value key pairs for each statsIncrease array entry
				increase.forEach((key, node) -> {
					final Stat stat = Stat.fromId(key);
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

	public Map<Stat, BigDecimal> calculateStats(Map<Stat, BigDecimal> statModifiers) {
		final Map<Stat, BigDecimal> baseStats = getAwakenedBaseStats();
		final Map<Stat, BigDecimal> stats = Maps.newEnumMap(baseStats);

		statModifiers.forEach((stat, value) -> {
			final Stat baseStat = stat.getBaseStat();

			// stat has no other base stat, just add on to the existing value
			if(baseStat == stat) {
				stats.merge(baseStat, value, BigDecimal::add);
			} else {
				stats.merge(baseStat, baseStats.get(baseStat).multiply(value), BigDecimal::add);
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
