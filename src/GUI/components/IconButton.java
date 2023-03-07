package GUI.components;

import utilities.ConfigParameters;
import utilities.Scaling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconButton extends JButton implements ConfigParameters {
    public IconButton(String imageName) {
        try {
            this.setIcon(new ImageIcon(ImageIO.read(new File("images/" + imageName))));
            this.setBorderPainted(false);
            this.setOpaque(false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public IconButton(String imageName, Double scaleToHeader) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("images/" + imageName))); // load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance((int) (Scaling.relativeHeight(6.5)*scaleToHeader), (int) (Scaling.relativeHeight(6.5)*scaleToHeader),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            this.setIcon(new ImageIcon(scaledImage));
            this.setBorderPainted(false);
            this.setOpaque(false);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
