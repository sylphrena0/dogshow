package demos.demo_swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImagePanel extends JPanel implements MouseListener {

    private BufferedImage sourceImage;

    public ImagePanel() {
        setPreferredSize(new Dimension(600, 600));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        if (sourceImage == null) {
            super.paint(g);
            return;
        } else {
            g.clearRect(0, 0, getWidth(), getHeight());
            g.drawImage(sourceImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        String separator = System.getProperty("file.separator");
        String path = System.getProperty("user.dir") + separator + "images" + separator; //hard coding b/c of issue

        JFileChooser chooser = new JFileChooser(path);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, & GIF Images", "png", "jpg", "gif" );
        chooser.setFileFilter(filter);

        int returnValue = chooser.showOpenDialog(this);
        String selectedFile = "";
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile().getAbsolutePath();
            loadImage(selectedFile);
        }
    }

    private void loadImage(String selectedFile) {
        sourceImage = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(new File(selectedFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        try {
            sourceImage = ImageIO.read(fis);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
