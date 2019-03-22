package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import dev.stocky37.epic7.core.GearSet;
import dev.stocky37.epic7.core.StatType;

import java.math.BigDecimal;
import java.util.Map;

public class CalculateStatsOutput {
	private final Map<StatType, BigDecimal> stats;
	private final Iterable<GearSet> activeGearSets;

	public CalculateStatsOutput(
			Map<StatType, BigDecimal> stats,
			Iterable<GearSet> activeGearSets
	) {
		this.stats = ImmutableMap.copyOf(stats);
		this.activeGearSets = Iterables.unmodifiableIterable(activeGearSets);
	}

	public Map<StatType, BigDecimal> getStats() {
		return stats;
	}

	public Iterable<GearSet> getActiveGearSets() {
		return activeGearSets;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("stats", stats)
				.add("activeGearSets", activeGearSets)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof CalculateStatsOutput)) {
			return false;
		}
		CalculateStatsOutput that = (CalculateStatsOutput) o;
		return Objects.equal(stats, that.stats) &&
				Objects.equal(activeGearSets, that.activeGearSets);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stats, activeGearSets);
	}
}
