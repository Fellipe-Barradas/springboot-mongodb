package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
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
			Query query = new Query().addCriteria(Criteria.where("email").is(student.getEmail()));
			List<Student> students = mongoTemplate.find(query, Student.class);
			if(students.size() > 1){
				throw new Exception("Too many students using this email: " + student.getEmail());
			}

			if(students.isEmpty()){
				studentRepository.insert(student);
			}else {
				throw new Exception("Student already exists: " + student.getEmail());
			}
		};
	}
}
