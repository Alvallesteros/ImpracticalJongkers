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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cafeteria> viewCafeteria()
    {
        return cafComponent.viewCafeterias();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/remove")
    public String removeCafeteria(@FormParam("cafeteriaID") int cafId) {
        return cafComponent.removeCafeteria(cafId);
    }
}