package org.springframework.academymanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.academymanagement.dto.auth.JwtTokenGenerateDTO;
import org.springframework.academymanagement.dto.auth.UserRegisterDTO;
import org.springframework.academymanagement.entity.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User userRegisterDtoToUser(UserRegisterDTO dto);

    JwtTokenGenerateDTO userToJwtTokenGenerateDTO(User user);
}
