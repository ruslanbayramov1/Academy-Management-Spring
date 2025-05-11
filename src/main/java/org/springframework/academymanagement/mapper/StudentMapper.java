package org.springframework.academymanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.academymanagement.dto.student.StudentCreateDTO;
import org.springframework.academymanagement.dto.student.StudentUpdateDTO;
import org.springframework.academymanagement.entity.Student;
import org.springframework.academymanagement.dto.student.StudentGetDTO;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentGetDTO studentToStudentGetDTO(Student student);

    @Mapping(target = "id", ignore = true)
    Student studentCreateDTOToStudent(StudentCreateDTO studentCreateDTO);

    @Mapping(target = "id", ignore = true)
    void updateStudentFromDto(StudentUpdateDTO studentUpdateDTO, @MappingTarget Student student);
}
