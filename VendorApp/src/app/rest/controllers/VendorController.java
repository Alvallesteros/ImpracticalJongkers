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
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/remove")
    public String removeVendor(@FormParam("VendorID") int vendorId) {
		return vendorComponent.removeVendor(vendorId);
    }
}