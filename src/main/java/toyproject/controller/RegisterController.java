package toyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.dto.RegisterRequestDto;
import toyproject.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegisterRequestDto dto) {
        userService.registerUser(dto);
        return "redirect:/register-success";

    @GetMapping("/register")
    public String registerForm() {
        return "register";  //
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegisterRequestDto dto) {
        // DB 저장, 비밀번호 암호화, 유효성 검사 등의 처리를 담당을 여기 컨트롤러에서 연결 예정
        System.out.println("회원가입 요청 = " + dto.getName());
        return "redirect:/register-success"; // 성공 시 이동할 페이지(회원가입 완료 화면 또는 메인페이지)
    }
}