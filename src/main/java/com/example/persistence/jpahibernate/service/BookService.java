package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.AuthorMapperDto;
import com.example.persistence.jpahibernate.dto.BookDto;
import com.example.persistence.jpahibernate.dto.BookMapperDto;
import com.example.persistence.jpahibernate.model.Author;
import com.example.persistence.jpahibernate.model.Book;
import com.example.persistence.jpahibernate.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<BookDto> fetchBooksAuthorsJoinFetch() {
        return this.bookRepository.fetchBooksAuthorsJoinFetch();
    }

    @Transactional(readOnly = true)
    public List<BookDto> findBy() {
        return this.bookRepository.findBy();
    }

    @Transactional(readOnly = true)
    public BookDto findByBookId(Long id) {
        return this.bookRepository.findByBookId(id);
    }

    @Transactional(readOnly = true)
    public BookDto findByBookTitle(String title) {
        return this.bookRepository.findByBookTitle(title);
    }

    @Transactional
    public void addBooks(AuthorMapperDto authorMapperDto) {
        List<Author> author = authorMapperDto.getAuthorId() != null
                ? List.of(this.authorService.findAuthorById(authorMapperDto.getAuthorId()))
                : this.authorService.findAuthorByName(authorMapperDto.getName());
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Author not found!");
        }
        List<Book> books = new ArrayList<>();
        List<BookMapperDto> booksDto = authorMapperDto.getBooks();
        booksDto.forEach(b -> {
            Book book = Book.builder().title(b.getTitle()).isbn(b.getIsbn()).price(b.getPrice()).build();
            book.setAuthor(author.get(0));
            books.add(book);
        });
        this.bookRepository.saveAll(books);
    }
}
