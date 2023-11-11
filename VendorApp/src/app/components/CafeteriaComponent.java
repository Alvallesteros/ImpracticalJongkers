package app.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Cafeteria;
import app.rest.controllers.CafeteriaDto;
import app.repositories.*;

@Component
public class CafeteriaComponent {
	
	@Autowired
	private CafeteriaRepository cafRepo;
	
	public Cafeteria newCafeteria(CafeteriaDto cafeteriaDto)
	{
		Cafeteria c = new Cafeteria();
		
		c.setName(cafeteriaDto.getName());
		c.setDescription(cafeteriaDto.getDescription());
		
		c = cafRepo.save(c);
		
		return c;
	}
	
	public List<Cafeteria> viewCafeterias() {
		
		return cafRepo.findAll();
		
	}
	
	public String removeCafeteria(int cafeteriaId) {
		return "Deleted Cafeteria " + cafRepo.deleteById(cafeteriaId);
	}
	
}