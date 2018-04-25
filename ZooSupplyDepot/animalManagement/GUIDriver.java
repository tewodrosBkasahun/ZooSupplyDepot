package animalManagement;

import GUI.View;

/** 
 * Run the main method in this class to try out the Graphical User Interface
 * 
 * @author Fawzi Emad, Evan Golub (C)2007-2015
 */
public class GUIDriver {

	/** Starts up the GUI */
	public static void main(String[] args) {
		
		final AnimalStore myPetStore = 
				new AnimalStore("Zoo Supply Depot", 12345678);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);		
				new View(myPetStore);
			}
		});	
	}

}
