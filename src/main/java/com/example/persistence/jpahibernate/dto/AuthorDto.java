package com.example.persistence.jpahibernate.dto;

import java.util.List;

public interface AuthorDto {
    String getName();

    String getGenre();

    Integer getAge();

    List<AuthorBookDto> getBooks();

    public interface AuthorBookDto {
        String getTitle();

        Integer getPrice();
    }
}
