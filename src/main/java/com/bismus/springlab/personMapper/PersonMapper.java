package com.bismus.springlab.personMapper;

import com.bismus.springlab.model.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Person(rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age")
        );
    }


}
