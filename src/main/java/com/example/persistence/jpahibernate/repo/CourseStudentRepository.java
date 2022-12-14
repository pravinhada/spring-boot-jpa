package com.example.persistence.jpahibernate.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.persistence.jpahibernate.model.CourseStudent;
import com.example.persistence.jpahibernate.model.CourseStudentId;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentId> {

    @Modifying
    @Query("delete from CourseStudent c where c.id in :id")
    void deleteCourseStudentByCourseStudentId(List<CourseStudentId> id);

}
