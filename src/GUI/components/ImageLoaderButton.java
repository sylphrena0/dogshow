package GUI.components;

import utilities.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ImageLoaderButton extends JButton implements ConfigParameters {
    public ImageLoaderButton() {
        try {
            this.setIcon(new ImageIcon(ImageIO.read(new File("images/noimage.png"))));
            this.setBorderPainted(false);
            this.setOpaque(false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
