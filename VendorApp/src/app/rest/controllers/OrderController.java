package app.rest.controllers;

import app.components.OrderComponent;
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
	
	
//	@POST
//	@Path("/add")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String addItem(@FormParam("orderId") long orderId, @FormParam("itemId") long itemId, @FormParam("quantity") int quantity) {
//        return orderComponent.addToOrder(itemId, orderId, quantity);
//    }
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Order addToOrder(@FormParam("orderCode") String orderCode,
							@FormParam("itemId") long itemId,
							@FormParam("qty") int qty) throws Exception {
        return orderComponent.addToOrder(orderCode, itemId, qty);
    }
}