package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.components.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;

import app.components.CustomerComponent;
import app.entities.*;

@Path("/customer")
public class CustomerController {

	@Autowired
	CustomerComponent customerComponent;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer newCustomer(CustomerDto customerDto)
	{
		return customerComponent.newCustomer(customerDto);
	}
	
	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeCustomer(@FormParam("customerId") Long customerId)
	{
		return customerComponent.removeCustomer(customerId);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> viewCustomer()
	{
		return customerComponent.getAllCustomers();
	}
	
	@GET
	@Path("/{customerId}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getCustomerOrders(@PathParam("customerId") Long customerId) {
        // Implement logic to fetch and return the orders of the customer with the specified ID
        return customerComponent.getCustomerOrders(customerId);
    }
}
