package com.example.persistence.jpahibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.jpahibernate.dto.StudentDto;
import com.example.persistence.jpahibernate.request.CourseRequest;
import com.example.persistence.jpahibernate.request.StudentRequest;
import com.example.persistence.jpahibernate.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getAllStudents() {
        return this.studentService.findByStudentsFetchJoin();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudentById(@PathVariable(name = "id") Long id) {
        return this.studentService.findByIdFetchJoin(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail addStudent(@RequestBody StudentRequest studentRequest) {
        this.studentService.addStudent(studentRequest);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, "Student added!");
    }

    @PostMapping(value = "/{id}/enroll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail enrollStudent(@PathVariable(name = "id") Long id, @RequestBody CourseRequest course) {
        this.studentService.enrollStudent(id, course);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, "Student enrolled!");
    }

    @PostMapping(value = "/{id}/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail removeStudent(@PathVariable(name = "id") Long id, @RequestBody CourseRequest course) {
        this.studentService.removeStudent(id, course);
        return ProblemDetail.forStatusAndDetail(HttpStatus.CREATED, "Student removed from course");
    }
}
