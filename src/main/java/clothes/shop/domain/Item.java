package clothes.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item {
    // 1.
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private int discountAmount;

    // 2.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_item_id")*/
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item")
    private AdminItem adminItem;

    // 3.

    @OneToMany(mappedBy = "item")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<WishList> wishLists = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToMany(mappedBy = "item")
    private List<RecentlyViewedItem> recentlyViewedItems = new ArrayList<>();


}
