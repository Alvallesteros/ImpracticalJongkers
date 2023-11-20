package app.repositories;

import app.entities.Cafeteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeteriaRepository extends JpaRepository<Cafeteria, Long> {
	
	Cafeteria findByName(String name);
	List<Cafeteria> findAll();
}