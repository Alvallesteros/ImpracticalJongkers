package app.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Cafeteria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String description;

	@OneToMany(mappedBy="cafeteria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Vendor> vendors;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Cafeteria [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
