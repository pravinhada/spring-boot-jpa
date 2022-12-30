package com.example.persistence.jpahibernate.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentId implements Serializable {
    
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "student_id")
    private Long studentId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final CourseStudentId other = (CourseStudentId) obj;
        return Objects.equals(this.studentId, other.studentId) 
            && Objects.equals(this.courseId, other.courseId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.studentId, this.courseId);
    }
}
