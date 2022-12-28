package com.example.persistence.jpahibernate.controller;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.service.AuthorService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    public List<AuthorDto> findBy() {
        return this.authorService.findBy();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto findAuthorById(@PathVariable(name = "id") Long id) {
        return this.authorService.findByAuthorIdJoinFetch(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> findByJoinFetch() {
        return this.authorService.findByJoinFetch();
    }

    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorMapperDto> findByViaArrayOfObjectsWithIds() {
        return this.authorService.findByViaArrayOfObjectsWithIds();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail addAuthor(@RequestBody AuthorMapperDto authorDto) {
        this.authorService.addAuthor(authorDto);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, authorDto.toString());
    }
}
