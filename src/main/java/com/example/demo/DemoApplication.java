package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository) {
		return args -> {
			Address address = new Address("Brasil", "Teresina", "552");
			Student student = new Student(
					"Luis",
					"Fellipe",
					"email@gmail.com",
					Gender.MALE,
					address,
					List.of("História", "Matemática"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			studentRepository.insert(student);
		};
	}
}
