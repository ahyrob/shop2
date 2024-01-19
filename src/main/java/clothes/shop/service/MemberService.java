package clothes.shop.service;

import clothes.shop.domain.Member;
import clothes.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional //변경
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 로그인
    public Member login(String name, String password) {
        Member findMembers = findMemberByName(name);

        if (findMembers != null && findMembers.getPassword().equals(password)) {
            return findMembers;
        } else {
            throw new IllegalStateException("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    public Member findMemberByName(String name) {
        List<Member> findMembers = memberRepository.findByName(name);
        if (findMembers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findMembers.get(0); // 중복 이름이 없다고 가정
    }



}
