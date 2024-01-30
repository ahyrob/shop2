package clothes.shop.service;

import clothes.shop.domain.RewardPoints;
import clothes.shop.repository.RewardPointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardPointsService {

    private final RewardPointsRepository rewardPointsRepository;

    @Transactional(readOnly = true)
    public RewardPoints findOne(Long id) {
        return rewardPointsRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<RewardPoints> findAll() {
        return rewardPointsRepository.findAll();
    }

    public int calculateTotalRewardPoints(Long id) {
        return 0;
    }
}
