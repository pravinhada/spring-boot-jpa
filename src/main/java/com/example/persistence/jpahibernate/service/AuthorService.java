package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.model.Author;
import com.example.persistence.jpahibernate.model.Book;
import com.example.persistence.jpahibernate.repo.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class AuthorService {

    @Resource
    private AuthorRepository authorRepository;

    @Transactional
    public void insertAuthor(Author author) {
        this.authorRepository.save(author);
    }

    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author id is required"));
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
        cheapBooks.forEach(b -> System.out.println(b.getPrice()));
    }
}
