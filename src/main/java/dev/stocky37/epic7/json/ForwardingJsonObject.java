package dev.stocky37.epic7.json;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ForwardingJsonObject implements JsonObject {
	protected abstract JsonObject delegate();

	@Override
	public JsonArray getJsonArray(String s) {
		return delegate().getJsonArray(s);
	}

	@Override
	public JsonObject getJsonObject(String s) {
		return delegate().getJsonObject(s);
	}

	@Override
	public JsonNumber getJsonNumber(String s) {
		return delegate().getJsonNumber(s);
	}

	@Override
	public JsonString getJsonString(String s) {
		return delegate().getJsonString(s);
	}

	@Override
	public String getString(String s) {
		return delegate().getString(s);
	}

	@Override
	public String getString(String s, String s1) {
		return delegate().getString(s, s1);
	}

	@Override
	public int getInt(String s) {
		return delegate().getInt(s);
	}

	@Override
	public int getInt(String s, int i) {
		return delegate().getInt(s, i);
	}

	@Override
	public boolean getBoolean(String s) {
		return delegate().getBoolean(s);
	}

	@Override
	public boolean getBoolean(String s, boolean b) {
		return delegate().getBoolean(s, b);
	}

	@Override
	public boolean isNull(String s) {
		return delegate().isNull(s);
	}

	@Override
	public JsonValue getValue(String jsonPointer) {
		return delegate().getValue(jsonPointer);
	}

	@Override
	public ValueType getValueType() {
		return delegate().getValueType();
	}

	@Override
	public JsonObject asJsonObject() {
		return delegate().asJsonObject();
	}

	@Override
	public JsonArray asJsonArray() {
		return delegate().asJsonArray();
	}

	@Override
	public String toString() {
		return delegate().toString();
	}

	@Override
	public int size() {
		return delegate().size();
	}

	@Override
	public boolean isEmpty() {
		return delegate().isEmpty();
	}

	@Override
	public boolean containsKey(Object o) {
		return delegate().containsKey(o);
	}

	@Override
	public boolean containsValue(Object o) {
		return delegate().containsValue(o);
	}

	@Override
	public JsonValue get(Object o) {
		return delegate().get(o);
	}

	@Override
	public JsonValue put(String s, JsonValue jsonValue) {
		return delegate().put(s, jsonValue);
	}

	@Override
	public JsonValue remove(Object o) {
		return delegate().remove(o);
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonValue> map) {
		delegate().putAll(map);
	}

	@Override
	public void clear() {
		delegate().clear();
	}

	@Override
	public Set<String> keySet() {
		return delegate().keySet();
	}

	@Override
	public Collection<JsonValue> values() {
		return delegate().values();
	}

	@Override
	public Set<Entry<String, JsonValue>> entrySet() {
		return delegate().entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return delegate().equals(o);
	}

	@Override
	public int hashCode() {
		return delegate().hashCode();
	}

	@Override
	public JsonValue getOrDefault(Object key, JsonValue defaultValue) {
		return delegate().getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super String, ? super JsonValue> action) {
		delegate().forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super String, ? super JsonValue, ? extends JsonValue> function) {
		delegate().replaceAll(function);
	}

	@Override
	public JsonValue putIfAbsent(String key, JsonValue value) {
		return delegate().putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, Object value) {
		return delegate().remove(key, value);
	}

	@Override
	public boolean replace(String key, JsonValue oldValue, JsonValue newValue) {
		return delegate().replace(key, oldValue, newValue);
	}

	@Override
	public JsonValue replace(String key, JsonValue value) {
		return delegate().replace(key, value);
	}

	@Override
	public JsonValue computeIfAbsent(
		String key,
		Function<? super String, ? extends JsonValue> mappingFunction
	) {
		return delegate().computeIfAbsent(key, mappingFunction);
	}

	@Override
	public JsonValue computeIfPresent(
		String key,
		BiFunction<? super String, ? super JsonValue, ? extends JsonValue> remappingFunction
	) {
		return delegate().computeIfPresent(key, remappingFunction);
	}

	@Override
	public JsonValue compute(
		String key,
		BiFunction<? super String, ? super JsonValue, ? extends JsonValue> remappingFunction
	) {
		return delegate().compute(key, remappingFunction);
	}

	@Override
	public JsonValue merge(
		String key,
		JsonValue value,
		BiFunction<? super JsonValue, ? super JsonValue, ? extends JsonValue> remappingFunction
	) {
		return delegate().merge(key, value, remappingFunction);
	}
}
