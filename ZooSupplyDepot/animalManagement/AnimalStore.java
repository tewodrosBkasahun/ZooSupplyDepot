package animalManagement;

/**
 *  The Animal Store has a name (String), options (list of Menageries), 
 *  an inventory (list of ZooAnimal), and an amount of cash on hand, 
 *  measured in pennies (int)
 * 
 *  This class facilitates orders being placed, deliveries being made to the
 *  inventory, and menageries being added to the options.
 */
public class AnimalStore {

	/*
	 * STUDENTS:  YOU MAY NOT ADD ANY OTHER INSTANCE VARIABLES TO THIS CLASS!
	 */
	private String name;
	private SortedListOfImmutables options;    // A list of Menagerie objects	
	private SortedListOfImmutables inventory;  // A list of ZooAnimal objects
	private int cash;

	/**
	 * Standard constructor.  The options and the inventory are both initialized as 
	 * empty lists.  The name and cash amount are set to match the parameters.
	 * 
	 * @param nameIn name of the animal store
	 * @param startingCash cash amount that the restaurant will have, measured
	 * in pennies
	 */
	public AnimalStore(String nameIn, int startingCash) {
		options = new SortedListOfImmutables();
		name = nameIn;
		cash = startingCash;
		inventory = new SortedListOfImmutables();
	}

	/**
	 * Getter for the name of the animal store.
	 * 
	 * @return reference to the name of the animal store
	 */
	public String getName() {

		return this.name;
		
	}

	/**
	 * Getter for the options.
	 * 
	 * @return a reference to a copy of the options
	 */
	public SortedListOfImmutables getMenu() {

		return new SortedListOfImmutables (options);
		
	}

	/**
	 * Adds an menagerie to the options.
	 * 
	 * @param menagerieToAdd reference to the menagerie to be added to the options
	 */
	public void addMenagerie(Menagerie menagerieToAdd) {

		options.add(menagerieToAdd);
		
	}
	
	/**
	 * Getter for the inventory.
	 * 
	 * @return a reference to a copy of the inventory
	 */
	public SortedListOfImmutables getInventory() {

		return new SortedListOfImmutables (inventory);
		
	}

	/**
	 * Getter for the current amount of cash on hand
	 * 
	 * @return the current amount of cash, measured in pennies
	 */
	public int getCash() {
		return this.cash;
	}

	/**
	 * Checks if the Animal items contained in the specified Menagerie are 
	 * actually contained in the animal store's inventory.
	 * 
	 * @param menagerieIn Menagerie that we are checking against the inventory
	 * @return true if the list of ZooAnimal items contained in the Menagerie are
	 * all present in the inventory, false otherwise.
	 */
	public boolean checkIfInInventory(Menagerie menagerieIn) {
		
		return inventory.checkAvailability(menagerieIn.getAnimalList());
		
		
	}

	/**
	 * Adds the specified list of animals to the inventory.  If the 
	 * total wholesale cost of all of the zoo animals combined exceeds 
	 * the amount of cash on hand, then NONE of the zoo animals are added 
	 * to the inventory.  If the amount of cash on hand is sufficient to
	 * pay for the shipment, then the amount of cash on hand is reduced by 
	 * the wholesale cost of the shipment.
	 * 
	 * @param list zoo animals to be added to the inventory
	 * @return true if the zoo animals are added; false if the zoo animals are
	 * not added because their wholesale cost exceeds the current cash
	 * on hand
	 */
	public boolean addShipmentToInventory(SortedListOfImmutables list) {
		if (this.cash >= list.getWholesaleCost()) {
			inventory.add(list);
			this.cash= this.cash-list.getWholesaleCost();
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * Removes the zoo animals contained in the specified Menagerie from the inventory.
	 * If the inventory does not contain all of the items required for this
	 * Menagerie, then NOTHING is removed from the inventory.  If the inventory contains
	 * all of the required items, then the amount of cash on hand is INCREASED by
	 * the retail value of the Menagerie.
	 *  
	 * @param menagerie Menagerie that has been ordered
	 * @return true if the zoo animals are removed from the inventory; false
	 * if the zoo animals were not removed because one or more required items
	 * were not contained in the inventory
	 */
	public boolean placeOrder(Menagerie menagerie) {
			if (checkIfInInventory(menagerie)) {
				return true;
			}else {
				
				this.cash =this.cash +menagerie.getRetailValue();
				inventory.remove(menagerie.getAnimalList());
				return false;	
			}
	}

}
