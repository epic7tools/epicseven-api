package dev.stocky37.epic7.core

import dev.stocky37.epic7.json.Hero

data class LevelledHero(val hero: Hero, val level: Int, val stars: Int, val awakening: Int) {

	val baseStats: Stats by lazy {
		when(stars) {
			5 -> hero.lv50Stats
			6 -> hero.lv60Stats
			else -> throw IllegalStateException()
		}
	}

	val awakenedBaseStats: Stats by lazy {
		baseStats.apply(Stats.from(*awakenings.toTypedArray()))
	}

	val awakenings: List<Stats> by lazy {
		hero.awakenings.slice(0 until awakening)
	}

}