package app.components.dto;

import java.util.Date;
import java.util.List;

public class OrderReceivedDto {
	
	private String orderCode;
	private List<OrderItemDetailDto> orderItems;

	private Date dateOrdered;

	private String status;

	private Double totalPrice;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<OrderItemDetailDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDetailDto> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderReceivedDto{" +
				"orderCode='" + orderCode + '\'' +
				", orderItems=" + orderItems +
				", dateOrdered=" + dateOrdered +
				", status='" + status + '\'' +
				'}';
	}
}
