package GUI.components;

import GUI.Controller;
import utilities.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class RoundedCheckbox extends JCheckBox implements ConfigParameters{
        private Shape shape;
        private final Color color;
        public RoundedCheckbox(String text, Controller controller) {
            super(" " + text, true);
            this.color = inputColor;
            this.setOpaque(false);
            this.addActionListener(controller);
            this.setForeground(Color.WHITE);
            this.setFont(inputFont);
            this.setFocusPainted(false);
            this.setBorder(componentInsets);

            try {
                this.setIcon(new ImageIcon(ImageIO.read(new File("images/unchecked.png")))); //set default icon for checkbox
                this.setSelectedIcon(new ImageIcon(ImageIO.read(new File("images/checked.png")))); //set selected icon when checkbox state is selected
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        protected void paintComponent(Graphics g) {
            g.setColor(color);
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(color);
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            return shape.contains(x, y);
        }
}
