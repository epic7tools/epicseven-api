package dev.stocky37.epic7.core;

import com.google.common.collect.Iterables;
import dev.stocky37.epic7.json.Hero;
import org.immutables.value.Value;

@Value.Immutable
public abstract class LevelledHero {
	abstract Hero hero();

	abstract int stars();

	abstract int level();

	abstract int awakening();

	public Stats getAwakenedStats() {
		return getBaseStats().apply(getAwakenings());
	}

	public Stats getBaseStats() {
		switch(stars()) {
			case 5:
				return hero().getLvl50Stats();
			case 6:
				return hero().getLvl60Stats();
			default:
				throw new IllegalStateException();
		}
	}

	public Iterable<Stats> getAwakenings() {
		return Iterables.limit(hero().getAwakeningStats(), awakening());
	}
}
