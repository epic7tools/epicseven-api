package dev.stocky37.epic7.repr;

import dev.stocky37.epic7.core.Stat;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatsFromMap implements Function<Map<String, BigDecimal>, Map<Stat, BigDecimal>> {
	private static StatsFromMap instance = new StatsFromMap();

	private StatsFromMap() {
	}

	public static StatsFromMap instance() {
		return instance;
	}

	@Override
	public Map<Stat, BigDecimal> apply(Map<String, BigDecimal> input) {
		return input.entrySet()
			.stream()
			.collect(Collectors.toMap(entry -> Stat.fromId(entry.getKey()), Map.Entry::getValue));
	}
}
