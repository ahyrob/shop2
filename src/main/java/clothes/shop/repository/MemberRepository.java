package clothes.shop.repository;


import clothes.shop.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",
                        Member.class)
                .setParameter("name", name)
                .getResultList();
    }


    // 아이디 찾을 때 이름이랑 이메일 있어야 돼
    public List<Member> findByNameAndEmail(String name, String email) {
        return em.createQuery("select m from Member m where m.name = :name and m.email = :email", Member.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .getResultList();
    }


}

