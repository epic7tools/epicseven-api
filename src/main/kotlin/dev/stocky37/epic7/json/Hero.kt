package dev.stocky37.epic7.json

import dev.stocky37.epic7.core.Stat
import dev.stocky37.epic7.core.Stats
import java.math.BigDecimal
import javax.json.Json
import javax.json.JsonObject

data class Hero(private val json: JsonObject) : JsonObject by json {
	val lv50Stats: Stats by lazy {
		getStats(statsJson.getJsonObject("lv50FiveStarNoAwaken"))
	}

	val lv60Stats: Stats by lazy {
		getStats(statsJson.getJsonObject("lv60SixStarNoAwaken"))
	}

	val awakenings: List<Stats> by lazy {
		awakeningsStatsJson.map { getStats(it) }
	}

	private val statsJson: JsonObject by lazy {
		json.getJsonObject("stats")
	}


	private val awakeningsJson: List<JsonObject> by lazy {
		json.getJsonArray("awakening").getValuesAs(JsonObject::class.java)
	}

	private val awakeningsStatsJson: List<JsonObject> by lazy {
		awakeningsJson
			.map { it.getJsonArray("statsIncrease").getValuesAs(JsonObject::class.java) }
			.map { combineAwakeningStats(it) }
	}

//	private val awakeningStatPairs: List<List<Pair<Stat, BigDecimal>>> by lazy {
//		awakeningsStatsJson.map { it.map { json -> json. } }
//	}

//	fun getPairs(json: JsonObject): List<Pair<Stat, BigDecimal>> {
//		return json.map { (key, node) -> {
//			val baseStat = Stat.fromId(key)
//			val value = BigDecimal(node.toString())
//			val stat = if(value < BigDecimal.ONE) Stat.fromId("$key%") else baseStat
//			Pair(stat, value)
//		} }
//	}


	companion object {
		private fun getStats(json: JsonObject): Stats {
			return Stats(json
				.filterKeys { !it.equals("cp", true) }
				.mapKeys { Stat.fromId(it.key) }
				.mapValues { BigDecimal(it.value.toString()) }
			)
		}

		private fun combineAwakeningStats(jsonArray: List<JsonObject>): JsonObject {
			val builder = Json.createObjectBuilder()
			jsonArray.forEach {
				it.forEach { (key, value) ->
					run {
						// handle the fact that
						val newKey = if(
							!Stat.fromId(key).isPercentage
							&& BigDecimal(value.toString()) < BigDecimal.ONE
						) "$key%" else key
						builder.add(newKey, value)
					}
				}
			}
			return builder.build()
		}
	}
}