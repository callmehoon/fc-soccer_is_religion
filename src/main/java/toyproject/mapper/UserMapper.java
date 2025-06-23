package toyproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyproject.controller.dto.LoginUserDto;
import toyproject.controller.dto.RegisterRequestDto;

@Mapper
public interface UserMapper {
    void insertUser(RegisterRequestDto dto);

    //  로그인 시 이메일로 사용자 조회
    LoginUserDto findByEmail(String email);
}