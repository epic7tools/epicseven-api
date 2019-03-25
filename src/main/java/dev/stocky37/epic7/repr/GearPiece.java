package dev.stocky37.epic7.repr;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import dev.stocky37.epic7.core.GearSet;
import dev.stocky37.epic7.core.Stat;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;


@RegisterForReflection
@Value.Immutable
public abstract class GearPiece {

	// note: yasson does not currently handle parameterized types in an @JsonbCreator function properly
	//       it should be fixed when commit 6ae0add gets released (it has already been merged in to master)
	//       see: https://github.com/eclipse-ee4j/yasson/issues/230
	@JsonbCreator
	public static GearPiece create(
		@JsonbProperty("set") String set,
		@JsonbProperty("stats") Map<String, BigDecimal> stats
	) {
		return ImmutableGearPiece.builder()
			.gearSet(Strings.isNullOrEmpty(set) ? Optional.empty() : Optional.of(GearSet.fromId(set)))
			.stats(StatsFromMap.instance().apply(stats))
			.build();
	}

	public abstract Optional<GearSet> gearSet();

	@Value.Default
	public Map<Stat, BigDecimal> stats() {
		return ImmutableMap.of();
	}
}
