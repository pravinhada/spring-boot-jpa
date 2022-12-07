package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.model.Course;
import com.example.persistence.jpahibernate.model.Student;
import com.example.persistence.jpahibernate.repo.CourseRepository;
import com.example.persistence.jpahibernate.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {
    final private CourseRepository courseRepository;
    final private StudentRepository studentRepository;

    final private StudentService studentService;

    @Transactional
    public void removeStudent() {
        Course course = this.courseRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Course with this id is not found."));
        Student student = this.studentRepository.findById(8L).orElseThrow(
                () -> new IllegalArgumentException("Student with this id is not found."));
        course.removeStudent(student);
    }

    @Transactional(readOnly = true)
    public Course getCourse(Long id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("course with id " + id + " is not found."));
    }
}
