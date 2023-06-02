package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.view.View;

/**
 * This is where the application is executed from and all systems are started.
 * It has the main method.
 *
 */
public class Main {

	/**
	 * The main method used to start the program.
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		AccountingSystem accounting = new AccountingSystem();
		InventorySystem inventory = new InventorySystem();
		Printer printer = new Printer();
		Controller contr = new Controller(inventory, accounting, printer);
		View view = new View(contr);
		view.run();
		view.run();
	}
}
