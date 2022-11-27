package com.bismus.springlab.service;

import com.bismus.springlab.exception.PersonByIdNotFoundException;
import com.bismus.springlab.exception.PersonByNameNotFoundException;
import com.bismus.springlab.exception.PersonTableIsEmptyException;
import com.bismus.springlab.model.Person;
import com.bismus.springlab.repository.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepositoryImpl personRepository;

    public List<Person> findAll(){
        if (personRepository.getAllPerson().isEmpty()){
            throw new PersonTableIsEmptyException();
        }
        return personRepository.getAllPerson();
    }

    public Person findById(int id){
        return personRepository.getPersonById(id)
                .orElseThrow(() -> new PersonByIdNotFoundException(id));
    }

//    public Person findByName(String name){
//        return personRepository.getPersonByName(name).
//                orElseThrow(() -> new PersonByNameNotFoundException(name));
//    }

    public List<Person> findByName(String name){
        if (personRepository.getAllPerson().isEmpty()){
            throw new PersonByNameNotFoundException(name);
        }
        return personRepository.getPersonByName(name);
    }
}
