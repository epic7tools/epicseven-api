package dev.stocky37.epic7;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject hello() {
		return Json.createObjectBuilder()
				.add("hello", "world")
				.build();
	}
}