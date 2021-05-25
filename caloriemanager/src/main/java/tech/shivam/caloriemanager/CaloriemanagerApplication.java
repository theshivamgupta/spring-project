package tech.shivam.caloriemanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CaloriemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloriemanagerApplication.class, args);
	}

}
