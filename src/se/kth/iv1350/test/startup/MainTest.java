package se.kth.iv1350.test.startup;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.pos.integration.OperationFailedException;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;
import se.kth.iv1350.pos.startup.Main;

class MainTest {

	private Main instanceToTest;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;

	@BeforeEach
	void setUp() {
		instanceToTest = new Main();
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
	void testUIHasStarted()
			throws OperationFailedException, ItemNotFoundInInventoryException, UnreachableDatabaseException {
		String[] args = null;
		Main.main(args);
		String printout = printoutBuffer.toString();
		String expectedOutpunt = "started";
		assertTrue(printout.contains(expectedOutpunt), "UI did not start correctly");
	}
}
