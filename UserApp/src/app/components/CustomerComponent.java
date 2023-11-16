package app.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Customer;
import app.entities.Order;

import app.repositories.CustomerRepository;
import app.repositories.OrderRepository;
import app.rest.controllers.CustomerDto;

@Component
public class CustomerComponent {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository	orderRepository;
	
	public Customer newCustomer(CustomerDto customerDto) {
		Customer c = new Customer();
		
		c.setContactNo(customerDto.getPhoneNumber());
		c.setEmailAddress(customerDto.getEmail());
		c.setFirstName(customerDto.getFirstName());
		c.setLastName(customerDto.getLastName());
		
		c = customerRepository.save(c);
		
		return c;
	}
	
	public String removeCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
		return "Deleted Customer " + customerId;
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
	}
}
