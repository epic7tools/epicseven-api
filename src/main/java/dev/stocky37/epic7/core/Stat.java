package dev.stocky37.epic7.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum Stat {
	ATK("atk", "Attack", false, true),
	ATK_PERC("atk%", "Attack%", true, true, Stat.ATK),
	HP("hp", "Health", false, true),
	HP_PERC("hp%", "Health%", true, true, Stat.HP),
	DEF("def", "Defense", false, true),
	DEF_PERC("def%", "Defense%", true, true, Stat.DEF),
	SPD("spd", "Speed", false, true),
	SPD_PERC("spd%", "Speed%", true, true, Stat.SPD),
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
	private final Stat baseStat;

	Stat(String id, String name, boolean percentage, boolean availableOnGear) {
		this(id, name, percentage, availableOnGear, null);
	}

	Stat(String id, String name, boolean percentage, boolean availableOnGear, Stat baseStat) {
		this.id = id;
		this.name = name;
		this.percentage = percentage;
		this.availableOnGear = availableOnGear;
		this.baseStat = baseStat;
	}

	public static List<Stat> getAllBaseStats() {
		return Arrays.stream(Stat.values())
				.filter(Stat::isBaseStat)
				.collect(Collectors.toList());
	}

	public static List<Stat> getAllGearStats() {
		return Arrays.stream(Stat.values())
				.filter(Stat::isAvailableOnGear)
				.collect(Collectors.toList());
	}

	// todo: if this is slow, change to map impl
	public static Stat fromId(String str) throws IllegalArgumentException {
		return Arrays.stream(Stat.values())
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

	public Stat getBaseStat() {
		return baseStat == null ? this : baseStat;
	}

	public boolean isBaseStat() {
		return baseStat == null;
	}
}



