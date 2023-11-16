package app.components;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.*;
import app.repositories.*;
import app.rest.controllers.CustomerDto;

@Component
public class DatabasePopulator {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository	orderRepository;
	
	@PostConstruct
	private void init() {
		System.out.println("Test...");
		List<Order> or = orderRepository.findByCustomer_Id(1L);
		System.out.println(or);
		if(customerRepository.count() == 0 
				&& orderRepository.count() == 0) {
			Customer c1 = new Customer();
			c1.setContactNo("12345367890");
			c1.setEmailAddress("john.doe@example.com");
			c1.setFirstName("John");
			c1.setLastName("Doe");
			customerRepository.save(c1);

			Customer c2 = new Customer();
			c2.setContactNo("98762543210");
			c2.setEmailAddress("alice.smith@example.com");
			c2.setFirstName("Alice");
			c2.setLastName("Smith");
			customerRepository.save(c2);

			Customer c3 = new Customer();
			c3.setContactNo("55516234567");
			c3.setEmailAddress("robert.johnson@example.com");
			c3.setFirstName("Robert");
			c3.setLastName("Johnson");
			customerRepository.save(c3);

			Customer c4 = new Customer();
			c4.setContactNo("88847779999");
			c4.setEmailAddress("sarah.wilson@example.com");
			c4.setFirstName("Sarah");
			c4.setLastName("Wilson");
			customerRepository.save(c4);

			Customer c5 = new Customer();
			c5.setContactNo("11122243333");
			c5.setEmailAddress("david.brown@example.com");
			c5.setFirstName("David");
			c5.setLastName("Brown");
			customerRepository.save(c5);
		}
	}
}
