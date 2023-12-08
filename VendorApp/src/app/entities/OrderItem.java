package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Item item;
	
	@Column
	@NotNull
	private int qty;
	
	@Column
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder() {
		return order.getId();
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order.getId() + ", qty=" + qty + ", price=" + price + "]";
	}

	@PreRemove
	private void preRemove() {
		// Remove this order item from the order before deletion
		if (order != null) {
			order.getOrderItems().remove(this);
			order.setTotalPrice(order.getTotalPrice() - this.price);
		}
	}
}
