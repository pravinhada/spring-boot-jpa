package com.example.persistence.jpahibernate.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    public List<AuthorDto> findBy() {
        return this.authorService.findBy();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> findByJoinFetch() {
        return this.authorService.findByJoinFetch();
    }

    // @GetMapping
    public List<AuthorMapperDto> findByViaArrayOfObjectsWithIds() {
        return this.authorService.findByViaArrayOfObjectsWithIds();
    }
}
