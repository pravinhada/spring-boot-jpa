package com.example.persistence.jpahibernate.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes = { AuthorController.class })
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void testFindByJoinFetch() {
        AuthorDto authorDto = new AuthorDto() {
            @Override
            public Integer getAge() {
                return 40;
            }

            @Override
            public String getGenre() {
                return "computer";
            }

            @Override
            public String getName() {
                return "Bob";
            }

            @Override
            public List<AuthorBookDto> getBooks() {
                return null;
            }
        };
        when(this.authorService.findByJoinFetch()).thenReturn(List.of(authorDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre", Is.is("computer")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Is.is("Bob")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", Is.is(40)));
    }

    @Test
    @SneakyThrows
    void testAddAuthor() {
        AuthorMapperDto authorMapperDto = new AuthorMapperDto();
        authorMapperDto.setName("Author1");
        authorMapperDto.setAge(40);
        authorMapperDto.setGenre("computer");

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(authorMapperDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
}
