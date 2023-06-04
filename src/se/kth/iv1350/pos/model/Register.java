package se.kth.iv1350.pos.model;

/**
 * The register class.
 *
 */
public class Register {

	private double amountInRegister = 5000;

	/**
	 * Creates a new receipt and increases the amount in the register with the
	 * amount paid.
	 * 
	 * @param amountPaid Entered by the cashier and is the paid amount by the
	 *                   customer.
	 * @param change     The change back for the sale.
	 * @param sale       The current sale by the customer
	 * @return The receipt of the sale.
	 */
	public Receipt registerPayment(Payment payment, Sale sale) {
		increaseAmountInRegister(payment.getAmountPaid());
		return new Receipt(payment, sale);
	}

	/**
	 * Increases the amount in register with the amount paid by the customer.
	 * 
	 * @param amountPaid The amount paid by the customer.
	 */
	private void increaseAmountInRegister(double amountPaid) {
		amountInRegister += amountPaid;
	}

	/**
	 * Get amount in register
	 * 
	 * @param amountPaid <code>amountInRegister</code>.
	 */
	public double getAmountInRegister() {
		return amountInRegister;
	}
}
