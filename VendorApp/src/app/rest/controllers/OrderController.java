package app.rest.controllers;

import app.components.OrderComponent;
import app.components.dto.OrderDto;
import app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class OrderController {
	
	@Autowired
	OrderComponent orderComponent;
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order newOrder(OrderDto orderDto) {
		return orderComponent.newOrder(orderDto);
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Order addToOrder(@FormParam("orderCode") String orderCode,
							@FormParam("itemId") long itemId,
							@FormParam("qty") int qty) throws Exception {
        return orderComponent.addToOrder(orderCode, itemId, qty);
    }
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Order editOrder(@FormParam("orderId") Long orderId,
						   @FormParam("status") String status) throws Exception {
		return orderComponent.editOrder(orderId, status);
	}
	
	@GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/view")
    public String viewOrder(@QueryParam("orderId") Long orderId)
    {
    	return orderComponent.viewOrder(orderId);
    }

	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/delete")
	public String deleteOrder(@FormParam("orderId") Long orderId) {
		return orderComponent.deleteOrder(orderId);
	}
}