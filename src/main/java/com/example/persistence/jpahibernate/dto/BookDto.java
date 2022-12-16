package com.example.persistence.jpahibernate.dto;

import java.math.BigDecimal;

public interface BookDto {
    public String getTitle();

    public String getIsbn();

    public BigDecimal getPrice();

    public BookAuthorDto getAuthor();

    public interface BookAuthorDto {
        public String getName();
    }
}
