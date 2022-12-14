package com.example.persistence.jpahibernate.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorMapperDto implements Serializable {
    private Long authorId;
    private String name;
    private String genre;

    private List<BookMapperDto> books = new ArrayList<>();
}
