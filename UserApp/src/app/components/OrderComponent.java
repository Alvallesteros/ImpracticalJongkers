package app.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.*;

import app.repositories.ItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import app.rest.controllers.*;

@Component
public class OrderComponent {

	@Autowired
	OrderRepository	orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	public Order newOrder(OrderDto orderDto) {
		/*
			Uses OrderDto and OrderItemDto.
			Finds existing customer and item(s), gives null if either aren't found.

			TODO: Refactoring (if possible)
		*/
		Order o = new Order();
		Customer customer =
				customerRepository.findById(orderDto.getCustomerId()).orElse(null);

		if (customer == null)
			throw new IllegalArgumentException("Customer not found");

		o.setOrderCode(
				String.format(
						"ORDER-%s-%s",
						customer.getLastName().toUpperCase(),
						generateRandomDigits(6))
		);

		o.setCustomer(customer);
		List<OrderItem> orderItems = new ArrayList<>();
		o = orderRepository.save(o);

		for (OrderItemDto oiDto : orderDto.getOrderItems()) {
			Long id = oiDto.getItemId();
			int qty = oiDto.getQuantity();

			Item item = itemRepository.findById(id).orElse(null);

			if (item == null)
				throw new IllegalArgumentException("Item not found");

			OrderItem oi = new OrderItem();

			oi.setOrder(o);
			oi.setItem(item);
			oi.setPrice(item.getPrice() * qty);
			oi.setQty(qty);

			orderItems.add(orderItemRepository.save(oi));
		}

		o.setOrderItems(orderItems);

		/*
		return "Thank you for ordering, "
				+ customer.getFirstName() + " "
				+ customer.getLastName()
				+ "! Your order code is "
				+ o.getOrderCode()
				+ " and your ordered items are "
				+ o.getOrderItems();
		*/
		return o;
	}
	
//	public String addToOrder(Long itemId, Long orderId, int quantity) {
//		Order o = orderRepository.getOne(orderId);
//		Item i = itemRepository.getOne(itemId);
//		double price = i.getPrice() * quantity;
//
//		OrderItem oi = new OrderItem();
//		oi.setItem(i);
//		oi.setOrder(o);
//		oi.setQty(quantity);
//		oi.setPrice(price);
//
//		oi = orderItemRepository.save(oi);
//
//		o.getOrderItems().add(oi);
//
//		double newPrice = orderRepository.findPrice(orderId);
//
//		return "Added to Order " + orderId + ", new price is " + newPrice;
//	}
	
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
