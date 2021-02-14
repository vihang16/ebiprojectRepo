package com.ebi.uk.repository;

import org.springframework.data.repository.CrudRepository;

import com.ebi.uk.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {


}
