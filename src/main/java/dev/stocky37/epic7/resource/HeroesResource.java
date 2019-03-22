package dev.stocky37.epic7.resource;

import dev.stocky37.epic7.core.HeroService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class HeroesResource {
	@Inject
	HeroService service;

	@GET
	public JsonArray getHeroes() {
		return service.getHeroes();
	}

	@GET
	@Path("{id}")
	public JsonObject getHero(@PathParam("id") String id) {
		return service.getHero(id);
	}
}