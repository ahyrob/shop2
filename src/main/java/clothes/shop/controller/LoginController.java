package clothes.shop.controller;

import clothes.shop.domain.Member;
import clothes.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    //@RequiredArgsConstructor 쓰면 @Autowired 부터 생략 가능
   /* @Autowired // 생략 가능
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }*/

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
    }




    @RequestMapping("/signup")
    public String signUp() {
        log.info("signup");
        return "login/signup";




    }  @RequestMapping("/member-login")
    public String memberLogin() {
        log.info("member login");
        return "login/member-login";
    }

/*@RequestMapping("/signup2")
    public String signup2() {
        log.info("signup2");
        return "login/signup2";
    }*/
@RequestMapping("/signup2")
    @GetMapping("/login/signup2")
    public String showSignUpForm(Model model) {
        log.info("signup2");
        model.addAttribute("signUpForm", new SignUpForm());
        return "login/signup2";
    }

    @PostMapping("/login/signup2")
    public String signUp(@Valid SignUpForm signUpForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login/signup2";
        }

        Member member = new Member();
        member.setLoginId(signUpForm.getLoginId());
        member.setEmail(signUpForm.getEmail());
        member.setName(signUpForm.getName());
        member.setPassword(signUpForm.getPassword());
        member.setPhone(signUpForm.getPhone());

        try {
            Long memberId = memberService.join(member);
            log.info("회원가입 완료");
            model.addAttribute("successMessage", "회원 가입이 성공적으로 완료되었습니다. 회원 ID: " + memberId);
        } catch (IllegalStateException e) {
            log.info("회원가입 미완료");
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "/";
    }

    @GetMapping("/login/id-finder")
    public String showIdFinderForm(Model model) {
        model.addAttribute("idFinderForm", new IdFInderForm());
        return "login/idFinderForm";
    }

    @PostMapping("/login/id-finder")
    public String findLoginId(@Valid IdFInderForm idFinderForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login/idFinderForm";
        }

        try {
            String foundLoginId = memberService.findLoginIdByNameAndEmail(idFinderForm.getName(), idFinderForm.getEmail());
            model.addAttribute("foundLoginId", foundLoginId);
        } catch (IllegalStateException e) {
            model.addAttribute("notFoundMessage", e.getMessage());
        }

        return "login/idFinderResult";
    }
    }


