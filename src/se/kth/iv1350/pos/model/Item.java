package se.kth.iv1350.pos.model;

/**
 * This is the Item class which is created in the <code>Sale</code> class once
 * an item is
 * added to the current sale.
 *
 */
public class Item {

	private int itemID;

	private ItemDTO itemInfo;

	private int quantity;

	/**
	 * Creates a new instance of the <code>Item</code> class.
	 * 
	 * @param itemID  Identifies each specific item.
	 * @param itemDTO Contains additional information about the items.
	 */
	public Item(int itemID, ItemDTO itemInfo) {
		this.itemID = itemID;
		this.itemInfo = itemInfo;
	}

	/**
	 * Increases the quantity of an item when called.
	 * 
	 */
	public void increaseQuantity() {
		quantity++;
	}

	/**
	 * The VAT of one specific item.
	 * 
	 * @return VAT of an item.
	 */
	public double getItemVAT() {
		return getItemInfo().getVATRate() * getItemInfo().getPrice();
	}

	/**
	 * Get the ID.
	 * 
	 * @return <code>itemID</code>.
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * Get item information.
	 * 
	 * @return <code>itemInfo</code>.
	 */
	public ItemDTO getItemInfo() {
		return itemInfo;
	}

	/**
	 * Get quantity of item.
	 *
	 * @return <code>quantity</code>.
	 */
	public int getQuantity() {
		return quantity;
	}
}
