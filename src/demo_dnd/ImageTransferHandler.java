package demo_dnd;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

class ImageTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	private DataFlavor fileFlavor = DataFlavor.javaFileListFlavor;
	private DataFlavor imageFlavor = DataFlavor.imageFlavor;
	private ImagePanel panel;
	
	private Image image;

	public ImageTransferHandler(ImagePanel panel) {
		this.panel = panel;
	}
	
	
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}

	@Override
	public boolean canImport(JComponent c, DataFlavor[] flavors) {
			return hasFileFlavor(flavors) || hasImageFlavor(flavors);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean importData(TransferSupport support) {
		Transferable t = support.getTransferable();
		if (hasImageFlavor(t.getTransferDataFlavors())) {
			try {
				System.out.println("Image flavor detected!");
	            image = (Image)t.getTransferData(imageFlavor);
				panel.setImage(image);
				return true;
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			return false;
			
		} else if (hasFileFlavor(t.getTransferDataFlavors()) ) {
			List<File> files = null;

			try {
				files = (List<File>) t.getTransferData(fileFlavor);
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			if (files == null)
				return false;
			File file = (File) files.get(0);
				
				try {
					image = ImageIO.read(file);
					panel.setImage(image);
					return true;
				} catch (IOException e) {
					System.err.println("Unable to open file " + file);
					e.printStackTrace();
					return false;
				}
			}
		
		return false;
	}
	
	
	private boolean hasFileFlavor(DataFlavor[] flavors) {
		for (DataFlavor f : flavors) {
			if (fileFlavor.equals(f)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasImageFlavor(DataFlavor[] flavors) {
		for (DataFlavor f : flavors) {
			if (imageFlavor.equals(f)) {
				System.out.println("This has image flavor");
				return true;
			}
		}
		return false;
	}

	@Override
	public Image getDragImage() {
		image = super.getDragImage();
		return image;
	}

}
