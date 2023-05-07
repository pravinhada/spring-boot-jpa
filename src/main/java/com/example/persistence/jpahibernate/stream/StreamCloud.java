package com.example.persistence.jpahibernate.stream;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.persistence.jpahibernate.event.CourseEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Configuration
public class StreamCloud {

    @Bean
    public Consumer<CourseEvent> courseEvent() {
        return (event) -> {
            var msg = switch (event.eventType()) {
                case CREATED -> "New course is created";
                case UPDATED -> "Course is updated";
                case DELETED -> "Course is deleted";
            };
            log.info(msg);
        };
    }
}
