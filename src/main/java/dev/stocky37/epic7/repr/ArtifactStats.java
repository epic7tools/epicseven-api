package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;
import java.util.Objects;

public class ArtifactStats {
	private final List<StatValue> stats;

	@JsonbCreator
	public ArtifactStats(@JsonbProperty("stats") StatValue[] stats) {
		this.stats = ImmutableList.copyOf(stats);
	}

	public List<StatValue> getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("stats", stats)
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof ArtifactStats)) {
			return false;
		}
		ArtifactStats that = (ArtifactStats) o;
		return stats.equals(that.stats);
	}

	@Override
	public int hashCode() {
		return Objects.hash(stats);
	}
}
