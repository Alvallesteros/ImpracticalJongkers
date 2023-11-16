package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Vendor {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String owner;
	
	@Column
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name="cafeteria_id")
	private Cafeteria cafeteria;

	@OneToMany(mappedBy="vendor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Item> items;

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCafeteria() {
		return cafeteria.getId();
	}

	public void setCafeteria(Cafeteria cafeteria) {
		this.cafeteria = cafeteria;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Vendor{" +
				"id=" + id +
				", name='" + name + '\'' +
				", owner='" + owner + '\'' +
				", description='" + description + '\'' +
				", cafeteria=" + cafeteria.getName() +
				'}';
	}

}
