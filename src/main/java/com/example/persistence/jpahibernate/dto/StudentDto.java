package com.example.persistence.jpahibernate.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface StudentDto {
    String getName();
    
    List<CourseStudentDto> getCourses();

    public interface CourseStudentDto {
        @Value("#{target.enrolledOn}")
        String getEnrolledDate();
        @Value("#{target.course.title}")
        String getTitle();

    }
}
