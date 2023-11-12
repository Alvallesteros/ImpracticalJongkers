package app.components;

import java.util.List;

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
	
	public Order newOrder(OrderDto orderDto) {
		Order o = new Order();
		o.setOrderCode(orderDto.getOrderCode());
		o.setCustomer(orderDto.getCustomer());
		o.setOrderItems(orderDto.getOrderItems());
		
		o = orderRepository.save(o);
		
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
}
