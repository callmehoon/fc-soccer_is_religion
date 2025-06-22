// src/main/java/toyproject/service/UserService.java
package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import toyproject.dto.RegisterRequestDto;
import toyproject.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequestDto dto) {
        // 비밀번호 암호화
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userMapper.insertUser(dto);
    }
}