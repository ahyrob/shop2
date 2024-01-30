package clothes.shop.repository;

import clothes.shop.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }


    //category 제외
    public List<Item> updateItem(String name, int price, int stockQuantity, String newName, int newPrice, int newStockQuantity) {
        List<Item> items = em.createQuery("select i from Item i where i.name = :name and i.price = :price and i.stockQuantity = :stockQuantity", Item.class)

                .setParameter("name", name)
                .setParameter("price", price)
                .setParameter("stockQuantity", stockQuantity)
                .getResultList();

        if (!items.isEmpty()) {
            Item item = items.get(0);
            item.setName(newName);
            item.setPrice(newPrice);
            item.setStockQuantity(newStockQuantity);
        }
        return items;
    }


}
