package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.controller.dto.RegisterRequestDto;
import toyproject.service.UserService;

@Controller
@RequiredArgsConstructor  // 자동 생성자 생성
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDto requestDto,
                           RedirectAttributes redirectAttributes) {
        String email = requestDto.getEmail();

        // 이메일 정규식 검사
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            redirectAttributes.addFlashAttribute("error", "올바른 이메일 형식이 아닙니다.");
            return "redirect:/register";
        }

        try {
            // USER_ID 생성
            String userId = userService.generateNewUserId();
            requestDto.setUserId(userId);

            // 회원가입 처리
            userService.register(requestDto);

            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입 실패: " + e.getMessage());
            return "redirect:/register";
        }
    }

}