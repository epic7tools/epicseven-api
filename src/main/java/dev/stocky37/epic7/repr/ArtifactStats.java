package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import dev.stocky37.epic7.core.Stat;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class ArtifactStats {
	private final List<Stat> stats;

	@JsonbCreator
	public ArtifactStats(@JsonbProperty("stats") List<Stat> stats) {
		this.stats = stats;
	}

	public List<Stat> getStats() {
		return stats;
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
		return Objects.equal(stats, that.stats);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stats);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("stats", stats)
				.toString();
	}
}
