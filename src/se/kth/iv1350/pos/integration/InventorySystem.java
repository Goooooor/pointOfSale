package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Sale;

/**
 * Updates the inventory with the sale information
 *
 */
public class InventorySystem {

	private ArrayList<ItemDTO> inventory = new ArrayList<>();

	private ItemDTO chicken = new ItemDTO("Whole chicken 1,5 kg", 100, 0.12, 30);

	private ItemDTO toiletPaper = new ItemDTO("Toiletpaper 20 pack", 100, 0.25, 85);

	private ItemDTO bacon = new ItemDTO("Bacon 5 pack", 50, 0.12, 100);

	private ItemDTO kanelBulle = new ItemDTO("Kanelbulle 10 pack", 30, 0.12, 20);

	/**
	 * Creates a new instance of the <code>InventorySystem</code> class.
	 * This is a fake inventory system where fake items are stored.
	 */
	public InventorySystem() {
		inventory.add(chicken);
		inventory.add(toiletPaper);
		inventory.add(bacon);
		inventory.add(kanelbulle);
	}

	/**
	 * The information about each item specified in the <code>ItemDTO</code> class
	 * that is stored in the fake inventory system.
	 * 
	 * @param itemID is the index of each item in the inventory system.
	 * @return The item information of desired item in the inventory system.
	 * @throws UnreachableDatabaseException When the database is not reachable.
	 * @throws ItemNotFoundException        When an item can not be found with
	 *                                      the entered itemID.
	 */
	public ItemDTO getItemInformation(int itemID) throws ItemNotFoundException, UnreachableDatabaseException {
		if (itemID < 0 || itemID >= inventory.size())
			throw new ItemNotFoundException(itemID);
		else if (itemID == 3)
			throw new UnreachableDatabaseException("Database failure");
		else
			return inventory.get(itemID);
	}

	/**
	 * Updates the inventory with the amount of each item sold.
	 * 
	 * @param sale Contains information about items sold.
	 */
	public void updateInventory(Sale sale) {
		ItemDTO updatedItem;
		for (int i = 0; i < sale.getItems().size(); i++) {
			updatedItem = new ItemDTO(inventory.get(i).getItemDescription(),
					inventory.get(i).getPrice(),
					inventory.get(i).getVATRate(),
					inventory.get(i).getInStock() - sale.getItems().get(i).getQuantity());

			for (int j = 0; j < inventory.size(); j++) {
				if (updatedItem.getItemDescription().equals(inventory.get(j).getItemDescription())) {
					inventory.remove(j);
					inventory.add(j, updatedItem);
				}
			}
		}
	}
}
