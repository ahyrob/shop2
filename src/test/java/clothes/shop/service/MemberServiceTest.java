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

    @Autowired
    MemberService memberService;
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
    public void 아이디_찾기() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setLoginId("testid");
        member1.setEmail("test@example.com");
        member1.setName("testname");
        // When
        memberService.join(member1);

        //then
        assertEquals(member1.getLoginId(), memberRepository.findLoginIdByNameAndEmail("testname", "test@example.com"));
    }

    @Test
    public void 비밀번호_변경() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("pwname");
        member1.setLoginId("pwloginid");
        member1.setEmail("pw@example.com");
        member1.setPassword("pw");

        // when
        memberService.join(member1);
        memberService.updatePassword("pwname", "pwloginid", "pw@example.com", "newpw");

        //then
        assertTrue(member1.getPassword().equals("newpw")); // assertEquals보다 assertTrue 사용(비밀번호가 변경된 경우에만 테스트가 성공해야 하기 때문)
    }

    @Test
    public void 로그인() throws Exception {
        // Given
        String loginId = "testuser";
        String password = "testpassword";

        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        memberRepository.save(member);

        // When
        Member loggedMember = memberService.login(loginId, password);

        // Then
        assertNotNull(loggedMember);
        assertEquals(loginId, loggedMember.getLoginId());
    }

    @Test
    public void 회원정보_수정() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId(1L);
        member1.setPhone("010-1234-5678");
        member1.setEmail("update@example.com");

        //when
        memberService.updateEmailAndPhone(1L,"newupdate@example.com", "010-5678-1234");

        //then
        assertTrue(member1.getEmail().equals("newupdate@example.com"));
        assertTrue(member1.getPhone().equals("010-5678-1234"));

    }

    @Test
    public void 회원정보_수정2() throws Exception {
        //given
        Member member1 = new Member();
        member1.setPhone("010-1234-5678");
        member1.setEmail("update@example.com");

        //when
        memberService.join(member1);
        Long memberId = member1.getId();
        memberService.updateEmailAndPhone2("update@example.com","010-1234-5678","newupdate@example.com", "010-9874-5678" );

        //then
        Member updatedMember = memberRepository.findOne(memberId);
        assertEquals("newupdate@example.com", updatedMember.getEmail());
        assertEquals("010-9874-5678", updatedMember.getPhone());

    }


}