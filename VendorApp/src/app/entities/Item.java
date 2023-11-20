package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor_id")
	private Vendor vendor;

	@Column
	private String description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getVendor() {
		return vendor.getId();
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}