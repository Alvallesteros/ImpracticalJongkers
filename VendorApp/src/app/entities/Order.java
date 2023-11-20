package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String orderCode;
	
	@OneToMany(mappedBy="order",
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER,
			orphanRemoval = true)
	private List<OrderItem> orderItems;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrdered;

	@Column
	private String status;

	@Column
	private Double totalPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
	}

	public void removeOrderItem(OrderItem orderItem) {
		this.orderItems.remove(orderItem);
		orderItem.setOrder(null); // Remove the bidirectional relationship
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
		return "Order{" +
				"id=" + id +
				", orderCode='" + orderCode + '\'' +
				", orderItems=" + orderItems +
				", dateOrdered=" + dateOrdered +
				", status='" + status + '\'' +
				", totalPrice=" + totalPrice +
				'}';
	}
}
