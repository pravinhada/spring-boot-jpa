package com.example.persistence.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    @OneToMany(mappedBy = "course", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<CourseStudent> students = new HashSet<>();

    public void addStudent(Student student) {
        CourseStudent courseStudent = CourseStudent.builder()
                .course(this)
                .student(student)
                .build();
        students.add(courseStudent);
        student.getCourses().add(courseStudent);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return id != null && id.equals(course.getId());
    }

    @Override
    public int hashCode() {
        return 2022;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + this.title + '\'' +
                ", category='" + this.category + '\'' +
                '}';
    }
}
