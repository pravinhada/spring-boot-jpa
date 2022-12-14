package com.example.persistence.jpahibernate.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMapperDto implements Serializable {

    public Long bookId;
    public String title;

}
