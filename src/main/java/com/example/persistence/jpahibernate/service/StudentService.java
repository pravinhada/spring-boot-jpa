package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.StudentDto;
import com.example.persistence.jpahibernate.model.Address;
import com.example.persistence.jpahibernate.model.Course;
import com.example.persistence.jpahibernate.model.Student;
import com.example.persistence.jpahibernate.repo.AddressRepository;
import com.example.persistence.jpahibernate.repo.CourseRepository;
import com.example.persistence.jpahibernate.repo.InstructorRepository;
import com.example.persistence.jpahibernate.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Slf4j
@Service
public class StudentService {
    @Resource
    private CourseRepository courseRepository;
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private AddressRepository addressRepository;
    @Resource
    private InstructorRepository instructorRepository;

    @Transactional
    public void insertStudents() {
        Course course1 = this.courseRepository.findById(1L).orElseThrow();
        Address address = new Address();
        address.setStreet("1 layton hall dr");
        address.setCity("Brokton");
        address.setState("MA");
        address.setZip(13400);

        this.addressRepository.save(address);

        Student student = new Student();
        student.setName("Jimmi");
        student.setAddress(address);
        //student.addCourse(course1);

        /*
        student.addCourse(course1);
        student.addCourse(course2);
        student.setAddress(address);

        Student student1 = new Student();
        student1.setName("ben");
        student1.addCourse(course2);
        student1.setAddress(address);

        this.studentRepository.saveAll(List.of(student, student1));

        Instructor instructor = new Instructor();
        instructor.setName("Micheal T");
        instructor.setAddress(address);

        this.instructorRepository.save(instructor);
        */
        this.studentRepository.save(student);

        //course1.addStudent(student);

        log.info("Total Students: {}", course1.getStudents().size());
    }

    @Transactional(readOnly = true) 
    public List<StudentDto> findByStudentsFetchJoin() {
        return this.studentRepository.findByStudentsJoinFetch();
    }

    @Transactional(readOnly = true) 
    public StudentDto findByIdFetchJoin(Long id) {
        return this.studentRepository.findByIdJoinFetch(id);
    }
}
