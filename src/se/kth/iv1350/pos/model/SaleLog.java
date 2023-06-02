package se.kth.iv1350.pos.model;

import java.util.ArrayList;

/**
 * Logs the sale information.
 *
 */
public class SaleLog {

	private ArrayList<Sale> finishedSales = new ArrayList<>();

	/**
	 * Collects all the finished sales in a log.
	 * 
	 * @param sale The sale to be stored.
	 */
	public void updateSaleLog(Sale sale) {
		finishedSales.add(sale);
	}

	/**
	 * Get the finshed sales.
	 * 
	 * @return <code>finishedSales</code>
	 */
	public ArrayList<Sale> getFinishedSales() {
		return finishedSales;
	}

}
