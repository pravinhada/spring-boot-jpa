package com.example.persistence.jpahibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthorMapperDto implements Serializable {
    private Long authorId;
    private String name;
    private String genre;
    private Integer age;

    @ToString.Exclude
    private List<BookMapperDto> books = new ArrayList<>();
}
