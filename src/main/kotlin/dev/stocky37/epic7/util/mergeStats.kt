package dev.stocky37.epic7.util

import dev.stocky37.epic7.core.Stat
import java.math.BigDecimal

fun mergeStats(vararg maps: Map<Stat, BigDecimal>): Map<Stat, BigDecimal> {
	return mergeAll({ a, b -> a + b }, *maps)
}