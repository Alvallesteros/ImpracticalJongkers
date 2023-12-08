package app.components;

import java.io.IOException;
import java.util.List;

import app.components.dto.ItemDto;
import app.components.dto.OrderReceivedDto;
import app.components.dto.ParamsDto;
import app.components.dto.VendorDto;
import app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.*;

import app.repositories.OrderRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;

@Component
public class OrderComponent {

	@Autowired
	OrderRepository	orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	Retrofit retrofit;

	@PostConstruct
	public void init() {
		retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:9998")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	public OrderReceivedDto newOrder(ParamsDto paramsDto) throws Exception {
		VendorIF caller = retrofit.create(VendorIF.class);
		Customer customer =
				customerRepository
				.findById(paramsDto.getCustomerId())
				.orElse(null);

		if (customer == null)
			throw new Exception("Customer doesn't exist!");

		try {
			Call<OrderReceivedDto> call = caller.order(paramsDto);
			Response<OrderReceivedDto> reply = call.execute();

			if (reply.isSuccessful()) {
				OrderReceivedDto order = reply.body();
				Order o = new Order();

				o.setDateOrdered(order.getDateOrdered());
				o.setOrderCode(order.getOrderCode());
				o.setTotalPrice(order.getTotalPrice());
				o.setStatus(order.getStatus());
				o.setCustomer(customer);

				orderRepository.save(o);

				return reply.body();
			}
			throw new Exception("Request failed: " + reply.code());
		} catch (Exception e) {
			throw new Exception(e);
		}
    }
	
	public String editOrder(String orderCode, String status)
	{
		Order o = orderRepository.findByOrderCode(orderCode);
		o.setStatus(status);
		orderRepository.save(o);
		return "Changed order status of " + orderCode;
	}
	
	public OrderReceivedDto viewOrder(String orderCode) throws IOException {
		VendorIF caller = retrofit.create(VendorIF.class);
		Call<OrderReceivedDto> call = caller.viewOrderDetails(orderCode);
		Response<OrderReceivedDto> resp = call.execute();

		return resp.body();
	}
	
//	private String generateRandomDigits(int length) {
//		Random random = new Random();
//		StringBuilder stringBuilder = new StringBuilder(length);
//
//		for (int i = 0; i < length; i++) {
//			stringBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
//		}
//
//		return stringBuilder.toString();
//	}

	public OrderReceivedDto addToOrder(String orderCode, long itemId, int qty) throws Exception {
		VendorIF caller = retrofit.create(VendorIF.class);
		Order order = orderRepository.findByOrderCode(orderCode);
		
		// TODO: add functionality for if order does not exist

		Call<OrderReceivedDto> call = caller.addToOrder(orderCode, itemId, qty);
		Response<OrderReceivedDto> resp = call.execute();

		order.setTotalPrice(resp.body().getTotalPrice());
		orderRepository.save(order);

		return resp.body();
	}
	
	public List<VendorDto> getAllVendors() throws Exception
	{
		VendorIF caller = retrofit.create(VendorIF.class);
		Call<List<VendorDto>> call = caller.viewVendors();
		Response<List<VendorDto>> resp = call.execute();
		return resp.body();
	}
	
	public List<ItemDto> viewItems(Long vendorId) throws Exception
	{
		VendorIF caller = retrofit.create(VendorIF.class);
		Call<List<ItemDto>> call = caller.viewItems(vendorId);
		Response<List<ItemDto>> resp = call.execute();
		return resp.body();
	}

	public Customer getCustomerDetails(String orderCode) {
		return orderRepository.findByOrderCode(orderCode).getCustomer();
	}
}
