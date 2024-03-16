package gui.components;

import gui.Controller;
import utilities.Constants;
import utilities.Utilities;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ImageLoaderButton extends IconButton {
    private Shape shape;
    private Image image;
    public ImageLoaderButton(Controller controller) {
        super("noImageIcon.png", Utilities.relativeHeight(20), Utilities.relativeHeight(20), controller);
        this.setBackground(Constants.transparent); // otherwise, paint component will draw over image with flatlaf dracula L&F
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image, (this.getWidth() - image.getWidth(null))/2, (this.getHeight() - image.getHeight(null))/2, null);
        }
        super.paintComponent(g);

    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public void setImage(Image image) {
        if (image == null) {
            this.image = null;
            setIcon("noImageIcon.png", Utilities.relativeHeight(20), Utilities.relativeHeight(20));
        } else {
            this.image = image;
            setIcon(null);
        } this.repaint();
    }



}
