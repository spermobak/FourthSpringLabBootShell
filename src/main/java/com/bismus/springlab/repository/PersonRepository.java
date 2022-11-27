package com.bismus.springlab.repository;

import com.bismus.springlab.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> getAllPerson();
    Optional<Person> getPersonById(int id);
//    Optional<Person> getPersonByName(String name);
    List<Person> getPersonByName(String name);
}
