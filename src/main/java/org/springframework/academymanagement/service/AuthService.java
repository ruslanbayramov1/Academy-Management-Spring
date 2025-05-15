package org.springframework.academymanagement.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.academymanagement.dto.auth.JwtTokenGenerateDTO;
import org.springframework.academymanagement.dto.auth.UserLoginDTO;
import org.springframework.academymanagement.dto.auth.UserRegisterDTO;
import org.springframework.academymanagement.entity.User;
import org.springframework.academymanagement.exception.NotFoundException;
import org.springframework.academymanagement.mapper.AuthMapper;
import org.springframework.academymanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;

    public void register(UserRegisterDTO dto) {
        if (!dto.password().equals(dto.passwordConfirm())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String hash = BCrypt.withDefaults().hashToString(11, dto.password().toCharArray());
        User user = authMapper.userRegisterDtoToUser(dto);
        user.setPasswordHash(hash);
        userRepository.save(user);
    }

    public String login(UserLoginDTO dto) {
        User user = userRepository.findByUsername(dto.username());
        if (user == null)
            throw new NotFoundException("User or password is incorrect");

        BCrypt.Result result = BCrypt.verifyer().verify(dto.password().toCharArray(), user.getPasswordHash().toCharArray());
        if (!result.verified)
            throw new NotFoundException("User or password is incorrect");

        // JWT
        JwtTokenGenerateDTO jwtDto = authMapper.userToJwtTokenGenerateDTO(user);
        return jwtService.generateToken(jwtDto);
    }
}
