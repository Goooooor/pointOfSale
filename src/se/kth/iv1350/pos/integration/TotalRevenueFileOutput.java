package se.kth.iv1350.pos.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.pos.model.SaleObserver;

/**
 * Prints the running total of the revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {

    private double totalRevenue = 0;

    private PrintWriter writer;

    /**
     * Creates an instance of <code>TotalRevenueFileOutput</code>.
     */
    public TotalRevenueFileOutput() {
        try {
            writer = new PrintWriter(new FileWriter("revenue.txt", true));
        } catch (IOException ioe) {
            System.out.println("CAN NOT OUTPUT TO FILE");
            ioe.printStackTrace();
        }
    }

    /**
     * Adds the price of the finished sale to the <code>totalRevenue</code>.
     * 
     * @param salePrice The price of the finished sale.
     */
    @Override
    public void addNewSale(double salePrice) {
        totalRevenue += salePrice;
        writer.println("Total revenue: " + totalRevenue);
        writer.flush();
    }
}
