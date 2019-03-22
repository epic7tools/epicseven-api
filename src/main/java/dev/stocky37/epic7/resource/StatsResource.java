package dev.stocky37.epic7.resource;

import dev.stocky37.epic7.core.StatsService;
import dev.stocky37.epic7.repr.CalculateStatsInput;
import dev.stocky37.epic7.repr.CalculateStatsOutput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class StatsResource {

	@Inject
	StatsService service;

	@POST
	@Path("calculate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CalculateStatsOutput calculate(CalculateStatsInput input) {
		return service.calculate(input);
	}
}
