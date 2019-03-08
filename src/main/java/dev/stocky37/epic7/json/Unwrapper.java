package dev.stocky37.epic7.json;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.function.Function;

public class Unwrapper implements Function<JsonObject, JsonArray> {

	private final String rootKey;

	public Unwrapper() {
		this("results");
	}

	public Unwrapper(String rootKey) {
		this.rootKey = rootKey;
	}

	@Override
	public JsonArray apply(JsonObject obj) {
		return obj.getJsonArray(rootKey);
	}
}
