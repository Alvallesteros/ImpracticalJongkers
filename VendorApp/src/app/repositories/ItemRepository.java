package app.repositories;

import app.entities.Item;
import app.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Item findByName(String name);
	Long deleteById(int itemId);
	List<Item> findByVendor(Vendor v);
}