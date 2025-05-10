package org.springframework.academymanagement.mapper;

import org.mapstruct.Mapper;
import org.springframework.academymanagement.entity.Student;
import org.springframework.academymanagement.dto.StudentGetDTO;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentGetDTO studentToStudentGetDTO(Student student);
}
