package web.mvc.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.mvc.SecurityUtils;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "안녕하세요. 홈페이지입니다.";
    }

    @RequestMapping("/user_test")
    public String user_test() {
        System.out.println("HomeController.user_test");
        UserDetails userDetails = SecurityUtils.currentUser();
        System.out.println("인증된 사용자: " + userDetails.getUsername());
        return "로그인 사용자입니다.";
    }
}
