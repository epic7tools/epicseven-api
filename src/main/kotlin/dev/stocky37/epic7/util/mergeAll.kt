package dev.stocky37.epic7.util

fun <K, V> mergeAll(reduce: (V, V) -> V = { _, b -> b }, vararg maps: Map<K, V>): Map<K, V> {
	val result = LinkedHashMap<K, V>()
	for(map in maps) {
		for((key, value) in map) {
			result.merge(key, value, reduce)
		}
	}
	return result
}