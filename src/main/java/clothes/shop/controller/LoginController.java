package clothes.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        log.info("login controller");
        return "login/login";
    }

    @RequestMapping("/id-finder")
    public String idFinder() {
        log.info("id finder");
        return "login/id-finder";
    }  @RequestMapping("/kakao-signup")
    public String kakaoSignup() {
        log.info("kakao sign up");
        return "login/kakao-signup";
    }  @RequestMapping("/order-inquiry-non-member1")
    public String orderInquiryNonMember1() {
        log.info("order inquiry nonmember1");
        return "login/order-inquiry-non-member1";
    }  @RequestMapping("/password-finder")
    public String passwordFinder() {
        log.info("password finder");
        return "login/password-finder";
    }  @RequestMapping("/signup")
    public String signUp() {
        log.info("signup");
        return "login/signup";
    }  @RequestMapping("/member-login")
    public String memberLogin() {
        log.info("member login");
        return "login/member-login";
    }

@RequestMapping("/signup2")
    public String signup2() {
        log.info("signup2");
        return "login/signup2";
    }

}
