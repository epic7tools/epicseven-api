package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.GearSet
import dev.stocky37.epic7.core.Stat
import dev.stocky37.epic7.util.mergeStats
import java.math.BigDecimal
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty
import javax.json.bind.annotation.JsonbTransient

data class EquipInput @JsonbCreator constructor(
	@JsonbProperty("level") val level: Int,
	@JsonbProperty("stars") val stars: Int,
	@JsonbProperty("awakening") val awakening: Int,
	@JsonbProperty("artifact") val artifact: ArtifactStats,
	@JsonbProperty("weapon") val weapon: GearPiece,
	@JsonbProperty("helmet") val helmet: GearPiece,
	@JsonbProperty("armour") val armour: GearPiece,
	@JsonbProperty("necklace") val necklace: GearPiece,
	@JsonbProperty("ring") val ring: GearPiece,
	@JsonbProperty("boots") val boots: GearPiece
) {

	@JsonbTransient
	fun getGearStats(): Map<Stat, BigDecimal> {
		return mergeStats(
			artifact.getStatMap(),
			weapon.getStatMap(),
			helmet.getStatMap(),
			armour.getStatMap(),
			necklace.getStatMap(),
			ring.getStatMap(),
			boots.getStatMap(),
			getCompletedGearSetsStats()
		)
	}

	fun getCompletedGearSets(): List<GearSet> {
		return getGearSetCount().flatMap { (set, count) ->
			val total = count / set.amountForSet
			if(total > 0) List(total) { set } else emptyList()
		}
	}

	private fun getCompletedGearSetsStats(): Map<Stat, BigDecimal> {
		val stats = mutableMapOf<Stat, BigDecimal>()
		getCompletedGearSets()
			.filter { set -> set.stat.isPresent }
			.forEach { set ->
				stats.merge(
					set.stat.get(),
					set.value.get(),
					BigDecimal::add
				)
			}
		return stats
	}

	private fun getGearSetCount(): Map<GearSet, Int> {
		return getAllGearSets().groupingBy { it }.eachCount()
	}

	private fun getAllGearSets(): List<GearSet> {
		return listOfNotNull(
			weapon.getGearSet(),
			helmet.getGearSet(),
			armour.getGearSet(),
			necklace.getGearSet(),
			ring.getGearSet(),
			boots.getGearSet()
		)
	}
}