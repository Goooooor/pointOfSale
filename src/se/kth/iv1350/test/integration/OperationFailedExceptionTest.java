package se.kth.iv1350.test.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.OperationFailedException;
import se.kth.iv1350.pos.integration.Printer;

public class OperationFailedExceptionTest {

    private InventorySystem inventorySystem;

    private AccountingSystem accountingSystem;

    private Printer printer;

    @BeforeEach
    public void prepareTest() {
        inventorySystem = new InventorySystem();
        accountingSystem = new AccountingSystem();
        printer = new Printer();
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        accountingSystem = null;
        printer = null;
    }

    @Test
    public void exceptionShouldBeThrown() {
        Controller contr = new Controller(inventorySystem, accountingSystem, printer);
        contr.createNewSale();
        assertThrows(OperationFailedException.class, () -> contr.registerItem(3),
                "Exception should have been thrown because database crashed ");
    }
}
