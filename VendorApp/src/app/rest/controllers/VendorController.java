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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vendor> viewVendors(@FormParam("name") String name)
    {
        if (name != "") {
            return vendorComponent.cafeteriaFilter(name);
        }
        return vendorComponent.viewVendors();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/remove")
    public String removeVendor(@FormParam("VendorID") int vendorId) {
        return vendorComponent.removeVendor(vendorId);
    }
}