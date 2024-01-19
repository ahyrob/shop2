package clothes.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class OrderItem {

    // 1.
    @Id
    @GeneratedValue
    @Column(name = "orderitem_id")
    private Long id;

    private int quantity;
    private int price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

   private boolean refundCheck;

    // 2.
    @ManyToOne(fetch = FetchType.LAZY) // 흠
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //3.



}
