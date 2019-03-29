package dev.stocky37.epic7.core

import dev.stocky37.epic7.core.Stat.*
import java.math.BigDecimal

enum class GearSet constructor(
	val id: String,
	val label: String,
	val amountForSet: Int,
	val stat: Stat? = null,
	val value: BigDecimal? = null
) {
	ATTACK("attack", "Attack Set", 4, ATK_PERC, BigDecimal("0.35")),
	CRITICAL("critical", "Critical Set", 2, CHC, BigDecimal("0.12")),
	HEALTH("health", "Health Set", 2, HP_PERC, BigDecimal("0.15")),
	LIFESTEAL("lifesteal", "Lifesteal Set", 4, LS, BigDecimal("0.2")),
	HIT("hit", "Hit Set", 2, EFF, BigDecimal("0.2")),
	SPEED("speed", "Speed Set", 4, SPD_PERC, BigDecimal("0.25")),
	DESTRUCTION("destruction", "Destruction Set", 4, CHD, BigDecimal("0.4")),
	COUNTER("counter", "Counter Set", 4, CA, BigDecimal("0.2")),
	RESIST("resist", "Resist Set", 2, EFR, BigDecimal("0.2")),
	DEFENSE("defense", "Defense Set", 2, DEF_PERC, BigDecimal("0.15")),
	RAGE("rage", "Rage Set", 4, DMG, BigDecimal("0.3")),
	IMMUNITY("immunity", "Immunity Set", 2),
	UNITY("unity", "Unity Set", 2, DAC, BigDecimal("0.04"));

	companion object {
		// todo: if this is slow, change to map impl
		fun fromId(str: String): GearSet {
			return GearSet.values().single { stat -> stat.id.equals(str, ignoreCase = true) }
		}
	}
}
