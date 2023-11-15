package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.components.OrderReceivedDto;
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
}