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
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@PostConstruct
	private void init() {
		System.out.println("Test...");
		List<Order> or = orderRepository.findByCustomer_Id(1L);
		System.out.println(or);
		if(customerRepository.count() == 0 
				&& orderRepository.count() == 0
				&& orderItemRepository.count() == 0
				&& itemRepository.count() == 0) {
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
			
			Item i1 = new Item();
			i1.setName("Pizza Margherita");
			i1.setPrice(12.99);
			itemRepository.save(i1);

			Item i2 = new Item();
			i2.setName("Spaghetti Carbonara");
			i2.setPrice(14.50);
			itemRepository.save(i2);

			Item i3 = new Item();
			i3.setName("Grilled Chicken Sandwich");
			i3.setPrice(8.99);
			itemRepository.save(i3);

			Item i4 = new Item();
			i4.setName("Caesar Salad");
			i4.setPrice(7.50);
			itemRepository.save(i4);

			Item i5 = new Item();
			i5.setName("Chocolate Brownie");
			i5.setPrice(4.99);
			itemRepository.save(i5);
			
			Customer customer1 = customerRepository.findById(1L).orElse(null); // Assuming customer IDs are assigned sequentially
			Customer customer2 = customerRepository.findById(2L).orElse(null);
			Customer customer3 = customerRepository.findById(3L).orElse(null);

			Item item1 = itemRepository.findById(1L).orElse(null); // Assuming item IDs are assigned sequentially
			Item item2 = itemRepository.findById(2L).orElse(null);

			Order order1 = new Order();
			order1.setCustomer(customer1);
			order1.setOrderCode("ORDER123");

			Order order2 = new Order();
			order2.setCustomer(customer2);
			order2.setOrderCode("ORDER456");

			Order order3 = new Order();
			order3.setCustomer(customer3);
			order3.setOrderCode("ORDER789");

			orderRepository.save(order1);
			orderRepository.save(order2);
			orderRepository.save(order3);

			// Sample Data for Order Items
			OrderItem orderItem1 = new OrderItem();
			orderItem1.setOrder(order1);
			orderItem1.setItem(item1);
			orderItem1.setQty(2);

			OrderItem orderItem2 = new OrderItem();
			orderItem2.setOrder(order2);
			orderItem2.setItem(item2);
			orderItem2.setQty(3);

			orderItemRepository.save(orderItem1);
			orderItemRepository.save(orderItem2);
		}
	}
}
