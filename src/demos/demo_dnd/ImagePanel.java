package demos.demo_dnd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Image image;
	private boolean letterBoxed = false;
	
	public ImagePanel(String fileName) {
		loadImage(fileName);
		// This method is defined on JComponent, so it is inherited by every Swing component.
		setTransferHandler(new ImageTransferHandler(this));
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void loadImage(String imageFile) {

		image = null;
		FileImageInputStream fiis = null;
		try {
			fiis = new FileImageInputStream(new File(imageFile));
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
		letterBoxed = false;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		if (image != null) {
			g.setColor(Color.BLACK);
			g.clearRect(0, 0, getWidth(), getHeight());
			if (!letterBoxed) {
				letterBoxed = true;
				image = letterboxImage(image, getWidth(), getHeight());	

			}
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}

	}
	
	
	private Image letterboxImage(Image srcImage, int width, int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics gc = img.getGraphics();

		double scaleFactor = 1.0;		

		int iwidth = srcImage.getWidth(null);
		int iheight = srcImage.getHeight(null);
		

		// Scale by the longer edge
		if (iwidth > iheight) {
			scaleFactor = width/(double)iwidth;
		} else {
			scaleFactor = height/(double)iheight;
		}
		
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = iwidth;
		int sy2 = iheight;
		
		int dx1 = (width-(int)(iwidth*scaleFactor))/2;
		int dy1 = (height-(int)(iheight*scaleFactor))/2;
		int dx2 = (width+(int)(iwidth*scaleFactor))/2;
		int dy2 = (height+(int)(iheight*scaleFactor))/2;
		

		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, width, height);
		gc.drawImage(srcImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		gc.dispose();
		return img;
	}
	
	public void setImage(Image image) {
		this.image = image;
		letterBoxed = false;
		repaint();
	}


}
