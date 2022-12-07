package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Override
    @EntityGraph(attributePaths = "books.author", type = EntityGraph.EntityGraphType.FETCH)
    List<Publisher> findAll();
}
