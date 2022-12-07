package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.model.Author;
import com.example.persistence.jpahibernate.model.Book;
import com.example.persistence.jpahibernate.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    final private AuthorService authorService;
    final private BookRepository bookRepository;

    @Transactional
    public void insertBook() {
        Author author = this.authorService.findReferenceById(1L);
        Book book = Book.builder().title("Spring Boot Persistence Best Practices").isbn("IABS-2323-45").build();
        book.setAuthor(author);
        this.bookRepository.save(book);
        log.info("inserted book");
    }

    @Transactional
    public void removeBook() {

    }
}
