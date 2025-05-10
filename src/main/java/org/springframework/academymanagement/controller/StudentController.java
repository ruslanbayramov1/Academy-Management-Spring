package org.springframework.academymanagement.controller;

import org.springframework.academymanagement.dto.StudentGetDTO;
import org.springframework.academymanagement.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentGetDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/hi")
    public String getText() {
        return "Hi! U finally solved the problem after 4 hours :)";
    }
}
