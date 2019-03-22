package dev.stocky37.epic7.core;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static dev.stocky37.epic7.core.StatType.ATK;
import static dev.stocky37.epic7.core.StatType.ATK_PERC;
import static dev.stocky37.epic7.core.StatType.CA;
import static dev.stocky37.epic7.core.StatType.CHC;
import static dev.stocky37.epic7.core.StatType.CHD;
import static dev.stocky37.epic7.core.StatType.DAC;
import static dev.stocky37.epic7.core.StatType.DEF;
import static dev.stocky37.epic7.core.StatType.DEF_PERC;
import static dev.stocky37.epic7.core.StatType.DMG;
import static dev.stocky37.epic7.core.StatType.EFF;
import static dev.stocky37.epic7.core.StatType.EFR;
import static dev.stocky37.epic7.core.StatType.HP_PERC;
import static dev.stocky37.epic7.core.StatType.LS;
import static dev.stocky37.epic7.core.StatType.SPD;
import static dev.stocky37.epic7.core.StatType.SPD_PERC;

public enum GearSet {
	ATTACK("attack", "Attack Set", ATK_PERC, new BigDecimal("0.35"), 4),
	CRITICAL	("critical", "Critical Set", CHC, new BigDecimal("0.12"), 2),
	HEALTH("health", "Health Set", HP_PERC, new BigDecimal("0.15"), 2),
	LIFESTEAL("lifesteal", "Lifesteal Set", LS, new BigDecimal("0.2"), 4),
	HIT("hit", "Hit Set", EFF, new BigDecimal("0.2"), 2),
	SPEED("speed", "Speed Set", SPD_PERC, new BigDecimal("0.25"), 4),
	DESTRUCTION("destruction", "Destruction Set", CHD, new BigDecimal("0.4"), 4),
	COUNTER("counter", "Counter Set", CA, new BigDecimal("0.2"), 4),
	RESIST("resist", "Resist Set", EFR, new BigDecimal("0.2"), 2),
	DEFENSE("defense", "Defense Set", DEF_PERC, new BigDecimal("0.15"), 2),
	RAGE("rage", "Rage Set", DMG, new BigDecimal("0.3"), 4),
	IMMUNITY("immunity", "Immunity Set", null, null, 2),
	UNITY("unity", "Unity Set", DAC, new BigDecimal("0.04"), 2);

	private final String id;
	private final String name;
	private final StatType stat;
	private final BigDecimal value;
	private final int amountForSet;

	GearSet(String id, String name, StatType stat, BigDecimal value, int amountForSet) {
		this.id = id;
		this.name = name;
		this.stat = stat;
		this.value = value;
		this.amountForSet = amountForSet;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Optional<StatType> getStat() {
		return Optional.ofNullable(stat);
	}

	public Optional<BigDecimal> getValue() {
		return Optional.ofNullable(value);
	}

	public int getAmountForSet() {
		return amountForSet;
	}

	// todo: if this is slow, change to map impl
	public static GearSet fromId(String str) throws IllegalArgumentException {
		return Arrays.stream(GearSet.values())
				.filter(gearset -> gearset.getId().equalsIgnoreCase(str))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
