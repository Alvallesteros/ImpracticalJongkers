package app.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.*;
import app.repositories.*;
import app.rest.controllers.CustomerDto;
import app.rest.controllers.OrderItemDto;
import app.rest.controllers.ParamsDto;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class DatabasePopulator {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository	orderRepository;
	
	Retrofit retrofit;
	
	@PostConstruct
	private void init() throws Exception {
		List<Order> or = orderRepository.findByCustomer_Id(1L);
		System.out.println(or);
		if (customerRepository.count() == 0 && orderRepository.count() == 0) {
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
			
			retrofit = new Retrofit.Builder()
					.baseUrl("http://localhost:9998")
					.addConverterFactory(GsonConverterFactory.create())
					.build();
			
			// populating orders
			OrderItemDto i1 = new OrderItemDto();
			i1.setItemId(1L);
			i1.setQuantity(1);
			OrderItemDto i2 = new OrderItemDto();
			i2.setItemId(2L);
			i2.setQuantity(2);
			List<OrderItemDto> l1 = new ArrayList<>();
			l1.add(i1);
			l1.add(i2);
			ParamsDto o1 = new ParamsDto();
			o1.setCustomerId(1L);
			o1.setOrderItems(l1);
			o1.setVendorId(1L);
			
			VendorIF caller = retrofit.create(VendorIF.class);
			Call<OrderReceivedDto> call = caller.order(o1);
			Response<OrderReceivedDto> reply = call.execute();
			OrderReceivedDto order1 = reply.body();
			makeOrder(order1, o1);
			
			OrderItemDto i3 = new OrderItemDto();
			i3.setItemId(5L);
			i3.setQuantity(3);
			List<OrderItemDto> l2 = new ArrayList<OrderItemDto>();
			l2.add(i3);
			ParamsDto o2 = new ParamsDto();
			o2.setCustomerId(2L);
			o2.setOrderItems(l2);
			o2.setVendorId(2L);
			call = caller.order(o2);
			reply = call.execute();
			OrderReceivedDto order2 = reply.body();
			makeOrder(order2, o2);

			OrderItemDto i4 = new OrderItemDto();
			i4.setItemId(9L);
			i4.setQuantity(1);
			OrderItemDto i5 = new OrderItemDto();
			i5.setItemId(10L);
			i5.setQuantity(2);
			List<OrderItemDto> l3 = new ArrayList<OrderItemDto>();
			l3.add(i4);
			l3.add(i5);
			ParamsDto o3 = new ParamsDto();
			o3.setCustomerId(3L);
			o3.setOrderItems(l3);
			o3.setVendorId(3L);
			call = caller.order(o3);
			reply = call.execute();
			OrderReceivedDto order3 = reply.body();
			makeOrder(order3, o3);
		}
		
	}
	
	private void makeOrder(OrderReceivedDto order, ParamsDto paramsDto) {
		Order o = new Order();
		o.setDateOrdered(order.getDateOrdered());
		o.setOrderCode(order.getOrderCode());
		o.setTotalPrice(order.getTotalPrice());
		o.setStatus(order.getStatus());
		o.setCustomer(customerRepository.getOne(paramsDto.getCustomerId()));
		orderRepository.save(o);
	}
}
