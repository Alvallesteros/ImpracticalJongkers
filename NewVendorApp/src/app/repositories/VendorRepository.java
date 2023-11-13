package app.repositories;

import app.entities.Cafeteria;
import app.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	List<Vendor> findAll();
	Long deleteById(int vendorId);
	List<Vendor> findAllByCafeteria(Cafeteria cafeteria);
}