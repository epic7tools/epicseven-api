package dev.stocky37.epic7.core

enum class Stat constructor(
	val id: String,
	val label: String,
	val isPercentage: Boolean,
	val isAvailableOnGear: Boolean,
	private val baseStat: Stat? = null
) {
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

	val isBaseStat: Boolean by lazy {
		baseStat == null
	}

	fun getBaseStat(): Stat {
		return baseStat ?: this
	}

	companion object {
		fun getBaseStats(): List<Stat> {
			return Stat.values().filter { it.isBaseStat }
		}

		fun getGearStats(): List<Stat> {
			return Stat.values().filter { it.isAvailableOnGear }
		}

		// todo: if this is slow, change to map impl
		fun fromId(str: String): Stat {
			return values().single { stat -> stat.id.equals(str, ignoreCase = true) }
		}
	}
}



