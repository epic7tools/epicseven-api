package dev.stocky37.epic7.json;


import javax.json.Json;
import javax.json.JsonObject;
import java.util.function.Function;

public class HeroTransform implements Function<JsonObject, JsonObject> {

  private static HeroTransform ourInstance = new HeroTransform();

  public static HeroTransform getInstance() {
    return ourInstance;
  }

  private HeroTransform() {}

  @Override
  public JsonObject apply(JsonObject original) {
    System.out.println(original.toString());
    return Json.createObjectBuilder(original)
        .add("id", original.getJsonString("fileId"))
        .remove("_id")
        .remove("fileId")
        .build();
  }
}
