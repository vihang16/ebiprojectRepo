package com.ebi.uk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;

@SpringBootApplication
public class EbiProjectJavaApplication {
	
	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(EbiProjectJavaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			personRepository.save(new Person("Dumm1", "dumm2",32));
			personRepository.save(new Person("Vihang", "Shah", 29));
        };
	}
}
