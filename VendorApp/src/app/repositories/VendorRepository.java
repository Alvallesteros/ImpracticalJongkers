package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.*;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	List<Vendor> findAll();
	Long deleteById(int vendorId);
	List<Vendor> findAllByCafeteria(Cafeteria cafeteria);
}