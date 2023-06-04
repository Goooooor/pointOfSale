package se.kth.iv1350.test.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundException;

public class ItemNotFoundExceptionTest {

    @Test
    public void exceptionShouldBeThrown() throws ItemNotFoundException {
        InventorySystem inventorySystem = new InventorySystem();

        int invalidItemID = 4;
        assertThrows(ItemNotFoundException.class, () -> inventorySystem.getItemInformation(invalidItemID),
                "ItemID " + invalidItemID + " was not found and exception should have been thrown ");
    }
}
