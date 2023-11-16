package app.components;

import app.entities.*;
import app.repositories.ItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import app.rest.controllers.OrderDto;
import app.rest.controllers.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class OrderComponent {

	@Autowired
	OrderRepository	orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ItemRepository itemRepository;


	public Order newOrder(OrderDto orderDto) {
		/*
			Uses OrderDto and OrderItemDto.
			Finds existing customer and item(s), gives null if either aren't found.

			TODO: Refactoring (if possible)
		*/
		Order o = new Order();
		Double totalPrice = 0.0;

		o.setOrderCode("ORDER-" + generateRandomDigits(6));

		List<OrderItem> orderItems = new ArrayList<>();
		o = orderRepository.save(o);

		for (OrderItemDto oiDto : orderDto.getOrderItems()) {
			Long id = oiDto.getItemId();
			int qty = oiDto.getQuantity();

			Item item = itemRepository.findById(id).orElse(null);

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
	
	public String editOrder(long orderId, String status)
	{
		// edit status or cancel order
		return "ok";
	}
	
	public String viewOrder(long orderId)
	{
		// return summary of order (orderitems + price)
		return "ok";
	}
	private String generateRandomDigits(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			stringBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
		}

		return stringBuilder.toString();
	}
}
