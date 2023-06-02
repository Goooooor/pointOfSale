package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Sale;

/**
 * Updates the accounting with the sale information.
 *
 */
public class AccountingSystem {

	private double runningTotalExcludingVAT;

	private double totalVATaccumulatedInSales;

	/**
	 * Updates the accounting system with the total amount price of the items sold.
	 * 
	 * @param sale Takes in the finished sale to update the accounting system.
	 */
	public void updateAccounting(Sale sale) {
		totalVATaccumulatedInSales = +sale.getTotalVAT();
		runningTotalExcludingVAT = +sale.getTotalSalesExcludingVAT();
	}

	/**
	 * Get running total price of all finished sales excluding VAT.
	 * 
	 * @return
	 */
	public double getRunningTotalExcludingVAT() {
		return runningTotalExcludingVAT;
	}

	/**
	 * Get total VAT.
	 * 
	 * @return <code>totalVATaccumulatedInSales</code>.
	 */
	public double getTotalVATaccumulatedInSales() {
		return totalVATaccumulatedInSales;
	}
}
