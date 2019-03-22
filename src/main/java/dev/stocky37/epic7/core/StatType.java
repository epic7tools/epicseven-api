package dev.stocky37.epic7.core;

import dev.stocky37.epic7.repr.StatValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public enum StatType {
	ATK("atk", "Attack", false, true),
	ATK_PERC("atk%", "Attack%", true, true, StatType.ATK),
	HP("hp", "Health", false, true),
	HP_PERC("hp%", "Health%", true, true, StatType.HP),
	DEF("def", "Defense", false, true),
	DEF_PERC("def%", "Defense%", true, true, StatType.DEF),
	SPD("spd", "Speed", false, true),
	SPD_PERC("spd%", "Speed%", true, true, StatType.SPD),
	CHC("chc", "Critical Hit Chance", true, true),
	CHD("chd", "Critical Hit Damage", true, true),
	EFF("eff", "Effectiveness", true, true),
	EFR("efr", "Effect Resistance", true, true),
	DAC("dac", "Dual Attack Chance", true, false),
	LS("ls", "Lifesteal", true, false),
	CA("ca", "Counterattack", true, false),
	DMG("dmg", "Damage", true, false);


	private final String id;
	private final String name;
	private final boolean percentage;
	private final boolean availableOnGear;
	private final StatType baseStatType;

	StatType(String id, String name, boolean percentage, boolean availableOnGear) {
		this(id, name, percentage, availableOnGear, null);
	}

	StatType(String id, String name, boolean percentage, boolean availableOnGear, StatType baseStatType) {
		this.id = id;
		this.name = name;
		this.percentage = percentage;
		this.availableOnGear = availableOnGear;
		this.baseStatType = baseStatType;
	}

	public static List<StatType> getAllBaseStats() {
		return Arrays.stream(StatType.values())
				.filter(StatType::isBaseStat)
				.collect(Collectors.toList());
	}

	public static List<StatType> getAllGearStats() {
		return Arrays.stream(StatType.values())
				.filter(StatType::isAvailableOnGear)
				.collect(Collectors.toList());
	}

	// todo: if this is slow, change to map impl
	public static StatType fromId(String str) throws IllegalArgumentException {
		return Arrays.stream(StatType.values())
				.filter(stat -> stat.getId().equalsIgnoreCase(str))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isPercentage() {
		return percentage;
	}

	public boolean isAvailableOnGear() {
		return availableOnGear;
	}

	public StatType getBaseStatType() {
		return baseStatType == null ? this : baseStatType;
	}

	public boolean isBaseStat() {
		return baseStatType == null;
	}
}



