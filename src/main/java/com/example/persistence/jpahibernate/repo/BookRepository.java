package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.dto.BookDto;
import com.example.persistence.jpahibernate.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b join fetch b.author")
    List<BookDto> fetchBooksAuthorsJoinFetch();

    List<BookDto> findBy();
}
