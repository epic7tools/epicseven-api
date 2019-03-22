package dev.stocky37.epic7.repr;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import dev.stocky37.epic7.core.Stat;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class StatLine {

	private final Stat stat;
	private final int value;

	@JsonbCreator
	public StatLine(@JsonbProperty("stat") Stat stat, @JsonbProperty("value") int value) {
		this.stat = stat;
		this.value = value;
	}

	public Stat getStat() {
		return stat;
	}

	public int getValue() {
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
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		StatLine statLine = (StatLine) o;
		return value == statLine.value &&
				stat == statLine.stat;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stat, value);
	}
}
