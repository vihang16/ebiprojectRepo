package com.ebi.uk;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class EbiPersonTest {

	@Autowired
	private WebTestClient webTestClient;
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void saveUser() {
		Person ps=new Person();
		ps.setAge(22);
		ps.setFirstName("a");
		ps.setLastName("b");
		personRepository.deleteAll();
		
		webTestClient.post().uri("/persons/create")
				.contentType(MediaType.APPLICATION_JSON)
                 .body(Mono.just(ps), Person.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().valueEquals("Content-Type", MediaType.APPLICATION_JSON.toString())
				.expectBody()
				.jsonPath("$.age").isEqualTo(22);      
               
	}
}
