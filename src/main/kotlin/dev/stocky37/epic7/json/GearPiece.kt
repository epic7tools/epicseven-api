package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.GearSet
import dev.stocky37.epic7.core.Stat
import dev.stocky37.epic7.repr.StatsFromMap
import java.math.BigDecimal
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty

data class GearPiece @JsonbCreator constructor(
	@JsonbProperty("stats") val stats: Map<String, BigDecimal>,
	@JsonbProperty("set") val set: String
) {
	fun getStatMap(): Map<Stat, BigDecimal> {
		return StatsFromMap.instance().apply(stats)
	}

	fun getGearSet(): GearSet? {
		return if(!set.isBlank()) GearSet.fromId(set) else null
	}
}