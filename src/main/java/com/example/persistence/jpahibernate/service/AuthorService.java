package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.AuthorDto;
import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.dto.AuthorTransformer;
import com.example.persistence.jpahibernate.model.Author;
import com.example.persistence.jpahibernate.model.Book;
import com.example.persistence.jpahibernate.repo.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorTransformer authorTransformer;

    @Transactional
    public void insertAuthor(Author author) {
        this.authorRepository.save(author);
    }

    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author with id %d is not found", id)));
    }

    public Author findReferenceById(Long id) {
        return this.authorRepository.getReferenceById(id);
    }

    @Transactional
    public void fetchBooksOfAuthorById(Long id) {
        List<Book> books = this.authorRepository.fetchBooksByAuthorId(id);
        books.get(0).setIsbn("NEW-ISBN-1233");
    }

    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public void getAllCheapBooks() {
        Author author = this.authorRepository.findById(1L).orElseThrow();
        Set<Book> cheapBooks = author.getCheapBooks();
        cheapBooks.forEach(b -> log.info("{}", b.getPrice()));
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findBy() {
        return this.authorRepository.findBy(); // LazyInitialization exception need to fix here
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findByViaQuery() {
        return this.authorRepository.findByViaQuery();
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findByJoinFetch() {
        return this.authorRepository.findByJoinFetch();
    }

    @Transactional(readOnly = true)
    public List<AuthorMapperDto> findByViaArrayOfObjectsWithIds() {
        List<Object[]> result = this.authorRepository.findByViaArrayOfObjectsWithIds();
        return this.authorTransformer.transform(result);
    }

    @Transactional(readOnly = true)
    public List<Author> findAuthorByName(String name) {
        return this.authorRepository.findAuthorByNameIgnoreCase(name);
    }

    @Transactional
    public void addAuthor(AuthorMapperDto authorMapperDto) {
        List<Author> authors = this.findAuthorByName(authorMapperDto.getName());
        if (!authors.isEmpty()) {
            throw new IllegalArgumentException("Author [" + authorMapperDto.getName() + "] already exist.");
        }
        Author author = Author.builder()
                .name(authorMapperDto.getName())
                .age(authorMapperDto.getAge())
                .genre(authorMapperDto.getGenre())
                .build();
        this.insertAuthor(author);
    }

    @Transactional(readOnly = true)
    public AuthorDto findByAuthorIdJoinFetch(Long id) {
        return this.authorRepository.findByAuthorIdJoinFetch(id);
    }
}
