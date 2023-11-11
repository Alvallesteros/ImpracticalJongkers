package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cafeteria> viewCafeteria()
	{
		return cafComponent.viewCafeterias();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/remove")
    public String removeCafeteria(@FormParam("cafeteriaID") int cafId) {
		return cafComponent.removeCafeteria(cafId);
    }
}