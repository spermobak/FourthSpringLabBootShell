package com.bismus.springlab.repository;

import com.bismus.springlab.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> getAllPerson();
    Optional<Person> getPersonById(int id);

    List<Person> getPersonByName(String name);


    void insertPerson(String name, int age);

    void updatePerson(int id, String name, int age);

    void deletePerson(int id);
}
