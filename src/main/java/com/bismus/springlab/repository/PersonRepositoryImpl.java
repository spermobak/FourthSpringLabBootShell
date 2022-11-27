package com.bismus.springlab.repository;

import com.bismus.springlab.personMapper.PersonMapper;
import com.bismus.springlab.model.Person;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private static final String GET_ALL_PERSONS = "Select * from person";
    private static final String GET_PERSON_BY_ID = "Select * from person where id =:id";
    private static final String GET_PERSON_BY_NAME = "Select * from person where name =:name";

    private final PersonMapper personMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonRepositoryImpl(PersonMapper personMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.personMapper = personMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAllPerson() {
        return jdbcTemplate.query(GET_ALL_PERSONS,new PersonMapper());
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        return jdbcTemplate.query(GET_PERSON_BY_ID,param,personMapper).stream().findFirst();
    }

//    @Override
//    public Optional<Person> getPersonByName(String name) {
//        var param = new MapSqlParameterSource();
//        param.addValue("name", name);
//        return jdbcTemplate.query(GET_PERSON_BY_NAME,param,personMapper).stream().findAny();
//    }

    @Override
    public List<Person> getPersonByName(String name) {
        var param = new MapSqlParameterSource();
        param.addValue("name", name);
        return jdbcTemplate.query(GET_PERSON_BY_NAME,param,personMapper);
    }
}
