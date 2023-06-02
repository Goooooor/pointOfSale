package se.kth.iv1350.pos.model;

/**
 * This is interface is implemented if a class is interested in when a sale is
 * finished.
 */
public interface SaleObserver {

    /**
     * 
     * @param salePrice
     */
    void addNewSale(double salePrice);
}
