package dev.stocky37.epic7;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public interface EpicSevenDbApi {


	@GET
	@Path("/hero")
	JsonObject getHeroes();

	@GET
	@Path("/hero/{id}")
	JsonObject getHero(@PathParam("id") String id);
}


