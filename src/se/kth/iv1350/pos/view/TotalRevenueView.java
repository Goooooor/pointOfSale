package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;

/**
 * Shows a running total of revenue collected from all finished sales.
 */
public class TotalRevenueView implements SaleObserver {

    private double totalRevenue = 0;

    /**
     * Adds the price of the finished sale to the <code>totalRevenue</code>.
     * 
     * @param salePrice The price of the finished sale.
     */
    @Override
    public void addNewSale(double salePrice) {
        totalRevenue += salePrice;
        System.out.println("### Total revenue: " + totalRevenue + " SEK ###\n");
    }
}
