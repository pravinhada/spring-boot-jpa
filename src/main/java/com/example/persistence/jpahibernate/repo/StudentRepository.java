package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.dto.StudentDto;
import com.example.persistence.jpahibernate.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s join fetch s.courses sc join fetch sc.course")
    List<StudentDto> findByStudentsJoinFetch();

    @Query("select s from Student s join fetch s.courses sc join fetch sc.course where s.id = :id")
    StudentDto findByIdJoinFetch(Long id);
}
