package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
