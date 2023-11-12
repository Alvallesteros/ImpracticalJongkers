package app.rest.controllers;

import java.util.List;
import app.entities.*;
import app.rest.controllers.OrderItemDto;

public class OrderDto {
	
	private String orderCode;
	private Customer customer;
	
	private List<OrderItemDto> orderItems;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
