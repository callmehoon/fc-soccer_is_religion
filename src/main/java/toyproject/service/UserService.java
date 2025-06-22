package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.RegisterRequestDto;
import toyproject.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void registerUser(RegisterRequestDto dto) {
        // 현재는 별도 처리 없이 바로 DB에 저장(패스워드 암호화/복호화 미구현)
        userMapper.insertUser(dto);
    }
}