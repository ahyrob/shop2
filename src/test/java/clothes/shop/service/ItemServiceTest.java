package clothes.shop.service;

import clothes.shop.domain.item.Item;
import clothes.shop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 상품_등록() throws Exception {
        //given
        Item item = new Item();
        item.setName("something");
        item.setPrice(100000);
        item.setStockQuantity(10);

        // When
        Long itemId = itemService.saveItem(item);

        // Then
        assertNotNull(itemId);
        Item savedItem = itemRepository.findOne(itemId);
        assertNotNull(savedItem);

        assertEquals(item.getName(), savedItem.getName());
        assertEquals(item.getPrice(), savedItem.getPrice());
    }

    @Test
    public void 상품_조회() throws Exception {
        //given
        Item item = new Item();
        item.setName("finditem");
        item.setPrice(10000);
        item.setStockQuantity(100);

        //when
        Long itemId = itemService.saveItem(item);
        Item saveitem = itemRepository.findOne(itemId);

        // then
        assertNotNull(saveitem);
        assertEquals(itemId, saveitem.getId());
        assertEquals("finditem", saveitem.getName());
        assertEquals(10000, saveitem.getPrice());
        assertEquals(100, saveitem.getStockQuantity());
    }

    @Test
    public void 상품_수정() throws Exception {
        //given
        Item item = new Item();
        item.setName("finditem");
        item.setPrice(10000);
        item.setStockQuantity(100);
        itemRepository.save(item);

        //when
        List<Item> updatedItems = itemRepository.updateItem("finditem", 10000, 100, "newfinditem", 20000, 200);

        //then
        assertFalse(updatedItems.isEmpty());

        for (Item updatedItem : updatedItems) {
            assertNotNull(updatedItem);
            assertEquals("newfinditem", updatedItem.getName());
            assertEquals(20000, updatedItem.getPrice());
            assertEquals(200, updatedItem.getStockQuantity());
        }
    }
}
