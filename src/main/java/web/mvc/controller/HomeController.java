package web.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "안녕하세요. 홈페이지입니다.";
    }

    @GetMapping("/user_test")
    public String user_test() {
        System.out.println("HomeController.user_test");
        return "로그인 사용자입니다.";
    }
}
