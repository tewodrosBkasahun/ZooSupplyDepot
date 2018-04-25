package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import animalManagement.*;


public class OrderButtonListener implements ActionListener {

	private Menagerie entree;
	private View view;
	private AnimalStore store;
	private InventoryDialogBox inventoryBox;

	public OrderButtonListener(View v, InventoryDialogBox idb, AnimalStore psIn, Menagerie e) {
		entree = e;
		view = v;
		store = psIn;
		inventoryBox = idb;
	}

	public void actionPerformed( ActionEvent e ) {		
		boolean success = store.placeOrder(entree);
		if (!success) {
			JOptionPane.showMessageDialog(null, "Sorry, we can't place that order because we don't have all of the required items in stock.");
		}
		view.reDrawCash();
		inventoryBox.setSize(inventoryBox.getPreferredSize());
		inventoryBox.repaint();
	}

}
