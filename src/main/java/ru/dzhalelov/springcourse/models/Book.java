package ru.dzhalelov.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book
{
    private int bookId;
    @NotEmpty(message = "Поле не может быть пустым")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    private String author;
    @Min(value = 0, message = "Введите минимум 0")
    @Max(value = 2022, message = "Введите максимум 2022")
    @NotNull
    private int year;

    public Book() {}

    public Book(int bookId, String name, String author, int year)
    {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

}
