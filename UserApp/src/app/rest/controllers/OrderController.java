package app.rest.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import app.components.OrderReceivedDto;
import app.components.VendorDto;
import app.components.ItemDto;
import app.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import app.components.OrderComponent;

@Path("/order")
public class OrderController {
	
	@Autowired
	OrderComponent orderComponent;
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderReceivedDto newOrder(ParamsDto paramsDto) throws Exception {
		return orderComponent.newOrder(paramsDto);
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderReceivedDto addItem(@FormParam("orderCode") String orderCode,
									@FormParam("itemId") long itemId,
									@FormParam("qty") int qty) throws Exception {
        return orderComponent.addToOrder(orderCode, itemId, qty);
    }
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String editOrder(@FormParam("orderId") long orderId, @FormParam("status") String status) {
        return orderComponent.editOrder(orderId, status);
    }
	
	@GET
	@Path("/vendors")
    @Produces(MediaType.APPLICATION_JSON)
	public List<VendorDto> viewVendors() throws Exception
	{
		return orderComponent.getAllVendors();
	}
	
	@GET
	@Path("/vendors/{vendorId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ItemDto> viewItems(@PathParam("vendorId") Long vendorId) throws Exception
	{
		return orderComponent.viewItems(vendorId);
	}

	@GET
	@Path("/customerdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerOfOrder(@QueryParam("orderCode") String orderCode) {
		return orderComponent.getCustomerDetails(orderCode);
	}
}