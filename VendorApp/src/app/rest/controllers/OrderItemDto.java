package app.rest.controllers;

public class OrderItemDto {
	
	private Long itemId;
	private int quantity;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderItemDto [itemId=" + itemId + ", quantity=" + quantity + "]";
	}
}
