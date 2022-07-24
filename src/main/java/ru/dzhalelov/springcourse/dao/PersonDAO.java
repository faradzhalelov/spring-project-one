package ru.dzhalelov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dzhalelov.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index()
    {
        return jdbcTemplate.query
                (
                        "SELECT * FROM person",
                        new BeanPropertyRowMapper<>(Person.class)
                );
    }

    public Person show(int id)
    {
        return jdbcTemplate.query
                (
                        "SELECT * FROM person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)
                ).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name)
    {
        return jdbcTemplate.query
                (
                        "SELECT * FROM person WHERE name=?", new Object[]{name},
                        new BeanPropertyRowMapper<>(Person.class)
                ).stream().findAny();
    }

    public void save(Person person)
    {
        jdbcTemplate.update
                (
                "INSERT INTO person(name, birth_date) VALUES(?, ?)",
                person.getName(), person.getBirthDate()
                );
    }

    public void update(int id, Person updatedPerson)
    {
        jdbcTemplate.update
                (
                        "UPDATE person SET name=?, birth_date=? WHERE id=?",
                        updatedPerson.getName(),
                        updatedPerson.getBirthDate(),
                        id
                );
    }

    public void delete(int id)
    {
        jdbcTemplate.update
                (
                        "DELETE FROM person WHERE id=?",
                        id
                );
    }

}
