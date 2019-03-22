package dev.stocky37.epic7.resource;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/")
public class RootResource {

	@Inject
	HeroesResource heroesResource;

	@Inject
	StatsResource statsResource;


	@Path("heroes")
	public HeroesResource heroes() {
		return heroesResource;
	}

	@Path("stats")
	public StatsResource stats() {
		return statsResource;
	}
}
