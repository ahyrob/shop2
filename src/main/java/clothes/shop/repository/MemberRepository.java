package clothes.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import clothes.shop.domain.Member;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository // spring bean 등록
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // 단건조회 (type, pk)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql-> 프로그램의 대상이 테이블이 아니라 entity
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // id 조회
    public String findLoginIdByNameAndEmail(String name, String email) {
        return em.createQuery("select m.loginId from Member m where m.name = :name and m.email = :email", String.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    //password 수정
    // 1.
    public void updatePassword(String name, String loginId, String email, String newPassword) {
        List<Member> members = em.createQuery("select m from Member m where m.name =:name and m.loginId=:loginId and m.email=:email", Member.class)
                .setParameter("name", name)
                .setParameter("loginId", loginId)
                .setParameter("email", email)
                .getResultList();

        if (!members.isEmpty()) {
            Member member = members.get(0);
            member.setPassword(newPassword);
        }
    }

    // 2.
    public void updatePassword2(Long memberId, String newPassword) {
        Member members = em.find(Member.class, memberId);
        if (members != null) {
            members.setPassword(newPassword);
            em.merge(members);
        }
    }

    // 로그인
    public Member login(String loginId, String password) {
        List<Member> members = em.createQuery("select m from Member m where m.loginId=:loginId and m.password=:password", Member.class)
                .setParameter("loginId", loginId)
                .setParameter("password", password)
                .getResultList();

        if (!members.isEmpty()) {
            return members.get(0);
        } else {
            return null;
        }
    }

    // 회원 정보 수정 email, phone
    public void updateEmailAndPhone(Long memberId, String newEmail, String newPhone) {
        Member member = em.find(Member.class, memberId);
        if (member != null) {
            member.setEmail(newEmail);
            member.setPhone(newPhone);
            em.merge(member);
        }
    }

    public void updateEmailAndPhone2(String email, String phone, String newEmail, String newPhone) {
        List<Member> members = em.createQuery("select m from Member m where m.email=:email and m.phone=:phone", Member.class)
                .setParameter("email", email)
                .setParameter("phone", phone)
                .getResultList();

        if (!members.isEmpty()) {
            Member member = members.get(0);
            member.setEmail(newEmail);
            member.setPhone(newPhone);
        }
    }




}
