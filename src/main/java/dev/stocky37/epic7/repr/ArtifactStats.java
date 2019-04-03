package dev.stocky37.epic7.repr;

import com.google.common.collect.ImmutableMap;
import dev.stocky37.epic7.core.Stat;
import dev.stocky37.epic7.core.Stats;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;
import java.util.Map;

@Value.Immutable
public abstract class ArtifactStats {

	// note: yasson does not currently (1.0.3) handle parameterized types in an @JsonbCreator function properly
	//       it should be fixed when commit 6ae0add gets released (it has already been merged in to master)
	//       see: https://github.com/eclipse-ee4j/yasson/issues/230
	@JsonbCreator
	public static ArtifactStats create(@JsonbProperty("stats") Map<String, BigDecimal> stats) {
		return ImmutableArtifactStats.builder().stats(Stats.from(stats)).build();
	}

	@Value.Default
	public Map<Stat, BigDecimal> stats() {
		return ImmutableMap.of();
	}
}
