package dev.stocky37.epic7.repr;

import dev.stocky37.epic7.core.Stat;
import org.immutables.value.Value;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

@Value.Immutable
public abstract class StatValue {
	@JsonbCreator
	public static StatValue create(@JsonbProperty("stat") Stat stat, @JsonbProperty("value") BigDecimal value) {
		return ImmutableStatValue.builder().stat(stat).value(value).build();
	}

	public abstract Stat stat();

	public abstract BigDecimal value();
}
