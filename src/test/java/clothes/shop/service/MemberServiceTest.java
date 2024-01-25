package clothes.shop.service;

import clothes.shop.domain.Member;
import clothes.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        Long saveId = memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.join(member1);
        //memberService.join(member2); //예외가 발생해야 한다.
        //Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

    }

    @Test
    public void 로그인() throws Exception{
        // Given
        Member member = new Member();
        member.setName("testUser");
        member.setPassword("password123");
        Long memberId = memberService.join(member);

        // When
        Member loggedMember = memberService.login("testUser", "password123");

        // Then
        assertNotNull(loggedMember); // loggedMember가 null이 아닌지 확인
        assertEquals(memberId, loggedMember.getId()); // memberId와 loggedMember가 같은지 확인
    }

    @Test
    public void 아이디_찾기() throws Exception {
        //given
        Member member = new Member();
        member.setName("finduser");
        member.setEmail("findemail@example.com");
        Long memberId = memberService.join(member);

        //when
        String idFindMember = memberService.findLoginIdByNameAndEmail("finduser", "findemail@example.com");

        //then
        assertEquals(member.getLoginId(), idFindMember);
    }

}