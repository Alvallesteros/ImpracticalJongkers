package app.components;

import app.components.dto.CustomerContactDto;
import app.entities.*;
import app.repositories.ItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import app.components.dto.OrderDto;
import app.components.dto.OrderItemDto;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

@Component
public class OrderComponent {

	@Autowired
	OrderRepository	orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ItemRepository itemRepository;

	Retrofit retrofit_orderapp;
	Retrofit retrofit_userapp;

	@PostConstruct
	public void init() {
		  retrofit_orderapp = new Retrofit.Builder()
				  .baseUrl("http://localhost:9997")
				  .addConverterFactory(GsonConverterFactory.create())
				  .build();

		  retrofit_userapp = new Retrofit.Builder()
				  .baseUrl("http://localhost:9999")
				  .addConverterFactory(GsonConverterFactory.create())
				  .build();
	}

	public Order newOrder(OrderDto orderDto) {
		/*
			Uses OrderDto and OrderItemDto.
			Finds existing customer and item(s), gives null if either aren't found.

			TODO: Refactoring (if possible)
		*/
		Order o = new Order();

		Double totalPrice = 0.0;
		System.out.println(orderDto);
		System.out.println(orderDto.getOrderItems());
		System.out.println(
				itemRepository.findById(orderDto.getOrderItems().get(0).getItemId())
		);

		o.setOrderCode("ORDER-" + generateRandomDigits(6));

		List<OrderItem> orderItems = new ArrayList<>();
		List<OrderItemDto> orderItemDtoList = orderDto.getOrderItems();
		o = orderRepository.save(o);
//		orderRepository.flush();
//	    o = orderRepository.findById(o.getId()).orElse(null);

		for (OrderItemDto oiDto : orderItemDtoList) {
			System.out.println(oiDto.toString());
			Long id = oiDto.getItemId();
			int qty = oiDto.getQuantity();

			Item item = itemRepository.findById(id).orElse(null);

			System.out.println("Item: " + item);

			if (item == null)
				throw new IllegalArgumentException("Item not found");

			if (!item.getVendor().equals(orderDto.getVendorId()))
				throw new IllegalArgumentException("This vendor doesn't sell this item.");

			if (!item.isInStock())
				throw new IllegalArgumentException("Item is out of stock.");

			OrderItem oi = new OrderItem();

			oi.setOrder(o);
			oi.setItem(item);
			oi.setPrice(item.getPrice() * qty);
			oi.setQty(qty);
			totalPrice += item.getPrice() * qty;

			orderItems.add(orderItemRepository.save(oi));
		}

		o.setOrderItems(orderItems);
		o.setDateOrdered(new java.util.Date());
		o.setStatus("ORDERED");
		o.setTotalPrice(totalPrice);

		return orderRepository.save(o);
	}
	
	public Order addToOrder(String orderCode, Long itemId, int quantity) throws Exception {
		Order o = orderRepository.findByOrderCode(orderCode);
		Item i = itemRepository.findById(itemId).orElse(null);

		if (o == null)
			throw new Exception("Order not found.");

		double price = i.getPrice() * quantity;


		OrderItem oi = new OrderItem();
		oi.setItem(i);
		oi.setOrder(o);
		oi.setQty(quantity);
		oi.setPrice(price);

		oi = orderItemRepository.save(oi);

		o.addOrderItem(oi);
		o.setTotalPrice(o.getTotalPrice() + price);

		return orderRepository.save(o);
	}
	
	public Order editOrder(String orderCode, String status) throws Exception {
		Order o = orderRepository.findByOrderCode(orderCode);
		o.setStatus(status);

		String message = "Hi! Your order " + orderCode + " is now marked as " + status;


		try {
			UserAppIF caller_user = retrofit_userapp.create(UserAppIF.class);
			Call<CustomerContactDto> call_user = caller_user.getCustomerDetails(orderCode);
			Response<CustomerContactDto> resp_user = call_user.execute();

			String contactNo = resp_user.body().getContactNo();
			System.out.println("Sending update to " + contactNo);

			OrderAppIF caller_order = retrofit_orderapp.create(OrderAppIF.class);
			Call<ResponseBody> call_order = caller_order.sendMessage(contactNo, message);
			Response<ResponseBody> resp_order = call_order.execute();

			UserAppIF caller_user2 = retrofit_userapp.create(UserAppIF.class);
			Call<ResponseBody> call_user_2 = caller_user2.editOrderStatus(orderCode,
																		  status);
			Response<ResponseBody> resp_user_2 = call_user_2.execute();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return orderRepository.save(o);
	}

	public Order viewOrder(String orderCode)
	{
		Order o = orderRepository.findByOrderCode(orderCode);
		return o;
	}

	public List<Order> viewOrdersByStatus(String orderStatus)
	{
		List<Order> o = orderRepository.findByStatus(orderStatus);
		return o;
	}

	private String generateRandomDigits(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			stringBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
		}

		return stringBuilder.toString();
	}

    public String deleteOrder(Long orderId) {
		Order o = orderRepository.findById(orderId).orElse(null);
		if (o != null) {
			Iterator<OrderItem> iterator = o.getOrderItems().iterator();
			while (iterator.hasNext()) {
				OrderItem orderItem = iterator.next();
				iterator.remove(); // Use the iterator to safely remove the element from the list
				// Optionally, you can also explicitly remove the bidirectional relationship
				orderItem.setOrder(null);
				orderItemRepository.delete(orderItem); // Assuming orderItemRepository is your JPA repository for OrderItem
			}

			orderRepository.delete(o);
			return "Deleted order " + orderId;
		}
        return null;
    }
}
