package GUI.components;

import utilities.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
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
}
