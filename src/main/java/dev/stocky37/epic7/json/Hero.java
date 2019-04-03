package dev.stocky37.epic7.json;

import com.google.common.base.Converter;
import dev.stocky37.epic7.core.Stat;
import dev.stocky37.epic7.core.Stats;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Hero extends ForwardingJsonObject {
	private static final Converter<Stats, JsonObject> converter = StatsJsonConverter.instance();
	private final JsonObject json;

	public Hero(JsonObject json) {
		this.json = json;
	}

	private static JsonObject combineAwakeningStats(Iterable<JsonObject> arr) {
		final JsonObjectBuilder builder = Json.createObjectBuilder();
		for(final JsonObject stats : arr) {
			stats.forEach((key, value) -> {
				final Stat stat = Stat.fromId(key);
				final BigDecimal numValue = new BigDecimal(value.toString());
				// if it's NOT a %stat and it's value is less than 1, assume it's meant to be a percent of the base stat
				final String newKey = !stat.isPercentage() && numValue.compareTo(BigDecimal.ONE) < 0 ? key + "%" : key;
				builder.add(newKey, numValue);
			});
		}
		return builder.build();
	}

	@Override
	protected JsonObject delegate() {
		return json;
	}

	public Stats getLvl50Stats() {
		return converter.reverse().convert(getStatsJson().getJsonObject("lv50FiveStarNoAwaken"));
	}

	public Stats getLvl60Stats() {
		return converter.reverse().convert(getStatsJson().getJsonObject("lv60SixStarNoAwaken"));
	}

	public Iterable<Stats> getAwakeningStats() {
		return converter.reverse().convertAll(getAwakeningStatsJson());
	}

	private List<JsonObject> getAwakeningStatsJson() {
		return getAwakeningJson().stream()
			.map(it -> it.getJsonArray("statsIncrease").getValuesAs(JsonValue::asJsonObject))
			.map(Hero::combineAwakeningStats)
			.collect(Collectors.toList());
	}

	private List<JsonObject> getAwakeningJson() {
		return json.getJsonArray("awakening").getValuesAs(JsonValue::asJsonObject);
	}

	private JsonObject getStatsJson() {
		return json.getJsonObject("stats");
	}


}
