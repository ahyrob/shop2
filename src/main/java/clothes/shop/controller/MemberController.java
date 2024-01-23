package clothes.shop.controller;

import clothes.shop.domain.Member;
import clothes.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signUp(@RequestBody MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setLoginId(memberRequest.getLoginId());
        member.setPassword(memberRequest.getPassword());
        member.setPhone(memberRequest.getPhone());

        return memberService.join(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = new Member();
        member.setId(id);
        member.setName("John Doe");
        member.setEmail("john@example.com");

        return ResponseEntity.ok(member);
    }
    public static class MemberRequest {
        private String name;
        private String loginId;
        private String password;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
