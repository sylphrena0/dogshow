package gui.components;

import gui.Controller;
import utilities.Parameters;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton implements Parameters {
    public IconButton(String imageName, int iconWidth, int iconHeight, Controller controller) {
        ImageIcon imageIcon = Utilities.getImageIcon(imageName); // load the image to a imageIcon
        Image scaledImage = imageIcon.getImage().getScaledInstance(iconWidth, iconHeight,  Image.SCALE_SMOOTH); // scale it the smooth way
        this.setIcon(new ImageIcon(scaledImage));
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.addActionListener(controller);
        this.setBackground(null);
    }

    public void setIcon(String imageName, int iconWidth, int iconHeight) {
        ImageIcon imageIcon = Utilities.getImageIcon(imageName); // load the image to a imageIcon
        Image scaledImage = imageIcon.getImage().getScaledInstance(iconWidth, iconHeight,  Image.SCALE_SMOOTH); // scale it the smooth way
        this.setIcon(new ImageIcon(scaledImage));
    }
}
