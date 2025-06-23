package toyproject.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    //DB에서 조회한 결과를 담는 역할
    private String email;
    private String password;
}

