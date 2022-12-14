package com.example.persistence.jpahibernate.controller;

import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.dto.BookDto;
import com.example.persistence.jpahibernate.dto.BookMapperDto;
import com.example.persistence.jpahibernate.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
@ContextConfiguration(classes = { BookController.class })
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDto bookDto;

    @BeforeEach
    void init() {
        this.bookDto = new BookDto() {
            @Override
            public String getTitle() {
                return "system design";
            }

            @Override
            public String getIsbn() {
                return "3434-3434-BDF";
            }

            @Override
            public BigDecimal getPrice() {
                return BigDecimal.valueOf(34.99);
            }

            @Override
            public BookAuthorDto getAuthor() {
                return () -> "Anghel Leonard";
            }
        };
    }

    @Test
    @SneakyThrows
    void testFindBy() {
        when(this.bookService.findBy()).thenReturn(List.of(this.bookDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Is.is("system design")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn", Is.is("3434-3434-BDF")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Is.is(34.99)));
    }

    @Test
    @SneakyThrows
    void testFindByBookId() {
        when(this.bookService.findByBookId(anyLong())).thenReturn(this.bookDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("system design")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Is.is("3434-3434-BDF")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(34.99)));
    }

    @Test
    @SneakyThrows
    void testFindByBookTitle() {
        when(this.bookService.findByBookTitle(anyString())).thenReturn(this.bookDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/system%20design/title"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("system design")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Is.is("3434-3434-BDF")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(34.99)));
    }

    @Test
    @SneakyThrows
    void testAddBook() {
        AuthorMapperDto authorMapperDto = new AuthorMapperDto();
        authorMapperDto.setName("Author1");
        authorMapperDto.setAge(40);
        authorMapperDto.setGenre("computer");

        BookMapperDto bookMapperDto = new BookMapperDto();
        bookMapperDto.setTitle("Test Book");
        bookMapperDto.setIsbn("TEST121344");
        bookMapperDto.setPrice(BigDecimal.valueOf(3.45));

        authorMapperDto.setBooks(List.of(bookMapperDto));

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(authorMapperDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
}
