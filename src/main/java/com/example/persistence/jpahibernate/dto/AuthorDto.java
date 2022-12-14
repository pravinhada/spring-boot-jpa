package com.example.persistence.jpahibernate.dto;

import java.util.List;

public interface AuthorDto {
    String getName();

    String getGenre();

    Integer getAge();

    List<BookDto> getBooks();

    public interface BookDto {
        String getTitle();
    }
}
