package ru.dzhalelov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dzhalelov.springcourse.models.Book;
import ru.dzhalelov.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query
                (
                "SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class)
                );
    }

    public Book show(int bookId) {
        return jdbcTemplate.query
                (
                        "SELECT * FROM book WHERE book_id=?",
                        new BeanPropertyRowMapper<>(Book.class),
                        bookId
                ).stream().findAny().orElse(null);
    }

    public void save(Book book)
    {
        jdbcTemplate.update
                (
                        "INSERT INTO book(name, author, year) VALUES(?, ?, ?)",
                        book.getName(),
                        book.getAuthor(),
                        book.getYear()
                );
    }

    public void update(int bookId, Book updatedBook)
    {
        jdbcTemplate.update
                (
                        "UPDATE book SET name=?, author=?, year=? WHERE book_id=?",
                        updatedBook.getName(),
                        updatedBook.getAuthor(),
                        updatedBook.getYear(),
                        bookId
                );
    }

    public void delete(int bookId)
    {
        jdbcTemplate.update
                (
                        "DELETE FROM book WHERE book_id=?",
                        bookId
                );
    }

    public Optional<Person> getBookOwner(int bookId)
    {
        return jdbcTemplate.query
                        (
                                "SELECT Person.* FROM Book " +
                                        "JOIN Person ON Book.id = Person.id " +
                                        "WHERE Book.book_id = ?",
                                new BeanPropertyRowMapper<>(Person.class),
                                bookId
                        ).stream().findAny();
    }

    public void release(int bookId)
    {
        jdbcTemplate.update
                (
                        "UPDATE Book SET id=NULL WHERE book_id=?",
                        bookId
                );
    }

    public void assign(int book_id, Person selectedPerson)
    {
        jdbcTemplate.update
                (
                        "UPDATE Book SET id=? WHERE book_id=?",
                        selectedPerson.getId(),
                        book_id
                );
    }

}
