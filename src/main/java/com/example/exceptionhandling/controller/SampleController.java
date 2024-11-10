package com.example.exceptionhandling.controller;

import com.example.exceptionhandling.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping("/sample")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSample(@Valid @RequestBody SampleRequest sampleRequest) {
        return sampleService.callExternalApi(sampleRequest);
    }

    public static class SampleRequest {
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        private String name;

        @NotNull(message = "Students list cannot be null")
        @NotEmpty(message = "Students list cannot be empty")
        @Valid
        private List<Student> students;

        // getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }

    public static class Student {
        @NotBlank(message = "Student name cannot be blank")
        @Size(min = 2, max = 30, message = "Student name must be between 2 and 30 characters")
        private String studentName;

        // getters and setters
        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }
}
