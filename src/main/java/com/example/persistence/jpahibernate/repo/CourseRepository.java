package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.dto.CourseDto;
import com.example.persistence.jpahibernate.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join fetch c.students cs join fetch cs.student s")
    List<CourseDto> findByJoinFetch();

    @Query("select c from Course c join fetch c.students cs join fetch cs.student where c.id = :id")
    CourseDto findByIdJoinFetch(Long id);

    @Query("select c from Course c join fetch c.students cs join fetch cs.student where c.id = :id")
    Course findByCourseIdJoinFetch(Long id);

    List<Course> findByTitle(String title);

    @Modifying
    @Query("delete from Course c where c.id = :id")
    void deleteCourseById(Long id);
}
