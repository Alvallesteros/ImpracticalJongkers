package app.components;

import app.entities.*;
import app.repositories.*;
import app.rest.controllers.CafeteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DatabasePopulator {
	
	@Autowired
	private CafeteriaRepository cafRepo;

	@Autowired
	private VendorRepository vendorRepo;

	@Autowired
	private ItemRepository itemRepo;

	@PostConstruct
	private void init() {
		if (cafRepo.count() == 0) {
			// Cafeteria 1
			Cafeteria cafeteria1 = new Cafeteria();
			cafeteria1.setName("Cafeteria One");
			cafeteria1.setDescription("A cozy place with a variety of food options.");
			cafeteria1 = cafRepo.save(cafeteria1);


			// Cafeteria 2
			Cafeteria cafeteria2 = new Cafeteria();
			cafeteria2.setName("Cafeteria Two");
			cafeteria2.setDescription("An elegant space with a focus on healthy choices.");
			cafeteria2 = cafRepo.save(cafeteria2);

			// Vendor A for Cafeteria 1
			Vendor vendorA = new Vendor();
			vendorA.setCafeteria(cafeteria1);
			vendorA.setName("Delicious Delights");
			vendorA.setDescription("Offering a diverse range of mouth-watering sandwiches and snacks.");
			vendorA.setOwner("John Doe");
			vendorA = vendorRepo.save(vendorA);

			// Vendor B for Cafeteria 1
			Vendor vendorB = new Vendor();
			vendorB.setCafeteria(cafeteria1);
			vendorB.setName("Pizza Paradise");
			vendorB.setDescription("Serving hot and fresh pizzas with a variety of toppings.");
			vendorB.setOwner("Jane Smith");
			vendorB = vendorRepo.save(vendorB);

			// Vendor C for Cafeteria 2
			Vendor vendorC = new Vendor();
			vendorC.setCafeteria(cafeteria2);
			vendorC.setName("Salad Sensations");
			vendorC.setDescription("Specializing in fresh salads and wraps with a variety of dressings.");
			vendorC.setOwner("Michael Johnson");
			vendorC = vendorRepo.save(vendorC);

			// Vendor D for Cafeteria 2
			Vendor vendorD = new Vendor();
			vendorD.setCafeteria(cafeteria2);
			vendorD.setName("Organic Delights");
			vendorD.setDescription("Providing organic fruit options for a healthy snacking experience.");
			vendorD.setOwner("Emily Williams");
			vendorD = vendorRepo.save(vendorD);

			Item item1A = new Item();
			item1A.setName("Classic Chicken Sandwich");
			item1A.setPrice(7.99);
			item1A.setDescription("Grilled chicken, lettuce, and tomato on freshly baked bread.");
			item1A.setVendor(vendorA);
			item1A = itemRepo.save(item1A);

			Item item2A = new Item();
			item2A.setName("Vegetarian Wrap");
			item2A.setPrice(6.49);
			item2A.setDescription("A flavorful wrap filled with fresh vegetables and hummus.");
			item2A.setVendor(vendorA);
			item2A = itemRepo.save(item2A);

			Item item3A = new Item();
			item3A.setName("Turkey Club");
			item3A.setPrice(8.99);
			item3A.setDescription("Sliced turkey, bacon, lettuce, and mayo on toasted bread.");
			item3A.setVendor(vendorA);
			item3A = itemRepo.save(item3A);

			Item item4A = new Item();
			item4A.setName("Chips and Salsa");
			item4A.setPrice(3.99);
			item4A.setDescription("A side of crispy tortilla chips served with fresh salsa.");
			item4A.setVendor(vendorA);
			item4A = itemRepo.save(item4A);

			Item item1B = new Item();
			item1B.setName("Margherita Pizza");
			item1B.setPrice(11.99);
			item1B.setDescription("Classic pizza with tomato sauce, mozzarella, and fresh basil.");
			item1B.setVendor(vendorB);
			item1B = itemRepo.save(item1B);

			Item item2B = new Item();
			item2B.setName("Pepperoni Feast");
			item2B.setPrice(13.49);
			item2B.setDescription("Pizza loaded with pepperoni, cheese, and tomato sauce.");
			item2B.setVendor(vendorB);
			item2B = itemRepo.save(item2B);

			Item item3B = new Item();
			item3B.setName("Vegetarian Delight");
			item3B.setPrice(12.99);
			item3B.setDescription("A veggie-packed pizza with mushrooms, bell peppers, and olives.");
			item3B.setVendor(vendorB);
			item3B = itemRepo.save(item3B);

			Item item4B = new Item();
			item4B.setName("Garlic Knots");
			item4B.setPrice(4.99);
			item4B.setDescription("Soft, garlic-infused knots of bread served with marinara sauce.");
			item4B.setVendor(vendorB);
			item4B = itemRepo.save(item4B);

			// Items for Vendor C
			Item item1C = new Item();
			item1C.setName("Mango Tango Salad");
			item1C.setPrice(9.99);
			item1C.setDescription("A refreshing salad with mixed greens, mango slices, and citrus vinaigrette.");
			item1C.setVendor(vendorC);
			item1C = itemRepo.save(item1C);

			Item item2C = new Item();
			item2C.setName("Quinoa Power Bowl");
			item2C.setPrice(11.49);
			item2C.setDescription("A nutritious bowl with quinoa, roasted vegetables, and tahini dressing.");
			item2C.setVendor(vendorC);
			item2C = itemRepo.save(item2C);

			Item item3C = new Item();
			item3C.setName("Greek Gyro Wrap");
			item3C.setPrice(10.99);
			item3C.setDescription("A classic Greek wrap with seasoned gyro meat, tzatziki, and tomatoes.");
			item3C.setVendor(vendorC);
			item3C = itemRepo.save(item3C);

			Item item4C = new Item();
			item4C.setName("Fruit Infusion Smoothie");
			item4C.setPrice(5.99);
			item4C.setDescription("A delicious smoothie blend with a variety of fresh fruits.");
			item4C.setVendor(vendorC);
			item4C = itemRepo.save(item4C);

			Item item1D = new Item();
			item1D.setName("Organic Fruit Bowl");
			item1D.setPrice(8.99);
			item1D.setDescription("A bowl filled with a variety of organically grown fruits.");
			item1D.setVendor(vendorD);
			item1D = itemRepo.save(item1D);

			Item item2D = new Item();
			item2D.setName("Berry Bliss Parfait");
			item2D.setPrice(7.49);
			item2D.setDescription("A delightful parfait with layers of berries, yogurt, and granola.");
			item2D.setVendor(vendorD);
			item2D = itemRepo.save(item2D);

			Item item3D = new Item();
			item3D.setName("Avocado Toast");
			item3D.setPrice(9.99);
			item3D.setDescription("A trendy breakfast option with avocado slices on whole-grain toast.");
			item3D.setVendor(vendorD);
			item3D = itemRepo.save(item3D);

			Item item4D = new Item();
			item4D.setName("Detox Green Juice");
			item4D.setPrice(6.99);
			item4D.setDescription("A rejuvenating green juice made from kale, cucumber, and apple.");
			item4D.setVendor(vendorD);
			item4D = itemRepo.save(item4D);
		}
	}
}