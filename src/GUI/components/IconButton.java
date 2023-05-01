package GUI.components;

import GUI.Controller;
import utilities.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconButton extends JButton implements ConfigParameters {
    public IconButton(String imageName, int iconWidth, int iconHeight, Controller controller) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("images/" + imageName))); // load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance(iconWidth, iconHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            this.setIcon(new ImageIcon(scaledImage));
            this.setBorderPainted(false);
            this.setOpaque(false);
            this.addActionListener(controller);
            this.setBackground(null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void setIcon(String imageName, int iconWidth, int iconHeight) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("images/" + imageName))); // load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance(iconWidth, iconHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            this.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
