package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.CourseDto;
import com.example.persistence.jpahibernate.model.Course;
import com.example.persistence.jpahibernate.model.Student;
import com.example.persistence.jpahibernate.repo.CourseRepository;
import com.example.persistence.jpahibernate.repo.StudentRepository;
import com.example.persistence.jpahibernate.request.CourseRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    final private CourseRepository courseRepository;
    final private StudentRepository studentRepository;

    @Transactional
    public void removeStudent() {
        Course course = this.courseRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Course with this id is not found."));
        Student student = this.studentRepository.findById(8L).orElseThrow(
                () -> new IllegalArgumentException("Student with this id is not found."));
        // course.removeStudent(student);
        log.info("student is removed ", student);
    }

    @Transactional(readOnly = true)
    public Course getCourse(Long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("course with id " + id + " is not found."));
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        return this.courseRepository.findByJoinFetch();
    }

    @Transactional(readOnly = true)
    public CourseDto findCourseById(Long id) {
        return this.courseRepository.findByIdJoinFetch(id);
    }

    @Transactional
    public void addCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.title());
        course.setCategory(courseRequest.category());
        List<Course> courses = this.courseRepository.findByTitle(courseRequest.title());
        if (!courses.isEmpty()) {
            throw new IllegalArgumentException("Course with name [" + courseRequest.title() + "] is already exist.");
        }
        this.courseRepository.save(course);
    }
}
