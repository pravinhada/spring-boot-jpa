package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@Resource
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
