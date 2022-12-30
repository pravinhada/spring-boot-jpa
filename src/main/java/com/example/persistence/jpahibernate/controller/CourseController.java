package com.example.persistence.jpahibernate.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.jpahibernate.dto.CourseDto;
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
}
