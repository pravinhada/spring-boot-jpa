package com.example.persistence.jpahibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BookMapperDto implements Serializable {

    public Long bookId;
    public String title;
    public String isbn;
    public BigDecimal price;
}
