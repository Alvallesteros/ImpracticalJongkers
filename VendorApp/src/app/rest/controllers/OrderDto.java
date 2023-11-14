package app.rest.controllers;

import java.util.List;
import app.entities.*;
import app.rest.controllers.OrderItemDto;

public class OrderDto {
	
	private Long customerId;
	
	private List<OrderItemDto> orderItems;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "OrderDto [orderItems=" + orderItems + "]";
	}
}
