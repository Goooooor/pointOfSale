package se.kth.iv1350.pos.integration;

/**
 * If an item is not found in the inventory this exception is thrown.
 */
public class ItemNotFoundException extends Exception {

    /**
     * Creates an instance of <code>ItemNotFoundInInventoryException</code>.
     * 
     * @param msg The message of the exception.
     */
    public ItemNotFoundException(int itemID) {
        super("No item with the itemID " + itemID + " was found");
    }
}
