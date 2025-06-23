
package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.dto.LoginUserDto;
import toyproject.dto.RegisterRequestDto;
import toyproject.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // 회원가입
    public void registerUser(RegisterRequestDto dto) {
        // 현재는 별도 처리 없이 바로 DB에 저장(패스워드 암호화/복호화 미구현)
        userMapper.insertUser(dto);
    }

    // 로그인
    public LoginUserDto login(String email, String password) {
        LoginUserDto user = userMapper.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}