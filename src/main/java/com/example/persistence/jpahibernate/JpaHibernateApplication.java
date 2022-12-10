package com.example.persistence.jpahibernate;

import com.example.persistence.jpahibernate.model.Publisher;
import com.example.persistence.jpahibernate.repo.AuthorRepository;
import com.example.persistence.jpahibernate.repo.PublisherRepository;
import com.example.persistence.jpahibernate.service.AuthorService;
import com.example.persistence.jpahibernate.service.BookService;
import com.example.persistence.jpahibernate.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    final private BookService bookService;
    final private AuthorService authorService;

    final private CourseService courseService;

    final private AuthorRepository authorRepository;
    final private PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("test the spring data jpa hibernate");
        //this.bookService.insertBook();
        //this.authorService.fetchBooksOfAuthorById(1L);
        //this.courseService.removeStudent();
        //List<Author> authors = this.authorRepository.findAll(); //this.authorRepository.findByAgeLessThanOrderByNameDesc(50);
        //authors.forEach(a -> a.getBooks().forEach(b -> System.out.println(b.getPublisher())));
        List<Publisher> publishers = this.publisherRepository.findAll();
        publishers.forEach(p -> p.getBooks().forEach(b -> System.out.println(b.getAuthor())));

        this.authorService.getAllCheapBooks();
    }
}
