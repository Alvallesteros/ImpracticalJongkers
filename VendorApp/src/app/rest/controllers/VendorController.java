package app.rest.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.VendorComponent;
import app.entities.*;

@Path("/vendor")
public class VendorController {

    @Autowired
    VendorComponent vendorComponent;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Vendor addVendor(VendorDto vendorDto)
    {
        return vendorComponent.newVendor(vendorDto);
    }

    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vendor> viewVendors()
    {
        return vendorComponent.viewVendors();
    }

    @GET
    @Path("/{vendorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getVendorItems(@PathParam("vendorId") Long vendorId) {
        return vendorComponent.viewVendorItems(vendorId);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/remove")
    public String removeVendor(@FormParam("VendorID") int vendorId) {
        return vendorComponent.removeVendor(vendorId);
    }
}