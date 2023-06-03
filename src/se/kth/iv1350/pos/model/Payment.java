package se.kth.iv1350.pos.model;

/**
 * This class is responsible for the amount paid by the customer and the change
 * given to the customer.
 */
public class Payment {

    private double amountPaid;

    private double change;

    /**
     * Creates and instance of the <code>Payment</code> class.
     * 
     * @param amountPaid The amount paid by the customer.
     * @param change     The change given to the customer.
     */
    public Payment(double amountPaid, double change) {
        this.amountPaid = amountPaid;
        this.change = Math.abs(change);
    }

    /**
     * Get amount paid.
     * 
     * @return <code>amountPaid</code>.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Get change.
     * 
     * @return <code>change</code>.
     */
    public double getChange() {
        return change;
    }
}
