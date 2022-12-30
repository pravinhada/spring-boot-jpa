package com.example.persistence.jpahibernate.service;

import com.example.persistence.jpahibernate.dto.StudentDto;
import com.example.persistence.jpahibernate.model.Address;
import com.example.persistence.jpahibernate.model.Course;
import com.example.persistence.jpahibernate.model.CourseStudent;
import com.example.persistence.jpahibernate.model.CourseStudentId;
import com.example.persistence.jpahibernate.model.Student;
import com.example.persistence.jpahibernate.repo.AddressRepository;
import com.example.persistence.jpahibernate.repo.CourseRepository;
import com.example.persistence.jpahibernate.repo.CourseStudentRepository;
import com.example.persistence.jpahibernate.repo.InstructorRepository;
import com.example.persistence.jpahibernate.repo.StudentRepository;
import com.example.persistence.jpahibernate.request.CourseRequest;
import com.example.persistence.jpahibernate.request.StudentRequest;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

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
    @Resource
    private CourseStudentRepository courseStudentRepository;

    @Transactional(readOnly = true)
    public List<StudentDto> findByStudentsFetchJoin() {
        return this.studentRepository.findByStudentsJoinFetch();
    }

    @Transactional(readOnly = true)
    public StudentDto findByIdFetchJoin(Long id) {
        return this.studentRepository.findByIdJoinFetch(id);
    }

    @Transactional
    public void addStudent(StudentRequest studentRequest) {
        if (!this.studentRepository.findByName(studentRequest.name()).isEmpty()) {
            throw new IllegalArgumentException("Student name already exist. Try another!");
        }

        Address address = new Address();
        address.setStreet(studentRequest.address().street());
        address.setCity(studentRequest.address().city());
        address.setState(studentRequest.address().state());
        address.setZip(studentRequest.address().zipCode());

        this.addressRepository.save(address);

        Student student = new Student();
        student.setName(studentRequest.name());
        student.setAddress(address);

        this.studentRepository.save(student);
    }

    @Transactional
    public void enrollStudent(Long studentId, CourseRequest course) {
        List<Course> courses = this.courseRepository.findByTitle(course.title());
        if (courses.isEmpty()) {
            throw new IllegalArgumentException("Course with name [" + course.title() + "] not exist to enroll.");
        }

        Student student = this.studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("Student with id " + studentId + " not found"));

        StudentDto studentDto = this.findByIdFetchJoin(studentId);
        studentDto.getCourses().forEach(c -> {
            if (c.getTitle().equalsIgnoreCase(course.title())) {
                throw new IllegalArgumentException(
                        "Student " + studentDto.getName() + " is already enrolled in course " + course.title());
            }
        });

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setId(new CourseStudentId(courses.get(0).getId(), studentId));
        courseStudent.setCourse(courses.get(0));
        courseStudent.setStudent(student);
        courseStudent.setEnrolledOn(new Date());
        this.courseStudentRepository.save(courseStudent);
    }

    @Transactional
    public void removeStudent(Long studentId, CourseRequest course) {
        List<Course> courses = this.courseRepository.findByTitle(course.title());
        if (courses.isEmpty()) {
            throw new IllegalArgumentException("Course with name [" + course.title() + "] not exist to enroll.");
        }

        Student student = this.studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("Student with id " + studentId + " not found"));

        StudentDto studentDto = this.findByIdFetchJoin(studentId);
        studentDto.getCourses().forEach(c -> {
            if (c.getTitle().equalsIgnoreCase(course.title())) {
                // remove the student here
                CourseStudent courseStudent = this.courseStudentRepository
                        .findById(new CourseStudentId(courses.get(0).getId(), student.getId())).orElseThrow(
                                () -> new IllegalArgumentException("Could not found the CourseStudent"));
                this.courseStudentRepository.deleteCourseStudentByCourseStudentId(courseStudent.getId());
            }
        });
    }
}
