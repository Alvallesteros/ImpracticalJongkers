package app.rest.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.CafeteriaComponent;
import app.entities.*;

@Path("/cafeteria")
public class CafeteriaController {

	@Autowired
	CafeteriaComponent cafComponent;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cafeteria addCafeteria(CafeteriaDto cafeteriaDto)
	{
		return cafComponent.newCafeteria(cafeteriaDto);
	}
	
	@GET
	@Path("/all")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cafeteria> viewCafeterias()
	{
		return cafComponent.viewCafeterias();
	}

	@GET
	@Path("/{cafeteriaId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cafeteria viewCafeteria(@PathParam("cafeteriaId") Long cafeteriaId) {
		return cafComponent.viewCafeteria(cafeteriaId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/remove")
    public String removeCafeteria(@FormParam("cafeteriaID") int cafId) {
		return cafComponent.removeCafeteria(cafId);
    }
}