package se.kth.iv1350.test.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.ItemDTO;

public class ItemTest {

    private Item item;

    private ItemDTO itemInfo;

    @BeforeEach
    public void prepareTest() {
        itemInfo = new ItemDTO("Whole chicken 1,5 kg", 100, 0.12, 30);
        item = new Item(0, itemInfo);
    }

    @AfterEach
    public void tearDown() {
        itemInfo = null;
        item = null;
    }

    @Test
    void testIncreaseQuantity() {
        item.increaseQuantity();
        Assertions.assertEquals(1, item.getQuantity(), "The quantity was not increased");
    }
}
