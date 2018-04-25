package GUI;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Image;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.*;

import animalManagement.*;

public class AnimalListSelectionDialogBox extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton goButton = new JButton("Select These");
	private JTextField[] values;
	private Image[] images;

	private SortedListOfImmutables list;

	public AnimalListSelectionDialogBox(
			SortedListOfImmutables listToUpdate, 
			String title) 
	{
		super(new JFrame(), title, true);
		list = listToUpdate;
		this.setBackground(Color.WHITE);
		values = new JTextField[ZooAnimal.ANIMAL_OBJECTS.length];
		images = new Image[ZooAnimal.ANIMAL_OBJECTS.length];
		for(int i = 0; i < ZooAnimal.ANIMAL_OBJECTS.length; i++) {
			values[i] = new JTextField("000");
			values[i].setText("0");
			values[i].setColumns(3);
			values[i].setMaximumSize(values[i].getPreferredSize());
			values[i].setMinimumSize(values[i].getPreferredSize());

			images[i] = ZooAnimal.ANIMAL_OBJECTS[i].getImage();
		}


		goButton.addActionListener ( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {		

				for (int i = 0; i < values.length; i++) {
					for (int j = 0; j < Integer.parseInt(values[i].getText()); j++) {
						list.add(ZooAnimal.ANIMAL_OBJECTS[i]);
					}
				}
				AnimalListSelectionDialogBox.this.dispose();
			}
		});

		int numPanels = ZooAnimal.ANIMAL_OBJECTS.length;

		JPanel[] panel = new JPanel[numPanels];
		for (int i =0; i < panel.length; i++) {
			panel[i] = new JPanel();
			panel[i].setBackground(Color.WHITE);
			panel[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel[i].setLayout(new BoxLayout(panel[i], BoxLayout.LINE_AXIS));
			panel[i].add(values[i]);
			panel[i].add(new ImagePanel(images[i]));	
		}

		setLayout(new GridLayout(3, 5));

		for (int i = 0; i < numPanels; i++) {
			add(panel[i]);
		}

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		goButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		goButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		buttonPanel.add(goButton);

		buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(buttonPanel);

		pack();
		setVisible(true);

	}
}
