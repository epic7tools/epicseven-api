package dev.stocky37.epic7.repr;

import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Value.Immutable
public abstract class ArtifactStats {
	@JsonbCreator
	public static ArtifactStats create(@JsonbProperty("stats") StatValue[] stats) {
		return ImmutableArtifactStats.builder().addStats(stats).build();
	}

	public abstract List<StatValue> stats();
}
