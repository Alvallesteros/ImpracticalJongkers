package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserApp {

	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}
}