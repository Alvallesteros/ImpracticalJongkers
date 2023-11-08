package app.rest.controllers;

import java.util.List;
import app.entities.*;

public class OrderDto {
	
	private List<OrderItem> orderItems;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "OrderDto [orderItems=" + orderItems + "]";
	}
}
