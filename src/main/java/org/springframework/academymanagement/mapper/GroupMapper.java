package org.springframework.academymanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.academymanagement.dto.group.GroupCreateDTO;
import org.springframework.academymanagement.dto.group.GroupGetDTO;
import org.springframework.academymanagement.dto.group.GroupUpdateDTO;
import org.springframework.academymanagement.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupGetDTO groupToGroupGetDTO(Group group);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    Group groupCreateDTOToGroup(GroupCreateDTO groupCreateDTO);

    @Mapping(target = "students", ignore = true)
    @Mapping(target = "id", ignore = true)
    void groupUpdateDTOToGroup(GroupUpdateDTO groupCreateDTO, @MappingTarget Group group);
}
