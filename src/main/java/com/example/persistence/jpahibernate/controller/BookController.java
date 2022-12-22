package com.example.persistence.jpahibernate.controller;

import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.dto.BookDto;
import com.example.persistence.jpahibernate.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto findById(@PathVariable(value = "id") Long id) {
        return this.bookService.findByBookId(id);
    }

    @GetMapping(value = "/{title}/title", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto findByBookTitle(@PathVariable(value = "title") String title) {
        return this.bookService.findByBookTitle(title);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail addBooks(@RequestBody AuthorMapperDto authorDto) {
        this.bookService.addBooks(authorDto);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, "Added books to author ");
    }
}
