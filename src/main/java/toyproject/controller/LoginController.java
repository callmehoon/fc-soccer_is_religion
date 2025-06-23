package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.controller.dto.LoginUserDto;
import toyproject.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 로그인 폼 화면
    @GetMapping("/login")
    public String loginForm() {
        return "user_login";  // /WEB-INF/views/user_login.jsp
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        try {
            LoginUserDto user = userService.login(email, password);
            request.getSession().setAttribute("loginUser", user);
            return "redirect:/main"; // 로그인 성공 후 메인 페이지로 이동
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login"; // 실패 시 다시 로그인 폼으로
        }
    }
}