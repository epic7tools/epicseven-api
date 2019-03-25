package dev.stocky37.epic7.repr;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import dev.stocky37.epic7.core.GearSet;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;
import java.util.Optional;


@Value.Immutable
public abstract class GearPiece {

	@JsonbCreator
	public static GearPiece create(@JsonbProperty("set") String set, @JsonbProperty("stats") StatValue[] stats) {
		return ImmutableGearPiece.builder()
			.gearSet(Strings.isNullOrEmpty(set) ? Optional.empty() : Optional.of(GearSet.fromId(set)))
			.stats(ImmutableList.copyOf(stats))
			.build();
	}

	public abstract Optional<GearSet> gearSet();

	@Value.Default
	public List<StatValue> stats() {
		return ImmutableList.of();
	}
}
