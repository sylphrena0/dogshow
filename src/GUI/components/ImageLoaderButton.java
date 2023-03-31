package GUI.components;

import GUI.Controller;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ImageLoaderButton extends IconButton {
    private Shape shape;
    private Image image;
    public ImageLoaderButton(Controller controller) {
        super("noImageIcon.png", Scaling.relativeHeight(20), Scaling.relativeHeight(20), controller);
        this.setBackground(headerColor);
    }

    protected void paintComponent(Graphics g) {
//        g.setColor(backgroundColor);

        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        if (image != null) {
            g.drawImage(image, (this.getWidth() - image.getWidth(null))/2, (this.getHeight() - image.getHeight(null))/2, null);
        }
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public void setImage(Image image) {
        this.image = image;
        setIcon(null);
        this.repaint();
    }

}
