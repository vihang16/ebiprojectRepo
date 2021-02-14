package com.ebi.uk;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import com.ebi.uk.controller.PersonController;
import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@WebMvcTest
public class EbiProjectIntegrationTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository mockRepository;
    
    @Before
    public void init() {
    	List<Person> persons = new ArrayList<>();
        Person person = new Person("Dumm1","lastDummy", 29);
        person.setId(1l);
        persons.add(person);
        when(mockRepository.findAll()).thenReturn(persons);
    }

    @WithMockUser("ADMIN")
    @Test
    public void testGetAllPersons() throws Exception {
    	   mockMvc.perform(get("/persons/all"))
          // .andDo(print())
           .andExpect(status().isOk());
           /*.andExpect(jsonPath("$.id", is(1)))
           .andExpect(jsonPath("$.firstName", is("Dumm1")))
           .andExpect(jsonPath("$.lastName", is("lastDummy")))
           .andExpect(jsonPath("$.age", is(29)));*/
    }
}
