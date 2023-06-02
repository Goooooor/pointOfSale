package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Receipt;

/**
 * Creates a new instance of the <code>Printer</code> class.
 * The Printer class is responsible for printing the receipt
 *
 */
public class Printer {

	/**
	 * Prints the receipt.
	 * 
	 * @param receipt the receipt to print.
	 */
	public void printReceipt(Receipt receipt) {
		System.out.println(receipt);
	}
}
