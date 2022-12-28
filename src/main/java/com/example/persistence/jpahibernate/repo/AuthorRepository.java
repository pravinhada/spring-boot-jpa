package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.dto.AuthorDto;
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

    List<AuthorDto> findBy();

    @Query("""
            select a.name as name, a.genre as genre, a.age as age, b as books
            from Author a inner join a.books b
            """)
    List<AuthorDto> findByViaQuery();

    @Query("select a from Author a join fetch a.books")
    List<AuthorDto> findByJoinFetch();

    @Query("""
            select a.id as authorId, a.name as name, a.genre as genre, b.id as bookId, b.title as title,
            b.isbn as isbn, b.price as price
            from Author a left join a.books b
            """)
    public List<Object[]> findByViaArrayOfObjectsWithIds();

    List<Author> findAuthorByNameIgnoreCase(String string);

    @Query("select a from Author a join fetch a.books where a.id = :id")
    AuthorDto findByAuthorIdJoinFetch(Long id);
}
