package com.bismus.springlab.repository;

import com.bismus.springlab.personMapper.PersonMapper;
import com.bismus.springlab.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;

@Repository
@EnableTransactionManagement
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {


    private static final String SQL_GET_ALL_PERSONS = "Select * from person";
    private static final String SQL_GET_PERSON_BY_ID = "Select * from person where id =:id";
    private static final String SQL_GET_PERSON_BY_NAME = "Select * from person where name =:name";
    private static final String SQL_INSERT_PROFILE =
            "Insert into person (name, age) values (:name, :age)";

    private static final String SQL_UPDATE_PROFILE =
            "Update person set name = :name, age = :age where id = :id";

    private static final String SQL_DELETE_PROFILE = "Delete from person where id = :id";
    private final PersonMapper personMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Person> getAllPerson() {
        return jdbcTemplate.query(SQL_GET_ALL_PERSONS,new PersonMapper());
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        return jdbcTemplate.query(SQL_GET_PERSON_BY_ID,param,personMapper).stream().findFirst();
    }

    @Override
    public List<Person> getPersonByName(String name) {
        var param = new MapSqlParameterSource();
        param.addValue("name", name);
        return jdbcTemplate.query(SQL_GET_PERSON_BY_NAME,param,personMapper);
    }

    @Override
    public void insertPerson(String name, int age) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("age", age);
        jdbcTemplate.update(SQL_INSERT_PROFILE, params);
    }

    @Override
    public void updatePerson(int id, String name, int age) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", name);
        params.addValue("age", age);
        jdbcTemplate.update(SQL_UPDATE_PROFILE, params);
    }

    @Override
    public void deletePerson(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(SQL_DELETE_PROFILE, params);

    }
}
