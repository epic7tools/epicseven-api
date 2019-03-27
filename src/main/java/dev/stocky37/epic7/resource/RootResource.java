package dev.stocky37.epic7.resource;

import dev.stocky37.epic7.json.EquipInput;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
public class RootResource {

	@Inject
	HeroesResource heroesResource;

	@Path("heroes")
	public HeroesResource heroes() {
		return heroesResource;
	}

	@POST
	@Path("test")
	public JsonObject test(EquipInput data) {
		System.err.println(data);
		System.err.println(data.getAppliedGearSets());
		return Json.createObjectBuilder().build();
	}
}
