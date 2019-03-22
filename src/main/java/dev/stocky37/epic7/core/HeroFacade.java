package dev.stocky37.epic7.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class HeroFacade {
	private final JsonObject json;

	public HeroFacade(JsonObject json) {
		this.json = json;
	}

	public JsonObject getJson() {
		return json;
	}

	public Map<Stat, BigDecimal> getBaseStats(int stars, int level) {
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
		if(!(o instanceof HeroFacade)) {
			return false;
		}
		HeroFacade that = (HeroFacade) o;
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
