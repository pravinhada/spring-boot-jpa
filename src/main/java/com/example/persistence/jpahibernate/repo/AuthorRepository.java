package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Author;
import com.example.persistence.jpahibernate.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select b from Book b where b.author.id = :id")
    List<Book> fetchBooksByAuthorId(Long id);

    // using @NamedEntityGraph in entity
    @Override
    @EntityGraph(value = "author-books-publisher-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Author> findAll();

    // ad-hoc entity graph
    @EntityGraph(attributePaths = {"books.publisher"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Author> findByAgeLessThanOrderByNameDesc(int age);

}
