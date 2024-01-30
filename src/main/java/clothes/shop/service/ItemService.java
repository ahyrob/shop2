package clothes.shop.service;

import clothes.shop.domain.Category;
import clothes.shop.domain.item.Item;
import clothes.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(String name, int price, int stockQuantity, String newName, int newPrice, int newStockQuantity) {
        itemRepository.updateItem(name, price, stockQuantity, newName, newPrice, newStockQuantity);
    }


}
