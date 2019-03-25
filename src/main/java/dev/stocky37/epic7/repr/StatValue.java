package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import dev.stocky37.epic7.core.Stat;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class StatValue {
	private final Stat stat;
	private final BigDecimal value;

	@JsonbCreator
	public StatValue(@JsonbProperty("stat") Stat stat, @JsonbProperty("value") BigDecimal value) {
		this.stat = stat;
		this.value = value;
	}

	public Stat getStat() {
		return stat;
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("stat", stat)
				.add("value", value)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof StatValue)) {
			return false;
		}
		StatValue statValue = (StatValue) o;
		return stat == statValue.stat &&
				Objects.equal(value, statValue.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stat, value);
	}
}
