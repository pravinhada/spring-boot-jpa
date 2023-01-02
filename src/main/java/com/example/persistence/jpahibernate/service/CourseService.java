package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.CourseDto;
import com.example.persistence.jpahibernate.model.Course;
import com.example.persistence.jpahibernate.model.CourseStudentId;
import com.example.persistence.jpahibernate.repo.CourseRepository;
import com.example.persistence.jpahibernate.repo.CourseStudentRepository;
import com.example.persistence.jpahibernate.request.CourseRequest;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

    final private CourseRepository courseRepository;
    final private CourseStudentRepository courseStudentRepository;

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

    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = this.courseRepository.findByCourseIdJoinFetch(courseId);
        List<CourseStudentId> courseStudentIds = course.getStudents().stream().map(s -> s.getId()).toList();
        this.courseStudentRepository.deleteCourseStudentByCourseStudentId(courseStudentIds);
        this.courseRepository.deleteCourseById(course.getId());
    }
}
