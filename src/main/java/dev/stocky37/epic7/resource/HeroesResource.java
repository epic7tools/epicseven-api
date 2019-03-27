package dev.stocky37.epic7.resource;

import dev.stocky37.epic7.core.HeroService;
import dev.stocky37.epic7.json.EquipInput;
import dev.stocky37.epic7.json.StatsJsonTransform;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	@GET
	@Path("{id}/stats")
	public JsonObject getHeroStats(
		@PathParam("id") String id,
		@QueryParam("stars") @DefaultValue("5") int stars,
		@QueryParam("level") @DefaultValue("0") int level,
		@QueryParam("awakening") @DefaultValue("0") int awakening
	) {
		return StatsJsonTransform.instance().apply(service.getAwakenedStats(id, stars, level, awakening));
	}

	@POST
	@Path("{id}/equip")
	public JsonObject equipHero(@PathParam("id") String id, EquipInput input) {
		return service.equipHero(id, input);
	}
}