package org.springframework.academymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.academymanagement.dto.StudentCreateDTO;
import org.springframework.academymanagement.dto.StudentGetDTO;
import org.springframework.academymanagement.dto.StudentUpdateDTO;
import org.springframework.academymanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentGetDTO>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentCreateDTO student) {
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestHeader UUID id, @RequestBody @Valid StudentUpdateDTO student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestHeader UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
