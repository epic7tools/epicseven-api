package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.GearSet
import dev.stocky37.epic7.core.Stats
import java.math.BigDecimal
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty

data class GearPiece @JsonbCreator constructor(
	@JsonbProperty("stats") val statsMap: Map<String, BigDecimal>,
	@JsonbProperty("set") val set: String
) {

	val stats: Stats by lazy {
		Stats.fromStringMap(statsMap)
	}

	val gearSet: GearSet? by lazy {
		if(!set.isBlank()) GearSet.fromId(set) else null
	}
}