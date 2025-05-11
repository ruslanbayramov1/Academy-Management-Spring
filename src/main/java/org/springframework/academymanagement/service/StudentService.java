package org.springframework.academymanagement.service;

import org.springframework.academymanagement.dto.student.StudentCreateDTO;
import org.springframework.academymanagement.dto.student.StudentGetDTO;
import org.springframework.academymanagement.dto.student.StudentUpdateDTO;
import org.springframework.academymanagement.exception.NotFoundException;
import org.springframework.academymanagement.repository.StudentRepository;
import org.springframework.academymanagement.entity.Student;
import org.springframework.academymanagement.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Student not found."));
        return studentMapper.studentToStudentGetDTO(student);
    }

    public StudentGetDTO findByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode).orElseThrow(() -> new NotFoundException("Student not found."));
        return studentMapper.studentToStudentGetDTO(student);
    }

    public StudentGetDTO getStudentById(UUID studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found."));
        return studentMapper.studentToStudentGetDTO(student);
    }

    public void createStudent(StudentCreateDTO studentCreateDTO) {
        Student student = studentMapper.studentCreateDTOToStudent(studentCreateDTO);
        studentRepository.save(student);
    }

    public void updateStudent(UUID id, StudentUpdateDTO studentUpdateDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found."));
        studentMapper.updateStudentFromDto(studentUpdateDTO, student);
        studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found."));
        studentRepository.deleteById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
