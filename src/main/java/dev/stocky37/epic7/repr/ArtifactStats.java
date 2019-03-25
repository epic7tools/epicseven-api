package dev.stocky37.epic7.repr;

import com.google.common.collect.ImmutableList;
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

	@Value.Default
	public List<StatValue> stats() {
		return ImmutableList.of();
	}
}
