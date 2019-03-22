package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import dev.stocky37.epic7.core.GearSet;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class GearPiece {
	private final GearSet set;
	private final List<StatLine> stats;

	@JsonbCreator
	public GearPiece(@JsonbProperty("set") GearSet set, @JsonbProperty("stats") List<StatLine> stats) {
		this.set = set;
		this.stats = ImmutableList.copyOf(stats);
	}

	public GearSet getSet() {
		return set;
	}

	public List<StatLine> getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("set", set)
				.add("stats", stats)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof GearPiece)) {
			return false;
		}
		GearPiece gearPiece = (GearPiece) o;
		return set == gearPiece.set &&
				Objects.equal(stats, gearPiece.stats);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(set, stats);
	}
}
