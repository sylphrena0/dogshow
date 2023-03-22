package GUI.components;

import GUI.Controller;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ImageLoaderButton extends IconButton {
    private Shape shape;
    public ImageLoaderButton(Controller controller) {
        super("noImageIcon.png", Scaling.relativeHeight(20), Scaling.relativeHeight(20), controller);
    }

    protected void paintComponent(Graphics g) {
//        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
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

    public void setImage(Icon image) {
        this.setIcon(image);
    }

}
