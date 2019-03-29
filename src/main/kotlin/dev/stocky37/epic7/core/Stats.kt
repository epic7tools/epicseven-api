package dev.stocky37.epic7.core

import java.math.BigDecimal

data class Stats(private val stats: Map<Stat, BigDecimal> = mapOf()) : Map<Stat, BigDecimal> by stats {

	companion object {
		fun fromStringMap(stats: Map<String, BigDecimal>): Stats {
			return Stats(stats.mapKeys { Stat.fromId(it.key) })
		}

		fun from(vararg from: Map<Stat, BigDecimal>): Stats {
			return Stats().merge(from.asIterable())
		}

		fun from(from: Iterable<Pair<Stat, BigDecimal>>): Stats {
			return Stats().mergePairs(from.asIterable())
		}
	}

	val isBase: Boolean by lazy {
		stats.keys.all { it.isBaseStat }
	}

	fun merge(from: Iterable<Map<Stat, BigDecimal>>): Stats {
		return mergeEntries(from.map { it.asIterable() }.flatten())
	}

	private fun mergeEntries(from: Iterable<Map.Entry<Stat, BigDecimal>>): Stats {
		return mergePairs(from.map { it.toPair() })
	}

	fun mergePairs(from: Iterable<Pair<Stat, BigDecimal>>): Stats {
		val combined = this.toMutableMap()
		from.forEach { combined.merge(it.first, it.second) { a, b -> a + b } }
		return Stats(combined)
	}

	fun apply(from: Map<Stat, BigDecimal>): Stats {
		if(!isBase) {
			// todo: base stats and regular stats should probs be different classes
			throw IllegalStateException("Can't call apply() on non-base stats")
		}
		val totalStats = this.toMutableMap()
		from.forEach { stat, value ->
			run {
				val newValue = if(stat.isBaseStat) value else stats.getOrDefault(stat.getBaseStat(), BigDecimal.ZERO) * value
				totalStats.merge(stat.getBaseStat(), newValue) { a, b -> a + b }
			}
		}
		return Stats(totalStats)
	}
}
