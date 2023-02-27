/**
 * 
 */
package demos.demo_structure;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Krish Pillai
 *
 */
public class BasicImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private ControllingFrame controller;

	public BasicImagePanel(ControllingFrame controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	@Override
	public void paint(Graphics g) {
		if (image != null) {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}

	protected BufferedImage getImageFromAbsolutePath(String path) {

		FileImageInputStream fiis = null;
		BufferedImage image = null;
		try {
			fiis = new FileImageInputStream(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// use ImageIO to read the image in
		try {
			image = ImageIO.read(fiis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imageURL = BasicImagePanel.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return new ImageIcon(imageURL, description);
		}
	}

	protected BufferedImage getImageFromPackage(String packagename, String filename) {

		String path = packagename + "/" + filename;
		URL url = this.getClass().getClassLoader().getResource(path);
		BufferedImage image = null;
		try {
			if (url == null) {
				System.err.println("Can't find image resource file: \"" + path + "\"");
				System.exit(0);
			}
			// use ImageIO to read the image in
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("Failed to load: \"" + path + "\"");
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setImageFromPackageFile(String packagename, String filename) {
		this.image = getImageFromPackage(packagename, filename);
	}

	public void setImageFromAbsolutePath(String path) {
		this.image = getImageFromAbsolutePath(path);
	}

	public ControllingFrame getController() {
		return controller;
	}

}
