package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import app.components.OrderComponent;
import app.entities.*;

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
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String editOrder(@FormParam("orderId") long orderId, @FormParam("status") String status) {
        return orderComponent.editOrder(orderId, status);
    }
}