package clothes.shop.service;

import clothes.shop.domain.Member;
import clothes.shop.domain.RewardPoints;
import clothes.shop.domain.RewardStatus;
import clothes.shop.repository.MemberRepository;
import clothes.shop.repository.RewardPointsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RewardPointsServiceTest {

    @Autowired
    RewardPointsRepository rewardPointsRepository;
    @Autowired
    RewardPointsService rewardPointsService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 총적립금 () throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);

        RewardPoints rewardPoints1 = new RewardPoints();
        rewardPoints1.setMember(member);
        rewardPoints1.setRewardAmount(5000);
        rewardPoints1.setRewardStatus(RewardStatus.AVAILABLE);
        rewardPointsRepository.save(rewardPoints1);

        RewardPoints rewardPoints2 = new RewardPoints();
        rewardPoints2.setMember(member);
        rewardPoints2.setRewardAmount(5000);
        rewardPoints2.setRewardStatus(RewardStatus.USED);
        rewardPointsRepository.save(rewardPoints2);

        // when
        int totalRewardPoints = rewardPointsService.calculateTotalRewardPoints(member.getId());

        // then
        assertEquals(5000, totalRewardPoints); // Assuming USED points are not included in the total

     }

}
