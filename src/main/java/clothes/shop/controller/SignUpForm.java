package clothes.shop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpForm {

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;

    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 입니다.")
    private String password2;

    @NotEmpty(message = "휴대폰 번호는 필수 입니다.")
    private String phone;


}
