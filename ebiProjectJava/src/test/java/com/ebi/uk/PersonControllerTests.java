package com.ebi.uk;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PersonControllerTests {

    @MockBean
    private PersonRepository mockRepository;
	
    @Autowired
    private TestRestTemplate restTemplate;

    
    @BeforeEach
    public void init() {
    	List<Person> list = new ArrayList<>();
        Person p1 = new Person("dumm1", "lastName1",22);
        Person p2 = new Person("dumm2", "lastName2",32);
        p1.setId(1l);
        list.add(p2);
        list.add(p1);
        when(mockRepository.findAll()).thenReturn(list);
        when(mockRepository.findById(1l)).thenReturn(Optional.of( p1 ));
        when(mockRepository.save(any(Person.class))).thenAnswer(invocation -> {
        	  Person person = invocation.getArgument(0);
        	  // I assume you have an id field
        	  person.setId(42L);
        	  return person;
        	});
    }

    
    @Test
    public void createPerson() throws Exception {
    Person  p = new Person("dummy1", "dumm2", 11);   
    HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Person> httpEntity = new HttpEntity<Person>(p,headers);
   ResponseEntity<Person> response = restTemplate
            .withBasicAuth("user", "password")
            .postForEntity("/persons/create", httpEntity, Person.class);

    //ResponseEntity<Person> response = restTemplate.postForEntity(new URI("http://localhost:8080/persons/create"), httpEntity, Person.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    
    assertEquals(Long.valueOf("42"), response.getBody().getId());

    }
    
    @Test
    public void getPersonsBadReq() throws Exception {
    
    ResponseEntity<String> response = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity("/persons/all", String.class);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    //assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }
    
    @Test
    public void getPersonsGoodReq() throws Exception {	 
    ResponseEntity<Person[]> response = restTemplate
            .withBasicAuth("admin", "password")
            .getForEntity("/persons/all", Person[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody().length, 2);
    }
    
    @Test
    public void deleteReq() throws Exception {
    
     ResponseEntity<Void> response = restTemplate
    		 						.withBasicAuth("admin", "password")
    		 						.exchange("/persons/delete/1", HttpMethod.DELETE, null, Void.class);
           // .delete("/persons/delete/1");
    assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    
    @Test
    public void putReqTest() throws Exception {
    Person p1 = new Person( "l1", "nam", 22);
    p1.setId(1l);
    p1.setFavoriteColor("green");
    HttpHeaders headers = new HttpHeaders();
   	headers.setContentType(MediaType.APPLICATION_JSON);
    when(mockRepository.findById(1l)).thenReturn(Optional.of( p1 ));
    
    HttpEntity<Person> httpEntity = new HttpEntity<Person>(p1, headers);
     ResponseEntity<Person> response = restTemplate
    		 						.withBasicAuth("admin", "password")
    		 						
    		 						.exchange("/persons/update/1", HttpMethod.PUT, httpEntity, Person.class);
           // .delete("/persons/delete/1");
    assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
