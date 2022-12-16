package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
