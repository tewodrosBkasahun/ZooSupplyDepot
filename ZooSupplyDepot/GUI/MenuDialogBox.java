package GUI;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
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

import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.Box;
import java.util.Random;
import javax.swing.JCheckBox;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import animalManagement.*;

public class MenuDialogBox extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton newMenagerieButton = new JButton("Create New Menagerie");
	private JButton randomMenagerieButton = new JButton(
			"Create Random Menagerie");

	private AnimalStore store;
	private View view;

	private InventoryDialogBox inventoryBox;

	private JPanel headingPanel;
	private JPanel buttonPanel;
	private JPanel masterPanel;

	private void prepareMasterPanel() {

		masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
		masterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		int numPanels = store.getMenu().getSize() + 1;

		JPanel[] panel = new JPanel[numPanels];
		for (int i = 0; i < panel.length - 1; i++) {
			panel[i] = new JPanel();
			panel[i].setBackground(Color.WHITE);
			panel[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			panel[i].setLayout(new BoxLayout(panel[i], BoxLayout.LINE_AXIS));
			Menagerie entree = (Menagerie) store.getMenu().get(i);
			int value = entree.getRetailValue();
			JButton orderButton = new JButton("Order   ($" + value / 100 + "."
					+ value % 100 / 10 + value % 10 + ")");

			orderButton.addActionListener(new OrderButtonListener(view,
					inventoryBox, store, entree));

			panel[i].add(orderButton);
			panel[i].add(Box.createRigidArea(new Dimension(15, 15)));

			panel[i].add(Box.createRigidArea(new Dimension(15, 15)));
			panel[i].add(new JLabel(entree.getName()));
			panel[i].add(Box.createRigidArea(new Dimension(15, 15)));
			SortedListOfImmutables foodList = entree.getAnimalList();
			Image[] images = new Image[foodList.getSize()];
			for (int j = 0; j < foodList.getSize(); j++) {
				images[j] = ((ZooAnimal) foodList.get(j)).getImage();
			}
			for (int j = 0; j < images.length; j++) {
				panel[i].add(new ImagePanel(images[j]));

			}
			panel[i].add(Box.createHorizontalGlue());

		}

		for (int i = 0; i < panel.length - 1; i++) {
			masterPanel.add(panel[i]);
		}
	}

	public JButton getNewMenagerieButton() {
		return newMenagerieButton;
	}

	public JButton getRandomMenagerieButton() {
		return randomMenagerieButton;
	}

	private void prepareHeadingPanel() {
		headingPanel = new JPanel();
		JLabel words = new JLabel("Options");
		words.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
		headingPanel.add(words);
		headingPanel.setMaximumSize(new Dimension(2000, headingPanel
				.getPreferredSize().height));
		headingPanel.setBackground(Color.WHITE);
	}

	private void prepareButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(newMenagerieButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		buttonPanel.add(randomMenagerieButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	public MenuDialogBox(View v, AnimalStore rr, InventoryDialogBox idb) {
		view = v;

		store = rr;
		inventoryBox = idb;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.WHITE);
		prepareMasterPanel();
		prepareButtonPanel();
		prepareHeadingPanel();
		add(headingPanel);
		add(buttonPanel);
		JScrollPane scrollPane = new JScrollPane(masterPanel);
		masterPanel.setBackground(Color.WHITE);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);
		setBackground(Color.WHITE);
		setBorder(BorderFactory
				.createBevelBorder(javax.swing.border.EtchedBorder.LOWERED));
		setVisible(true);
	}
}
