package se.kth.iv1350.pos.model;

/**
 * 
 * One single receipt which is created for each sale.
 *
 */
public final class Receipt {

	private final double amountPaid;

	private final double change;

	private final Payment payment;

	private final Sale sale;

	/**
	 * Creates a new instance of the <code>Receipt</code> class.
	 * 
	 * @param payment The payment information of the sale.
	 * @param sale    The sale which has information about items sold.
	 */
	public Receipt(Payment payment, Sale sale) {
		this.payment = payment;
		amountPaid = payment.getAmountPaid();
		change = payment.getChange();
		this.sale = sale;
	}

	/**
	 * Get amount paid.
	 * 
	 * @return <code>amountPaid</code>
	 */
	public double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Get the change.
	 * 
	 * @return <code>change</code>
	 */
	public double getChange() {
		return change;
	}

	/**
	 * Returns the receipt information when a receipt is printed inside the Printer
	 * class.
	 */
	public String toString() {
		StringBuilder receipt = new StringBuilder();

		receipt.append("#################Gors livs##################\n");
		receipt.append("Time of sale: " + sale.getTimeOfSale() + "\n");

		for (int i = 0; i < sale.getItems().size(); i++) {
			receipt.append("\n" + sale.getItems().get(i).getItemInfo().getItemDescription() +
					"\n" + sale.getItems().get(i).getQuantity() + " st " +
					sale.getItems().get(i).getItemInfo().getPrice() + " SEK including " +
					sale.getItems().get(i).getItemInfo().getVATPrice() + " SEK VAT\n");
		}

		receipt.append("\nTotal price of sale: " + sale.getTotalPrice() + " SEK");
		receipt.append("\nPaid amount: " + payment.getAmountPaid() + " SEK");
		receipt.append("\nTotal VAT: " + sale.getTotalVAT() + " SEK");
		receipt.append("\nChange back: " + payment.getChange() + " SEK");
		receipt.append("\n############################################\n");

		return receipt.toString();
	}
}