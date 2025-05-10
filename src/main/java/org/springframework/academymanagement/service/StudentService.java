package org.springframework.academymanagement.service;

import org.springframework.academymanagement.dto.StudentGetDTO;
import org.springframework.academymanagement.repository.StudentRepository;
import org.springframework.academymanagement.entity.Student;
import org.springframework.academymanagement.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentGetDTO> getAllStudents() {
        List<Student> students =  studentRepository.findAll();
        return students.stream().map(studentMapper::studentToStudentGetDTO).toList();
    }

    public StudentGetDTO findByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(""));
        return studentMapper.studentToStudentGetDTO(student);
    }

    public StudentGetDTO findByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode).orElseThrow(() -> new RuntimeException(""));
        return studentMapper.studentToStudentGetDTO(student);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
