package com.ebi.uk.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/all")
    public Iterable<Person> getAllUser() {
        return personRepository.findAll();
    }
	
	@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person createUser( @RequestBody Person person) {
        return personRepository.save(person);
    }
	
	@PutMapping("/update/{id}")
    public Person updateUser( @PathVariable(value = "id") Long id, @RequestBody Person person) {
		//Person p =personRepository.findById(person.getId()).get();
		Person p = personRepository.findById(id)
				.orElseThrow();
		p.setAge(person.getAge());
		p.setFavoriteColor(person.getFavoriteColor());
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
        return personRepository.save(p);
    }

	@GetMapping("/{id}")
    public Person getUserById(@PathVariable(value = "id") long userId) {
        return personRepository.findById(userId).get();
    }
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {

	    Long personId = Long.parseLong(id);
	    personRepository.deleteById(personId);
	}
	@Bean
    RouterFunction<ServerResponse> helloRouterFunction() {
		
        RouterFunction<ServerResponse> routerFunction =
                RouterFunctions.route(RequestPredicates.path("/"),
                        serverRequest ->
                                ServerResponse.ok().body(Mono.just("Hello World!"), String.class));

        return routerFunction;
}
}
