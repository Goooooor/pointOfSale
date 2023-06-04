package se.kth.iv1350.test.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.integration.OperationFailedException;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;
import se.kth.iv1350.pos.view.View;

class ViewTest {

	private View instanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;

	@BeforeEach
	void setUp() {
		AccountingSystem accounting = new AccountingSystem();
		InventorySystem inventory = new InventorySystem();
		Printer printer = new Printer();
		Controller contr = new Controller(inventory, accounting, printer);
		instanceToTest = new View(contr);

		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}

	@AfterEach
	void tearDown() {
		instanceToTest = null;

		printoutBuffer = null;
		System.setOut(originalSysOut);
	}

	@Test
	void test() throws OperationFailedException, ItemNotFoundException, UnreachableDatabaseException {
		instanceToTest.run();
		String printout = printoutBuffer.toString();
		String expectedOutput = "started";
		assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
	}

}
