package se.kth.iv1350.pos.model;

/**
 * Additional item information.
 *
 */
public final class ItemDTO {

	private final String itemDescription;

	private final double price;

	private final double VATRate;

	private final int inStock;

	/**
	 * Creates a new instance of the <code>ItemDTO</code> class.
	 * 
	 * @param itemDescription Describes the item.
	 * @param price           The price of the item.
	 * @param VATRate         The vat rate of the item.
	 * @param inStock         Amount of items in stock.
	 */
	public ItemDTO(String itemDescription, double price, double VATRate, int inStock) {
		this.itemDescription = itemDescription;
		this.price = price;
		this.VATRate = VATRate;
		this.inStock = inStock;
	}

	/**
	 * Get the description of item.
	 * 
	 * @return <code>itemDescription</code>.
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Get the ID.
	 * 
	 * @return <code>price</code>.
	 */
	public double getPrice() {

		return price;
	}

	/**
	 * Get the VATRate.
	 * 
	 * @return <code>VATRate</code>.
	 */
	public double getVATRate() {
		return VATRate;
	}

	/**
	 * Get the VAT price.
	 * 
	 * @return The VAT price
	 */
	public double getVATPrice() {
		return price * VATRate;
	}

	/**
	 * Get amount of items in stock.
	 * 
	 * @return <code>itemID</code>.
	 */
	public int getInStock() {
		return inStock;
	}

	/**
	 * Overrides the equals method so that if the names or VATRate of two objects
	 * are equal it returns true.
	 * 
	 * @param The object to compare against.
	 */
	public boolean equals(Object obj) {
		ItemDTO item = (ItemDTO) obj;
		if (this.itemDescription.equals(item.getItemDescription()))
			return true;
		return false;
	}

	/**
	 * Overrides the toString method. Used when an item is added to the sale so that
	 * information about the item is seen.
	 * 
	 * @return The item toString method.
	 */
	public String toString() {
		StringBuilder item = new StringBuilder();
		item.append(getItemDescription());
		item.append(
				"\nPrice: " + getPrice() + " SEK, including " + getVATPrice() + " SEK VAT");
		return item.toString();
	}
}