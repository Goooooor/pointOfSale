package se.kth.iv1350.pos.controller;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.pos.integration.OperationFailedException;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.UnreachableDatabaseException;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.Register;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleLog;
import se.kth.iv1350.pos.model.SaleObserver;

/**
 * This is the systems only controller. All calls to the model pass through this
 * class.
 *
 */
public class Controller {

	private final InventorySystem inventorySystem;

	private final AccountingSystem accountingSystem;

	private final SaleLog saleLog;

	private final Register register;

	private final Printer printer;

	private Sale sale;

	private final ArrayList<SaleObserver> saleObservers = new ArrayList<>();

	/**
	 * Creates a new instance of the <code>Controller</code> class.
	 */
	public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer) {
		this.inventorySystem = new InventorySystem();
		this.accountingSystem = new AccountingSystem();
		this.printer = new Printer();
		saleLog = new SaleLog();
		register = new Register();
	}

	/**
	 * Starts a new sale. This method must be called before doing anything else.
	 */
	public void createNewSale() {
		sale = new Sale();
		sale.setSaleObservers(saleObservers);
	}

	/**
	 * Registers the item added entered using its item identifier.
	 * 
	 * @param itemID is used to identify items in the inventory.
	 * @return Information about the item registered as a String.
	 * @throws OperationFailedException         if database crashes
	 * @throws ItemNotFoundInInventoryException if item can not be found with
	 *                                          itemID.
	 */
	public ItemDTO registerItem(int itemID) throws OperationFailedException, ItemNotFoundInInventoryException {
		try {
			sale.registerItem(itemID);
			return sale.getItemInformation(itemID);
		} catch (UnreachableDatabaseException ude) {
			throw new OperationFailedException("Database could not be reached", ude);
		} catch (ItemNotFoundInInventoryException e) {
			throw new ItemNotFoundInInventoryException(itemID);
		}
	}

	/**
	 * Ends the sale.
	 * 
	 * @return The total price of the sale.
	 */
	public double endSale() {
		sale.endSale();
		return sale.getTotalPrice();
	}

	/**
	 * Calculates the change and registers the payment.
	 * 
	 * @param amountPaid is the amount the customers pays when the sale ends.
	 * @return The change from the payment.
	 */
	public double calculateChange(double amountPaid) {
		double change = sale.calculateChange(amountPaid);		
		registerPayment(new Payment(amountPaid, change));
		return change;
	}

	/**
	 * Get the running total.
	 * 
	 * @return The running total.
	 */
	public double getRunningTotal() {
		return sale.getRunningTotal();
	}

	/**
	 * Adds an observer to the list.
	 * 
	 * @param obs The observer.
	 */
	public void addSaleObserver(SaleObserver obs) {
		saleObservers.add(obs);
	}

	private void registerPayment(Payment payment) {
		printer.printReceipt(register.registerPayment(payment, sale));
		updateSystems();
	}

	private void updateSystems() {
		saleLog.updateSaleLog(sale);
		inventorySystem.updateInventory(sale);
		accountingSystem.updateAccounting(sale);
	}
}
