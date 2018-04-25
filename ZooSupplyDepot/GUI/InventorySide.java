package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

import animalManagement.*;

public class InventorySide extends JPanel {
	private static final long serialVersionUID = 1L;

	private AnimalStore petstore;

	private JScrollPane scrollPane;
	private InventoryDialogBox inventoryBox;
	
	private JPanel buttonPanel;
	private JPanel headingPanel;
	private JButton shipmentButton = new JButton("Deliver Shipment");
	private JButton randomShipmentButton = new JButton("Deliver Random Shipment");

	public InventorySide(AnimalStore r) {
		petstore = r;
		inventoryBox = new InventoryDialogBox(petstore);
		setBackground(Color.WHITE);
		prepareButtonPanel();
		prepareHeadingPanel();
		scrollPane = new JScrollPane(inventoryBox);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(headingPanel);
		add(buttonPanel);
		add(scrollPane);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.LOWERED));
		setVisible(true);
	}

	private void prepareButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(shipmentButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(randomShipmentButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
	}
	
	private void prepareHeadingPanel() {
		headingPanel = new JPanel();
		JLabel words = new JLabel("Inventory");
		words.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
		headingPanel.add(words);
		headingPanel.setMaximumSize(new Dimension(2000, headingPanel.getPreferredSize().height));
		headingPanel.setBackground(Color.WHITE);
	}
	
	public JButton getShipmentButton() {
		return shipmentButton;

	}
	
	public JButton getRandomShipmentButton() {
		return randomShipmentButton;
	}
	
	public InventoryDialogBox getInventoryBox() {
		return inventoryBox;
	}

	
}
