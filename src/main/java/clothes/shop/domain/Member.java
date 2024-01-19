package clothes.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    // 1.
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //@NotEmpty
    private String loginId; // 사용자 아이디
    //@NotEmpty
    private String name; // 사용자 이름
    //@NotEmpty
    private String password;
    @Embedded
    private Address address;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    // 3.

    @OneToMany(mappedBy = "member")
    private List<RewardPoints> rewardPointsList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberCoupon> memberCoupons = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<RecentlyViewedItem> recentlyViewedItems = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Cart cart;

    @OneToMany(mappedBy = "member")
    private List<MemberWishList> memberWishList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();



    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();


}
