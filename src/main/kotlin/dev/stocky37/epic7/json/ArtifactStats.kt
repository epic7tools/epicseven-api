package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.Stats
import java.math.BigDecimal
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty

data class ArtifactStats @JsonbCreator constructor(@JsonbProperty("stats") val statsMap: Map<String, BigDecimal>) {
	val stats: Stats by lazy {
		Stats.fromStringMap(statsMap)
	}
}