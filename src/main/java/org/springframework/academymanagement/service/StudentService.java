package org.springframework.academymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.academymanagement.dto.student.StudentCreateDTO;
import org.springframework.academymanagement.dto.student.StudentGetDTO;
import org.springframework.academymanagement.dto.student.StudentUpdateDTO;
import org.springframework.academymanagement.entity.Group;
import org.springframework.academymanagement.exception.NotFoundException;
import org.springframework.academymanagement.repository.GroupRepository;
import org.springframework.academymanagement.repository.StudentRepository;
import org.springframework.academymanagement.entity.Student;
import org.springframework.academymanagement.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;

    public List<StudentGetDTO> getAllStudents() {
        List<Student> students =  studentRepository.findAll();
        return students.stream().map(studentMapper::studentToStudentGetDTO).toList();
    }

    public StudentGetDTO getStudentById(UUID id) {
        Student student = getStudentEntityById(id);
        return studentMapper.studentToStudentGetDTO(student);
    }

    public void createStudent(StudentCreateDTO studentCreateDTO) {
        Group group = groupRepository.findById(studentCreateDTO.groupId()).orElseThrow(() -> new NotFoundException("Group not found"));

        Student student = studentMapper.studentCreateDTOToStudent(studentCreateDTO);
        student.setGroup(group);
        studentRepository.save(student);
    }

    public void updateStudent(UUID id, StudentUpdateDTO studentUpdateDTO) {
        Group group = groupRepository.findById(studentUpdateDTO.groupId()).orElseThrow(() -> new NotFoundException("Group not found"));

        Student student = getStudentEntityById(id);
        studentMapper.updateStudentFromDto(studentUpdateDTO, student);
        student.setGroup(group);
        studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        Student student = getStudentEntityById(id);
        studentRepository.deleteById(id);
    }

    private Student getStudentEntityById(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
    }
}
