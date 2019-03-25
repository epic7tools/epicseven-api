package dev.stocky37.epic7.repr;

import com.google.common.collect.ImmutableList;
import dev.stocky37.epic7.core.GearSet;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;


@Value.Immutable
public abstract class GearPiece {
	@JsonbCreator
	public static GearPiece create(@JsonbProperty("set") GearSet set, @JsonbProperty("stats") StatValue[] stats) {
		return ImmutableGearPiece.builder().gearSet(set).stats(ImmutableList.copyOf(stats)).build();
	}

	public abstract GearSet gearSet();

	public abstract List<StatValue> stats();
}
