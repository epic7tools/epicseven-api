package dev.stocky37.epic7.json;

import com.google.common.base.Converter;
import com.google.common.collect.ImmutableMap;
import dev.stocky37.epic7.core.Stat;
import dev.stocky37.epic7.core.Stats;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.adapter.JsonbAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ParametersAreNonnullByDefault
public class StatsJsonConverter extends Converter<Stats, JsonObject> implements JsonbAdapter<Stats, JsonObject> {
	private static StatsJsonConverter instance = new StatsJsonConverter();

	private StatsJsonConverter() {}

	public static StatsJsonConverter instance() {
		return instance;
	}

	@Override
	protected JsonObject doForward(Stats stats) {
		return adaptToJson(stats);
	}

	@Override
	protected Stats doBackward(JsonObject json) {
		return adaptFromJson(json);
	}

	@Override
	public JsonObject adaptToJson(Stats stats) {
		final JsonObjectBuilder builder = Json.createObjectBuilder();
		stats.forEach((key, value) -> builder.add(key.getId(), value.setScale(2, RoundingMode.HALF_UP)));
		return builder.build();
	}

	@Override
	public Stats adaptFromJson(JsonObject json) {
		ImmutableMap.Builder<Stat, BigDecimal> builder = ImmutableMap.builder();
		json.keySet()
			.stream()
			.filter(key -> !key.equalsIgnoreCase("cp"))
			.forEach(key -> builder.put(Stat.fromId(key), new BigDecimal(json.get(key).toString())));
		return new Stats(builder.build());
	}
}
