package clothes.shop.repository;

import clothes.shop.domain.RewardPoints;
import clothes.shop.domain.RewardStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RewardPointsRepository {

    private EntityManager em;

    public void save(RewardPoints rewardPoints) {
        em.persist(rewardPoints);
    }

    public RewardPoints findOne(Long id) {
        return em.find(RewardPoints.class, id);
    }

    public List<RewardPoints> findAll() {
        return em.createQuery("select r from RewardPoints r", RewardPoints.class)
                .getResultList();
    }

    public List<RewardStatus> findByStatus(RewardStatus rewardStatus) {
        return em.createQuery("select r from RewardPoints r where r.rewardStatus=: rewardStatus", RewardStatus.class)
                .getResultList();
    }

    public List<RewardStatus> findAvailablePoints() {
        return findByStatus(RewardStatus.AVAILABLE);
    }


}
