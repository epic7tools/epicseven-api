package dev.stocky37.epic7.json;


import javax.json.Json;
import javax.json.JsonObject;
import java.util.function.Function;

public class HeroTransform implements Function<JsonObject, JsonObject> {

	private static HeroTransform instance = new HeroTransform();

	private HeroTransform() {}

	public static HeroTransform getInstance() {
		return instance;
	}

	@Override
	public JsonObject apply(JsonObject original) {
		return Json.createObjectBuilder(original)
			.add("id", original.getJsonString("fileId"))
			.remove("_id")
			.remove("fileId")
			.build();
	}
}
