package app.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.*;

import app.repositories.ItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import app.repositories.VendorRepository;
import app.repositories.CafeteriaRepository;
import app.rest.controllers.*;

@Component
public class OrderComponent {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private CafeteriaRepository cafRepo;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	public Order newOrder(OrderDto orderDto) {
//		Double totalPrice = 0.0;
		System.out.println(orderDto.toString());
		Order o = new Order();

		List<OrderItem> orderItems = new ArrayList<>();

		orderRepository.save(o);

		for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
			Long id = orderItemDto.getItemId();
			int qty = orderItemDto.getQuantity();

			Item item = itemRepository.findById(id).orElse(null);

			if (item == null)
				throw new IllegalArgumentException("No item");

			OrderItem oi = new OrderItem();

			oi.setOrder(o);
			oi.setItem(item);
			oi.setPrice(item.getPrice() * qty);
			oi.setQty(qty);

			orderItems.add(orderItemRepository.save(oi));
		}

		o.setOrderItems(orderItems);
		o.setOrderCode(orderDto.getOrderUUID());
		o.setStatus("NA");
		o.setDateOrdered(new java.util.Date());
//		o.setTotalPrice(totalPrice);
		return orderRepository.save(o);
	}



}