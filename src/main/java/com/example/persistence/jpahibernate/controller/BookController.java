package com.example.persistence.jpahibernate.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.jpahibernate.dto.BookDto;
import com.example.persistence.jpahibernate.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    public List<BookDto> getAllBooks() {
        return this.bookService.fetchBooksAuthorsJoinFetch();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> findBy() {
        return this.bookService.findBy();
    }
}
