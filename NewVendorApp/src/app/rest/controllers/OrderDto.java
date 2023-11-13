package app.rest.controllers;

import java.util.List;

public class OrderDto {
	
	private List<OrderItemDto> orderItems;

	private Long vendorId;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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
