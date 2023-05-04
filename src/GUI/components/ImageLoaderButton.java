package GUI.components;

import GUI.Controller;
import utilities.Parameters;
import utilities.Utilities;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ImageLoaderButton extends IconButton implements Parameters {
    private Shape shape;
    private Image image;
    public ImageLoaderButton(Controller controller) {
        super("noImageIcon.png", Utilities.relativeHeight(20), Utilities.relativeHeight(20), controller);
        this.setBackground(transparent); //otherwise, paint component will draw over image with flatlaf dracula L&F
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15); //thicken the border by drawing white rounded square
        g.setColor(pageColor);
        g.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 15, 15); //reapply page background color

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
        if (image == null) {
            this.image = null;
            setIcon("noImageIcon.png", Utilities.relativeHeight(20), Utilities.relativeHeight(20));
        } else {
            this.image = image;
            setIcon(null);
        } this.repaint();
    }


}
