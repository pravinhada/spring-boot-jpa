package com.example.persistence.jpahibernate.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.service.AuthorService;

import lombok.SneakyThrows;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes = { AuthorController.class })
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

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
}
