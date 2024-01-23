package clothes.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController {
    @RequestMapping("/login/login")
    public String login() {
        log.info("login controller");
        return "login/login";
    }

    @RequestMapping("/login/id-finder")
    public String idFinder() {
        log.info("id finder");
        return "login/id-finder";
    }  @RequestMapping("/login/kakao-signup")
    public String kakaoSignup() {
        log.info("kakao sign up");
        return "login/kakao-signup";
    }  @RequestMapping("/login/order-inquiry-non-member")
    public String orderInquiryNonMember() {
        log.info("order inquiry nonmember");
        return "login/order-inquiry-non-member";
    }  @RequestMapping("/login/password-finder")
    public String passwordFinder() {
        log.info("password finder");
        return "login/password-finder";
    }  @RequestMapping("/login/signup")
    public String singUp() {
        log.info("signup");
        return "login/signup";
    }  @RequestMapping("/login/member-login")
    public String memberLogin() {
        log.info("member login");
        return "login/member-login";
    }
}
