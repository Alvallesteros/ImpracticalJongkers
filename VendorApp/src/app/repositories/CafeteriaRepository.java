package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entities.Cafeteria;

@Repository
public interface CafeteriaRepository extends JpaRepository<Cafeteria, Long> {
	
	Cafeteria findByName(String name);
	List<Cafeteria> findAll();
	Long deleteById(int cafeteriaId);	
}