package app.components;

import app.entities.*;
import app.repositories.ItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import app.rest.controllers.OrderDto;
import app.rest.controllers.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
	
	public Order editOrder(long orderId, String status)
	{
		Order o = orderRepository.findById(orderId).orElse(null);
		o.setStatus(status);

		String orderCode = o.getOrderCode();



		return orderRepository.save(o);
	}
	
	public String viewOrder(long orderId)
	{
		// return summary of order (orderitems + price)
		return orderRepository.findById(orderId).toString();
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
