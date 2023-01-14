package com.example.persistence.jpahibernate.event;

public record CourseEvent(
        Long eventId,
        EventType eventType) {
}
