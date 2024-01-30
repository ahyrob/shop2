package clothes.shop.service;

import clothes.shop.domain.Member;
import clothes.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    @Transactional(readOnly = true)
    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    // 아이디 조회
    @Transactional(readOnly = true)
    public String findIdByNameAndEmail(String name, String email) {
        return memberRepository.findLoginIdByNameAndEmail(name, email);
    }

    // 비밀번호 수정
    public void updatePassword(String name, String loginId, String email, String newPassword) {

        memberRepository.updatePassword(name, loginId, email, newPassword);
    }

    // 로그인
    public Member login(String loginId, String password) {
        return memberRepository.login(loginId, password);
    }

    // 회원 정보 수정
    public void updateEmailAndPhone(String email, String phone, String newEmail, String newPhone) {
        memberRepository.updateEmailAndPhone(email, phone,newEmail,newPhone);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }




}
