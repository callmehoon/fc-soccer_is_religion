package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.dto.RegisterRequestDto;
import toyproject.service.UserService;

@Controller
@RequiredArgsConstructor  // 🔥 자동 생성자 생성
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegisterRequestDto dto) {
        userService.registerUser(dto);  // ✅ 실제 서비스 연결
        System.out.println("회원가입 요청 = " + dto.getName());
        return "redirect:/register-success";
    }
}