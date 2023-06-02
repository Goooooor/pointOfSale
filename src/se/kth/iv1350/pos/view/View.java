package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.pos.integration.OperationFailedException;
import se.kth.iv1350.pos.integration.TotalRevenueFileOutput;
import se.kth.iv1350.pos.util.FileLogger;

/**
 * This is a placeholder for the real view.
 * It contains a hardcoded execution with calls to all system operations in the
 * controller
 *
 */
public class View {

	private Controller contr;

	private FileLogger fileLogger;

	public View(Controller contr) {
		this.contr = contr;
		this.fileLogger = new FileLogger();
		contr.addSaleObserver(new TotalRevenueView());
		contr.addSaleObserver(new TotalRevenueFileOutput());
	}

	/**
	 * Runs a fake test run of the program.
	 * 
	 */
	public void run() {
		contr.createNewSale();
		System.out.println("\n***A new sale has started***");
		System.out.println();

		// Use 0 as parameter for regular flow
		// Use 3 as parameter for fake database failure, OperationFailedException
		// Use 4 or higher as parameter for ItemNotFoundInInventoryException
		testFlow(0);
	}

	private void testFlow(int itemID) {
		try {
			System.out.println("//////Added to sale//////");
			System.out.println(contr.registerItem(0));
			System.out.println("\nRunning total: " + contr.getRunningTotal() + " SEK");
			System.out.println();

			System.out.println("//////Added to sale//////");
			System.out.println(contr.registerItem(1));
			System.out.println("\nRunning total: " + contr.getRunningTotal() + " SEK");
			System.out.println();

			System.out.println("//////Added to sale//////");
			System.out.println(contr.registerItem(2));
			System.out.println("\nRunning total: " + contr.getRunningTotal() + " SEK");
			System.out.println();

			System.out.println("//////Added to sale//////");
			System.out.println(contr.registerItem(0));
			System.out.println("\nRunning total: " + contr.getRunningTotal() + " SEK");
			System.out.println();

			System.out.println("//////Added to sale//////");
			System.out.println(contr.registerItem(itemID));
			System.out.println("\nRunning total: " + contr.getRunningTotal() + " SEK");
			System.out.println();
		} catch (OperationFailedException e) {
			System.out.println("The system has technical issues at the moment, please contact support\n");
			fileLogger.log(e);
		} catch (ItemNotFoundInInventoryException infiie) {
			System.out.println("The item with itemID " + itemID + " is not valid");
		}

		System.out.println("\n***The sale has ended***");
		System.out.println("------------------------\n");

		if (!(itemID == 3 || itemID >= 4)) {
			// Ending sale
			System.out.println("To pay: " + contr.endSale() + " kr");

			// Payment
			System.out.println("Customer pays: 500 kr");
			contr.pay(500);
		}

	}
}
