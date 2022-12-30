package com.example.persistence.jpahibernate.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface CourseDto {
    
    String getTitle();

    String getCategory();

    List<CourseStudentDto> getStudents();

    public interface CourseStudentDto {
        @Value("#{target.enrolledOn}")
        String getEnrolledDate();
        @Value("#{target.student.name}")
        String getStudentName();

    }
}
