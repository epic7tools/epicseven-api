package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.GearSet
import dev.stocky37.epic7.core.Stats
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty

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

	val gearStats: Stats by lazy {
		Stats.from(
			artifact.stats,
			weapon.stats,
			helmet.stats,
			armour.stats,
			necklace.stats,
			ring.stats,
			boots.stats,
			gearSetStats
		)
	}

	val completedGearSets: List<GearSet> by lazy {
		gearSetCount.flatMap { (set, count) ->
			val total = count / set.amountForSet
			if(total > 0) List(total) { set } else emptyList()
		}
	}

	private val gearSetStats: Stats by lazy {
		Stats.from(completedGearSets
			.filter { set -> set.stat.isPresent }
			.map { Pair(it.stat.get(), it.value.get()) }
		)
	}

	private val gearSetCount: Map<GearSet, Int> by lazy {
		allGearSets.groupingBy { it }.eachCount()
	}

	private val allGearSets: List<GearSet> by lazy {
		listOfNotNull(
			weapon.gearSet,
			helmet.gearSet,
			armour.gearSet,
			necklace.gearSet,
			ring.gearSet,
			boots.gearSet
		)
	}
}