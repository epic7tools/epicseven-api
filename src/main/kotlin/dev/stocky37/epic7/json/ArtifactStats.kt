package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.Stat
import dev.stocky37.epic7.repr.StatsFromMap
import java.math.BigDecimal
import javax.json.bind.annotation.JsonbCreator
import javax.json.bind.annotation.JsonbProperty

data class ArtifactStats @JsonbCreator constructor(@JsonbProperty("stats") val stats: Map<String, BigDecimal>) {
	fun getStatMap(): Map<Stat, BigDecimal> {
		return StatsFromMap.instance().apply(stats)
	}
}