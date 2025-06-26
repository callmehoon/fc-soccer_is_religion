
package toyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.controller.dto.LoginUserDto;
import toyproject.controller.dto.RegisterRequestDto;
import toyproject.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;



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

    // RegisterController에서... 회원가입
    public void register(RegisterRequestDto requestDto) {
        String userId = generateNewUserId();
        requestDto.setUserId(userId);
        userMapper.insertUser(requestDto);
    }

    // USER_ID(사내 관리용 회원부여번호) 신규 생성
    public String generateNewUserId() {
        String maxId = userMapper.getMaxUserId();  // e.g. "U00127"
        int nextNum = 1;
        if (maxId != null && maxId.length() == 6) {
            nextNum = Integer.parseInt(maxId.substring(1)) + 1;
        }
        return String.format("U%05d", nextNum);
    }
}