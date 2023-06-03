package se.kth.iv1350.pos.model;

import java.time.LocalTime;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;

/**
 * One single sale made by one single customer payed by one payment
 *
 */
public class Sale {

	private ArrayList<SaleObserver> saleObservers;

	private LocalTime saleTime;

	private InventorySystem inventorySystem;

	private ArrayList<Item> items;

	private double runningTotal;

	private double totalPrice;

	private double totalVAT;

	private double totalSalesExcludingVAT;

	/**
	 * Creates a new <code>Sale</code> with new items list, sale time and inventory
	 * system
	 * object.
	 */
	public Sale() {
		saleTime = LocalTime.now();
		items = new ArrayList<>();
		inventorySystem = new InventorySystem();
	}

	/**
	 * Adds item to the items list. If an item is already present the method
	 * increase the quantity of that item with one.
	 * If the item is not present a new item is created and the quantity is set to
	 * one.
	 * 
	 * @param itemID is used to identify if there is such an item in the inventory.
	 * @throws ItemNotFoundInInventoryException if item is not found in inventory.
	 * @throws UnreachableDatabaseException     if database crashes
	 */
	public void registerItem(int itemID) throws UnreachableDatabaseException, ItemNotFoundInInventoryException {
		try {
			if (itemIsAlreadyEntered(itemID)) {
				increaseQuantity(itemID);
				runningTotal += items.get(itemID).getItemInfo().getPrice();
				calcualteTotalVAT(itemID);
			} else {
				Item item = new Item(itemID, inventorySystem.getItemInformation(itemID));
				items.add(item);
				item.increaseQuantity();
				runningTotal += item.getItemInfo().getPrice();
				calcualteTotalVAT(itemID);
			}
		} catch (ItemNotFoundInInventoryException e) {
			throw new ItemNotFoundInInventoryException(itemID);
		} catch (UnreachableDatabaseException ude) {
			throw new UnreachableDatabaseException("Database server crashed");
		}
	}

	/**
	 * Ends the current sale and sets total price of the sale to the running total.
	 * 
	 * @return Total price of the sale.
	 */
	public double endSale() {
		notifyObservers();
		return getTotalPrice();
	}

	/**
	 * Get item information.
	 * 
	 * @param itemID Used to identify the item.
	 * @return The identified item.
	 */
	public ItemDTO getItemInformation(int itemID) {
		return items.get(itemID).getItemInfo();
	}

	/**
	 * Get running total
	 * 
	 * @return <code>runningTotal</code>.
	 */
	public double getRunningTotal() {
		return runningTotal;
	}

	/**
	 * Get total VAT of the sale.
	 * 
	 * @return <code>totalVAT</code>.
	 */
	public double getTotalVAT() {
		return totalVAT;
	}

	/**
	 * Get the items of the sale.
	 * 
	 * @return <code>Items</code>.
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Get the time when the was started.
	 * 
	 * @return <code>saleTime</code>.
	 */
	public LocalTime getTimeOfSale() {
		return saleTime;
	}

	/**
	 * Get total the price.
	 * 
	 * @return <code>totalPrice</code>
	 */
	public double getTotalPrice() {
		totalPrice = runningTotal;
		return totalPrice;
	}

	/**
	 * Calculates the change.
	 * 
	 * @param amountPaid The amount paid by the customer.
	 * @return The change.
	 */
	public double calculateChange(double amountPaid) {
		double change = Math.abs(getTotalPrice() - amountPaid);
		return change;
	}

	/**
	 * Get the total price excluding VAT
	 * 
	 * @return <code>totalSalesExcludingVAT</code>.
	 */
	public double getTotalSalesExcludingVAT() {
		totalSalesExcludingVAT = getTotalPrice() - getTotalVAT();
		return totalSalesExcludingVAT;
	}

	/**
	 * Sets the <code>saleObservers</code> from <code>Sale</code> as the
	 * <code>saleObservers</code> list from <code>Controller</code>.
	 * 
	 * @param observers The observers list.
	 */
	public void setSaleObservers(ArrayList<SaleObserver> observers) {
		saleObservers = observers;
	}

	private void increaseQuantity(int itemID) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemID() == itemID)
				items.get(i).increaseQuantity();
		}
	}

	private boolean itemIsAlreadyEntered(int itemID) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemID() == itemID)
				return true;
		}
		return false;
	}

	private void calcualteTotalVAT(int itemID) {
		totalVAT += items.get(itemID).getItemInfo().getVATPrice();
	}

	private void notifyObservers() {
		for (int i = 0; i < saleObservers.size(); i++) {
			saleObservers.get(i).addNewSale(getTotalPrice());
		}
	}
}