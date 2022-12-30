package com.example.persistence.jpahibernate.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.persistence.jpahibernate.dto.StudentDto;
import com.example.persistence.jpahibernate.request.CourseRequest;
import com.example.persistence.jpahibernate.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = { StudentController.class })
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentDto studentDto;

    @BeforeEach
    public void beforeEach() {
        this.studentDto = new StudentDto() {
            @Override
            public String getName() {
                return "Tim";
            }

            @Override
            public List<CourseStudentDto> getCourses() {
                return null;
            }
        };
    }

    @Test
    @SneakyThrows
    void testGetAllStudents() {
        when(this.studentService.findByStudentsFetchJoin()).thenReturn(List.of(this.studentDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Is.is("Tim")));
    }

    @Test
    @SneakyThrows
    void testGetStudentById() {
        when(this.studentService.findByIdFetchJoin(anyLong())).thenReturn(this.studentDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Tim")));
    }

    @Test
    @SneakyThrows
    void testAddStudent() {
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.studentDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @SneakyThrows
    void testEnrollStudent() {
        CourseRequest course = new CourseRequest("Test", "Computer");
        mockMvc.perform(MockMvcRequestBuilders.post("/students/1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(course)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail", Is.is("Student enrolled!")));;
    }
}
