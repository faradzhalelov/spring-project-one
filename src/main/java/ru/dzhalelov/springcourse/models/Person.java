package ru.dzhalelov.springcourse.models;


import javax.validation.constraints.*;

public class Person
{

    private int id;
    @NotEmpty(message = "ФИО не может быть пустым")
    @Pattern
            (
                    regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+",
                    message = "ФИО должно выглядить в формате: Фамилия Имя Отчество"
            )
    private String name;

    @Min(value = 1901, message = "Введите число от 1901 до 2022")
    @Max(value = 2022, message = "Введите число от 1900 до 2022")
    @NotNull(message = "Введите дату")
    private int birthDate;

    public Person() {}

    public Person(int id, String name, int birthDate)
    {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(int birthDate)
    {
        this.birthDate = birthDate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
