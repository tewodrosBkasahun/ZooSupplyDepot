package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import animalManagement.*;

import java.awt.Graphics;

public class View extends JSplitPane {

	private static final long serialVersionUID = 0L;
	
	private AnimalStore petstore = null;
	private InventorySide inventorySide = null;
	private MenuDialogBox menuBox = null;
	private JPanel headingPanel;
	private JLabel cash;
	
	public View(AnimalStore psIn) {
		super(HORIZONTAL_SPLIT, true);
		
		petstore = psIn;
		inventorySide = new InventorySide(petstore);
		menuBox = new MenuDialogBox(
				this, petstore, inventorySide.getInventoryBox()
		);
		
		registerInventoryButtons();
		registerMenuButtons();
		
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
		masterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		prepareHeadingPanel();
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setLeftComponent(menuBox);
		setRightComponent(inventorySide);
		masterPanel.add(headingPanel);
		masterPanel.add(this);
		JFrame frame = new JFrame(petstore.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(masterPanel);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	private JLabel getCashLabel() {
		JLabel retValue = new JLabel(
				"Available cash:  $" + petstore.getCash() / 100 + "." +
				petstore.getCash() % 100 / 10 + petstore.getCash() % 10
		);
		retValue.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 16));
		retValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		return retValue;
	}
	
	public void reDrawCash() {
		cash.setText("Available cash:  $" + petstore.getCash() / 100 + "." + 
				petstore.getCash() % 100 / 10 + petstore.getCash() % 10
		);
		this.repaint();
	}
	
	private void prepareHeadingPanel() {
		headingPanel = new JPanel();
		headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.PAGE_AXIS));
		JLabel title = new JLabel("Welcome to " + petstore.getName());
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("Gigi", Font.ITALIC | Font.BOLD, 32));
		JLabel secondLine = new JLabel(
				"Where the key thing is the key!"
		);
		secondLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		secondLine.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));
		
		cash = getCashLabel();
		
		headingPanel.add(title);
		headingPanel.add(secondLine);
		headingPanel.add(Box.createVerticalStrut(20));
		headingPanel.add(cash);
		headingPanel.add(Box.createVerticalStrut(20));
		headingPanel.doLayout();
		headingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	
	private void registerInventoryButtons() {
		
		inventorySide.getShipmentButton().addActionListener ( 
		new ActionListener() {
			public void actionPerformed( ActionEvent e ) {		

				final SortedListOfImmutables list = new SortedListOfImmutables();
				new AnimalListSelectionDialogBox(list, "Delivery Selection");
				boolean success = petstore.addShipmentToInventory(list);

				if (!success) {
					JOptionPane.showMessageDialog(
							null, 
							"You don't have enough cash for that delivery.  "+
							"Delivery rejected!"
					);
				}
				inventorySide.getInventoryBox().setSize(
						inventorySide.getPreferredSize()
				);
				inventorySide.invalidate();
				inventorySide.repaint();
				inventorySide.getInventoryBox().repaint();
				repaint();
				reDrawCash();
			}
		});

		inventorySide.getRandomShipmentButton().addActionListener ( 
		new ActionListener() {
			public void actionPerformed( ActionEvent e ) {		
				final SortedListOfImmutables list = new SortedListOfImmutables();
				Random random = new Random();
				for (int i = 0; i < ZooAnimal.ANIMAL_OBJECTS.length; i++) {
					for (int j = 0; j < random.nextInt(3); j++) {
						list.add(ZooAnimal.ANIMAL_OBJECTS[i]);
					}
				}
				if (!petstore.addShipmentToInventory(list)) {
					JOptionPane.showMessageDialog(
							null, 
							"The cost of the random delivery was more than your available cash.  Delivery rejected!"
					);
				}
				inventorySide.getInventoryBox().setSize(inventorySide.getPreferredSize());
				inventorySide.invalidate();
				inventorySide.repaint();
				inventorySide.getInventoryBox().repaint();
				repaint();
				reDrawCash();
			}
		});
		
	}
	
	private void registerMenuButtons() {
		menuBox.getNewMenagerieButton().addActionListener ( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {		

				String name = JOptionPane.showInputDialog("Enter the name for your menagerie: ");
				SortedListOfImmutables list = new SortedListOfImmutables();
				new AnimalListSelectionDialogBox(list, "Menagerie Selection");
				
				if (list.getSize() == 0) {
					return;
				}
				
				Menagerie newEntree = new Menagerie(name, list);
				petstore.addMenagerie(newEntree);
				menuBox = new MenuDialogBox(View.this, petstore, inventorySide.getInventoryBox());
				int dividerLocation = View.this.getDividerLocation();
				View.this.setLeftComponent(menuBox);
				View.this.setDividerLocation(dividerLocation);
				registerMenuButtons();
			}
		});
		
		
		menuBox.getRandomMenagerieButton().addActionListener ( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {		

				Random random = new Random();
				
				SortedListOfImmutables list = new SortedListOfImmutables();
				int numItems = random.nextInt(3) + 2;
				String name = "";
				for (int i = 0; i < numItems; i++) {
					ZooAnimal food = ZooAnimal.ANIMAL_OBJECTS[random.nextInt(ZooAnimal.ANIMAL_OBJECTS.length)];
					list.add(food);
					
				}
				for (int i = 0; i < list.getSize(); i++) {
					name += list.get(i).getName();
					if (i < list.getSize() - 2) {
						name += ", ";
					}
					if (i == list.getSize() - 2) {
						name += " and ";
					}
				}
				Menagerie newEntree = new Menagerie(name, list);
				petstore.addMenagerie(newEntree);
				menuBox = new MenuDialogBox(View.this, petstore, inventorySide.getInventoryBox());
				int dividerLocation = View.this.getDividerLocation();
				View.this.setLeftComponent(menuBox);
				View.this.setDividerLocation(dividerLocation);
				registerMenuButtons();
			}
		});
		
	}
	
	static boolean init;
	public void paint(Graphics g) {
		super.paint(g);
		
		if (init == false) {
			init = true;
			setDividerLocation(0.50);
		}
	}
}
