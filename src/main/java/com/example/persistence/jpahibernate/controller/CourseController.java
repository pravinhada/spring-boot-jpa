package com.example.persistence.jpahibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.jpahibernate.dto.CourseDto;
import com.example.persistence.jpahibernate.request.CourseRequest;
import com.example.persistence.jpahibernate.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto findCourseById(@PathVariable(name = "id") Long id) {
        return this.courseService.findCourseById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail addCourse(@RequestBody CourseRequest courseRequest) {
        this.courseService.addCourse(courseRequest);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, "Added the course");
    }
}
