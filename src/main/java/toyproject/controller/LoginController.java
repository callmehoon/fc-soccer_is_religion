package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        // 이메일 정규식 : 서버단에서 이메일 형식 유효성 검증 - js에서 검증 및 서버 체크
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            redirectAttributes.addFlashAttribute("error", "올바른 이메일 형식이 아닙니다.");
            return "redirect:/login";
        }

        try {
            LoginUserDto user = userService.login(email, password);
            request.getSession().setAttribute("loginUser", user);
            //다른 컨트롤러나 JSP에서 sessionScope.loginUser로 접근
            return "redirect:/main"; // 로그인 성공 후 메인 페이지로 이동
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login"; // 실패 시 다시 로그인 폼으로
        }
    }
    // 메인페이지 이동
    @GetMapping("/main")
    public String home(Model model) {
        model.addAttribute("contentPage", "rolling.jsp");
        return "main";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // 세션 완전 초기화
        return "redirect:/main"; // 로그아웃 후 메인으로 이동
    }
}