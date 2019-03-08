package dev.stocky37.epic7;

import dev.stocky37.epic7.json.HeroTransform;
import dev.stocky37.epic7.json.Unwrapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

	@Inject
	@RestClient
	EpicSevenDbApi api;

	@GET
	@Path("heroes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject hello(@PathParam("id") String id) {
		return new Unwrapper()
				.andThen(arr -> arr.getJsonObject(0))
				.andThen(HeroTransform.getInstance())
				.apply(api.getHero(id));
	}
}