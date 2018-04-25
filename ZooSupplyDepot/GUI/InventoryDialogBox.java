package GUI;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;


import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Container;
import javax.swing.BoxLayout;

import animalManagement.*;

import java.awt.Font;

public class InventoryDialogBox extends JPanel {

	private static final long serialVersionUID = 1L;

	private AnimalStore store;
	private int preferredHeight = 400;

	public InventoryDialogBox(AnimalStore psIn) {

		setBackground(Color.WHITE);
		store = psIn;
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));	
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(0, preferredHeight);
	}

	public void paint(Graphics g) {

		super.paint(g);
		java.awt.Rectangle bounds = getBounds();
		SortedListOfImmutables list = store.getInventory();

		if (list.getSize() == 0) {
			g.setFont(new Font("Arial", Font.BOLD, 12 ));
			g.drawString("Nothing in stock -- waiting for delivery!", 20, 20);
			preferredHeight = 250;
		} else {
			Image[] images = new Image[list.getSize()];
			for (int i = 0; i < list.getSize(); i++) {
				images[i] = ((ZooAnimal)list.get(i)).getImage();
			}
			
			int widthUsed = 0;
			int heightUsed = 0;
			int maxHeight = 0;
			final int SPACING = 10;
			for (int i = 0; i < images.length; i++) {
				int width = images[i].getWidth(this);

				if (width > bounds.width) {   // window is very narrow, just draw it and go to next line
					if (widthUsed > 0) { // already something on this line, go to the next one
						heightUsed += maxHeight + SPACING;
					}
					g.drawImage(images[i], 0, heightUsed, null);
					heightUsed += Math.max(images[i].getHeight(this), maxHeight) + SPACING;
					widthUsed = 0;
					maxHeight = 0;
				}
				else if (widthUsed + width > bounds.width) { // no more room on this row -- go to the next one.
					heightUsed += maxHeight + SPACING;	
					g.drawImage(images[i], 0, heightUsed, null);
					widthUsed = images[i].getWidth(this) + SPACING;
					maxHeight = images[i].getHeight(this);
				}
				else {  // draw as usual
					g.drawImage(images[i], widthUsed, heightUsed, null);
					if (images[i].getHeight(this) > maxHeight) {
						maxHeight = images[i].getHeight(this);
					}
					widthUsed += images[i].getWidth(this) + SPACING;
				}
			}
			preferredHeight = heightUsed + maxHeight;
		}
	}
}
