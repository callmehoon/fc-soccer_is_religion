package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.service.TestService;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    @GetMapping("/tests")
    public String index(){
        return "index";
    }
}
