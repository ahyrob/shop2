package clothes.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class AdminItem {
    @Id
    @GeneratedValue
    @Column(name = "admin_item_id")
    private Long id;

    private LocalDateTime adminDate;

    // 2.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    // 3.
    /*
    @OneToOne(mappedBy = "admin_item")
*/
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
