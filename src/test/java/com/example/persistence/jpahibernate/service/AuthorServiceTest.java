package com.example.persistence.jpahibernate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.repo.AuthorRepository;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
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
        when(this.authorRepository.findByJoinFetch()).thenReturn(List.of(authorDto));

        List<AuthorDto> result = this.authorService.findByJoinFetch();
        assertEquals(result.size(), 1);
    }

}
