package toyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "user_login";  // /WEB-INF/views/user_login.jsp 로 매핑됨
    }
}