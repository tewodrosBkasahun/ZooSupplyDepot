package animalManagement;



/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in decreasing retail value order 
 * based on the key value of the items.  When a new item is added into the 
 * list, it is inserted  into the correct position so that the list stays 
 * in descending order by key value.
 */
public class SortedListOfImmutables {

	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items =new Listable[other.items.length];
		for (int i=0;i<other.items.length;i++) {
			items[i]=other.items[i];
		}
		
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {

		return items.length;
		
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {

		return items[i];
		
	}
	
	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in descending wholesale cost order based on the key values
	 * of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain in descending key value order.
	 * 
	 * In order to accommodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
				Listable[] added = new Listable[this.items.length+1];
				int a = 0;
				int b=0;
				
				for (int i = 0; i < items.length; i++){
					if (itemToAdd.getName().compareToIgnoreCase(this.items[i].getName()) > b){
						a++;
					
					}
				}
				added[a] = itemToAdd;
				for (int i = 0; i < a; i++){
					added[i] = items[i];
				}
				
				for (int i = a+1; i < added.length; i++){
					added[i] = items[a];
					a++;
				}
				items = added;
			
		
	
	
	
	
	}
	
	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * ordering of the list by descending key values of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		
		for (int i=0;i<listToAdd.items.length;i++) {
			add(listToAdd.get(i));
		}              
		
	}
	
	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		int found;
	if(checkAvailability( itemToRemove)) {
		for (int i=0; i<this.items.length;i++) {
			if (itemToRemove.equals(this.items[i])){
			found=i;
				Listable[] removed = new Listable[this.items.length-1];
				for (int l=0; l<found; l++) {
					removed[l]= this.items[l];
				}
				for (int m=found+1; m<removed.length +1; m++) {
					removed[m-1]= this.items[m];
				}
			this.items = removed;
			}
		}
	}else {
		return ;
	}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {

		for (int i=0; i<listToRemove.items.length;i++) { 
			remove(listToRemove.get(i));
		}
		
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int total =0;
		for(int i=0; i<this.items.length; i++) {
			total= total + this.items[i].getWholesaleCost();
			
		}
		return total;
			
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {

		int total =0;
		for(int i=0; i<this.items.length; i++) {
			total= total + this.items[i].getRetailValue();
			
		}
		return total;
		
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		 boolean found=false;
			for (int i=0; i<this.items.length;i++) {
				if (itemToFind.equals(this.items[i])){
					found=true;
				}
			}
			
		return found;
		
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * If the list of items has duplicates then the current list must have
	 * that many of the item as well.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		boolean[] m = new boolean[listToCheck.items.length];
		for (int i=0; i<listToCheck.items.length;i++) {
			m[i]=checkAvailability(listToCheck.get(i));
		} 
		
		boolean ifFalse = false;
		int count =0;
		for (int i=0; i<listToCheck.items.length;i++) {
			if (m[i]== ifFalse) {
				count++;
			}
		} 
		
		if(count >0) {return false;
		}else return false;
		
	}

	
	
	
	/*
	 * Do not modify this method or you will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
