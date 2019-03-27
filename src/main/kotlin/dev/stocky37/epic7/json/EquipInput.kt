package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.GearSet
import dev.stocky37.epic7.core.Stat
import dev.stocky37.epic7.util.mergeAll
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
		var result = mergeAll(
			{ a, b -> a + b },
			artifact.getStatMap(),
			weapon.getStatMap(),
			helmet.getStatMap(),
			armour.getStatMap(),
			necklace.getStatMap(),
			ring.getStatMap(),
			boots.getStatMap()
		)


		getAppliedGearSets().forEach { (key, value) -> result.merge(key, value, BigDecimal::add) }






		return result

//		getCompleteGearSets().stream()
//			.filter({ x -> x.getStat().isPresent() })
//			.forEach { set ->
//				(.getStatMap as java.util.Map<Stat, BigDecimal>).merge(
//					set.getStat().get(),
//					set.getValue().get(),
//					BiFunction<BigDecimal, BigDecimal, BigDecimal> { obj, augend -> obj.add(augend) })
//			}
//
//		return ImmutableMap.copyOf(.getStatMap)
	}

	fun getAppliedGearSets(): List<GearSet> {
		println(getGearSetCount())
		val list = getGearSetCount().flatMap { (set, count) ->
			val total = count / set.amountForSet
			if(total > 0) List(total) { set } else emptyList()
		}

		println(list.filter { it.stat.isPresent }.groupBy({ it.value.get() }, {})

			return list
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