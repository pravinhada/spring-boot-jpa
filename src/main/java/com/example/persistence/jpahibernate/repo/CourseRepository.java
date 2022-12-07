package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
