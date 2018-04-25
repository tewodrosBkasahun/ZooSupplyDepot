package animalManagement;


import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Image;

/*
 * STUDENTS:  DO NOT MODIFY THIS CLASS! 
 *            A DIFFERENT SET OF ANIMALS MIGHT BE USED DURING TESTING!
 */

/** 
 * An IMMUTABLE class that represents an item that might be part of an menagerie
 * in a animal store.  (For example, Lion or Tiger.)
 * 
 * A zoo animal object has a name (String), a wholesale cost give in pennies (int),
 * a retail price given in pennies (int), a small picture that depicts
 * the item (java.awt.Image), and an int that stores the sorting key. <br><br>
 * 
 * Note that the constructor is private, so the only available ZooAnimal objects
 * are those that are found in the public static array of ZooAnimal objects called
 * ANIMAL_OBJECTS.  (You cannot use "new" to instantiate a ZooAnimal object, you 
 * must use the ones that are already in the ANIMAL_OBJECTS array.)
 */
public class ZooAnimal implements Listable {

	private final String name;
	private final int wholesaleCost;
	private final int retailCost;
	private final Image image;
	private final int key;
	
	
	/*
	 * These constants represent the Animal items that are available for use.
	 * 
	 * The constructor for
	 * the ZooAnimal class is private, so the elements of this array are the only
	 * ZooAnimal objects available.  (You cannot create new ones.)
	 */
	

	
    public final static ZooAnimal ALLIGATOR = 
    		new ZooAnimal("alligator", 13100, 13150, "alligator.jpg", 64);
    public final static ZooAnimal ARMADILLO = 
    		new ZooAnimal("armadillo", 12200, 12250, "armadillo.jpg", 26);
    public final static ZooAnimal CHEETAH = 
    		new ZooAnimal("cheetah", 11300, 11350, "cheetah.jpg", 24);
    public final static ZooAnimal EAGLE = 
    		new ZooAnimal("eagle", 10400, 10450, "eagle.jpg", 22);
    public final static ZooAnimal ELEPHANT = 
    		new ZooAnimal("elephant", 9500, 9550, "elephant.jpg", 20);
    public final static ZooAnimal GECKO = 
    		new ZooAnimal("gecko", 8600, 8650, "gecko.jpg", 18);
    public final static ZooAnimal JAGUAR = 
    		new ZooAnimal("jaguar", 7700, 7750, "jaguar.jpg", 16);
    public final static ZooAnimal LION = 
    		new ZooAnimal("lion", 6800, 6850, "lion.jpg", 14);
    public final static ZooAnimal PANDA = 
    		new ZooAnimal("panda", 5900, 5950, "panda.jpg", 12);
    public final static ZooAnimal REINDEER = 
    		new ZooAnimal("reindeer", 4100, 4105, "reindeer.jpg", 10);
    public final static ZooAnimal SQUIRREL = 
    		new ZooAnimal("squirrel", 3110, 3115, "squirrel.jpg", 8);
    public final static ZooAnimal TIGER = 
    		new ZooAnimal("tiger", 2120, 2125, "tiger.jpg", 6);
    public final static ZooAnimal TOUCAN = 
    		new ZooAnimal("toucan", 1130, 1135, "toucan.jpg", 4);
    public final static ZooAnimal ZEBRA = 
    		new ZooAnimal("zebra", 400, 450, "zebra.jpg", 2);
    

    /*
     * If it would be useful for you to access an array of all available ZooAnimal
     * objects, here it is.
     */
    public final static ZooAnimal[] ANIMAL_OBJECTS = {
            ZooAnimal.ALLIGATOR,
            ZooAnimal.ARMADILLO,
            ZooAnimal.CHEETAH,
            ZooAnimal.EAGLE,
            ZooAnimal.ELEPHANT,
            ZooAnimal.GECKO,
            ZooAnimal.JAGUAR,
            ZooAnimal.LION,
            ZooAnimal.PANDA,
            ZooAnimal.REINDEER,
            ZooAnimal.SQUIRREL,
            ZooAnimal.TIGER,
            ZooAnimal.TOUCAN,
            ZooAnimal.ZEBRA
    };


	private ZooAnimal(
			String nameIn, 
			int wholesaleCostIn, int retailCostIn, 
			String imageNameIn, 
			int keyIn) 
	{
		this.name = nameIn;
		this.wholesaleCost = wholesaleCostIn;
		this.retailCost = retailCostIn;
		this.image = Toolkit.getDefaultToolkit().getImage(imageNameIn);
		
		/*  Java normally loads images in a background thread.
		 *  This waits for the image to finish loading before moving on with
		 *  the rest of the program.  That helps to keep things
		 *  synchronized properly.
		 */
		try {
			MediaTracker tracker = new MediaTracker(new Panel());
			tracker.addImage(image, 0);
			tracker.waitForID(0);
			if (tracker.statusID(0,true) != MediaTracker.COMPLETE) { 
				throw new RuntimeException("Unable to load " + imageNameIn);
			}
		} catch(InterruptedException e) {
			// won't be interrupted, so no worries :-)
		}
		
		this.key = keyIn;
	}

	/**
	 * Getter for the Image associated with this zoo animal.  (It's a very small
	 * picture of the animal.)
	 * 
	 * @return a picture representing this item
	 */
	public Image getImage() {
		return image;
		//NOTE: this is a privacy leak but simplifies some issues 
		//      with Image that we haven't seen yet
	}

	/**
	 * Getter for the name of this zoo animal.
	 * 
	 * @return the name of this zoo animal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the wholesale cost of this zoo animal, measured in pennies.
	 * 
	 * @return wholesale cost for this zoo animal in pennies.
	 */
	public int getWholesaleCost() {
		return wholesaleCost;
	}

	/**
	 * Getter for the retail cost of this zoo animal, measured in pennies.
	 * 
	 * @return retail cost for this zoo animal in pennies.
	 */
	public int getRetailValue() {
		return retailCost;
	}

	
	/**
	 * Getter for the key of this zoo animal
	 * @return this zoo animal item's key
	 */
	public int getKeyValue() {
		return key;
	}
	
	
	/**
	 * Checks if the current object is equal to the parameter.  Note:
	 * only the NAMES of the zoo animals are compared.  If the two animals have
	 * the same name, they are considered equal!
	 * 
	 * @param other ZooAnimal item to be compared with the current object
	 * @return true if the two ZooAnimal have the same name, false otherwise
	 */
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (this.getClass() != other.getClass()) return false;
		
		return (name.equals(((ZooAnimal)other).name));
	}

	/**
	 * Returns the name of the zoo animal.
	 * 
	 * @return the name of the zoo animal
	 */
	public String toString() {
		return name;
	}
}
