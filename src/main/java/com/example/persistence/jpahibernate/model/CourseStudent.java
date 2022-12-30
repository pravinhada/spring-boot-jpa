package com.example.persistence.jpahibernate.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudent {

    @EmbeddedId
    private CourseStudentId id;

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Builder.Default
    private Date enrolledOn = new Date();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CourseStudent other = (CourseStudent) obj;
        return Objects.equals(this.student, other.student)
            && Objects.equals(this.course, other.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.course); 
    }
    
}
