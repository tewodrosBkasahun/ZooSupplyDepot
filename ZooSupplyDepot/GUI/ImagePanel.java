package GUI;


import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Dimension;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;

	public ImagePanel(Image image) {
		this.image = image;
	}

	public Dimension getMinimumSize() {
		return new Dimension(image.getWidth(this), image.getHeight(this));
	}
	
	public Dimension getMaximumSize() {
		return new Dimension(image.getWidth(this), image.getHeight(this));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this), image.getHeight(this));
	}

	public void paint(Graphics g) {

		java.awt.Rectangle bounds = getBounds();
		g.drawImage(image, 0, (bounds.height - image.getHeight(this)) / 2 , null);
	}

}
