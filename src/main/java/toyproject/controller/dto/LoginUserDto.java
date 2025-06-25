package toyproject.controller.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    //DB에서 조회한 결과를 담는 역할
    private String email;
    private String password;
    private String userId;

    //생성자
    public LoginUserDto(String email, String password, String userId) {
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    //Getter
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUserId() {
        return userId;
    }
}

