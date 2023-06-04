package se.kth.iv1350.test.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;

public class UnreachableDatabaseExceptionTest {

    @Test
    public void exceptionShouldBeThrown() {
        InventorySystem inventorySystem = new InventorySystem();

        int invalidItemID = 3;
        assertThrows(UnreachableDatabaseException.class, () -> inventorySystem.getItemInformation(invalidItemID),
                "Exception should have been thrown because database crashed ");
    }
}
