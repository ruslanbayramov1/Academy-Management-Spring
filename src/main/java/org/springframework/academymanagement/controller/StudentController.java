package org.springframework.academymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.academymanagement.dto.response.ApiResponse;
import org.springframework.academymanagement.dto.student.StudentCreateDTO;
import org.springframework.academymanagement.dto.student.StudentGetDTO;
import org.springframework.academymanagement.dto.student.StudentUpdateDTO;
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
    public ResponseEntity<ApiResponse<List<StudentGetDTO>>> getStudents() {
        var response = new ApiResponse<>(true, HttpStatus.OK, "success",studentService.getAllStudents());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentGetDTO>> getStudent(@PathVariable UUID studentId) {
        var response = new ApiResponse<>(true, HttpStatus.OK, "success",studentService.getStudentById(studentId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody @Valid StudentCreateDTO student) {
        studentService.createStudent(student);
        var response = new ApiResponse<Void>(true, HttpStatus.CREATED, "created");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateStudent(@RequestHeader UUID id, @RequestBody @Valid StudentUpdateDTO student) {
        studentService.updateStudent(id, student);
        var response = new ApiResponse<Void>(true, HttpStatus.CREATED, "updated");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@RequestHeader UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
