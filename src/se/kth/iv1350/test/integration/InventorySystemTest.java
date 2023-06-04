package se.kth.iv1350.test.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;
import se.kth.iv1350.pos.model.Sale;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    private ItemDTO testItemInfo;

    private Sale sale;

    @BeforeEach
    public void prepareTest() {
        inventorySystem = new InventorySystem();
        sale = new Sale();
        testItemInfo = new ItemDTO("Whole chicken 1,5 kg", 100, 0.12, 30);

    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        sale = null;
        testItemInfo = null;
    }

    @Test
    void testGetItemInformation() throws ItemNotFoundException, UnreachableDatabaseException {
        Assertions.assertEquals(testItemInfo, inventorySystem.getItemInformation(0),
                "The items should be equal but are not the same");
        Assertions.assertNotEquals(testItemInfo, inventorySystem.getItemInformation(1),
                "The items should not be eqaul");
    }

    @Test
    void testUpdateInventory() throws UnreachableDatabaseException, ItemNotFoundException {
        sale.registerItem(0);
        sale.registerItem(1);
        inventorySystem.updateInventory(sale);
        Assertions.assertTrue(inventorySystem.getItemInformation(0).getInStock() == 29,
                "Inventory was not updated correctly");
        Assertions.assertFalse(inventorySystem.getItemInformation(1).getInStock() == 85,
                "Inventory was not updated correctly");
    }
}
