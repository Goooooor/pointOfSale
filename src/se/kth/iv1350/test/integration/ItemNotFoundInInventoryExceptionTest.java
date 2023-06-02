package se.kth.iv1350.test.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundInInventoryException;

public class ItemNotFoundInInventoryExceptionTest {

    @Test
    public void exceptionShouldBeThrown() throws ItemNotFoundInInventoryException {
        InventorySystem inventorySystem = new InventorySystem();

        int invalidItemID = 4;
        assertThrows(ItemNotFoundInInventoryException.class, () -> inventorySystem.getItemInformation(invalidItemID),
                "ItemID " + invalidItemID + " was not found and exception should have been thrown ");
    }
}
