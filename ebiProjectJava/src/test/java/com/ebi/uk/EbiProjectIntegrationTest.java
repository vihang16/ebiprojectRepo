package com.ebi.uk;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ebi.uk.entity.Person;
import com.ebi.uk.repository.PersonRepository;

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
    	   mockMvc.perform(get("/persons/all").with(user("admin").password("password")))
    	   
          // .andDo(print())
           .andExpect(status().isOk());
           /*.andExpect(jsonPath("$.id", is(1)))
           .andExpect(jsonPath("$.firstName", is("Dumm1")))
           .andExpect(jsonPath("$.lastName", is("lastDummy")))
           .andExpect(jsonPath("$.age", is(29)));*/
    }
}
