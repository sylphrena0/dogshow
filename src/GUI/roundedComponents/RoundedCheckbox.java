package GUI.roundedComponents;

import GUI.Controller;
import config.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class RoundedCheckbox extends JCheckBox implements ConfigParameters{
        private Shape shape;
        private Color color;
        public RoundedCheckbox(String text, Controller controller) {
            super(text, true);
            this.color = inputColor;
            setOpaque(false); // As suggested by @AVD in comment.
            RoundedCheckbox RoundedCheckbox = this;
            RoundedCheckbox.addActionListener(controller);
            RoundedCheckbox.setForeground(Color.WHITE);
            RoundedCheckbox.setFont(inputFont);
            RoundedCheckbox.setFocusPainted(false);

            try {
                // Set default icon for checkbox
                this.setIcon(new ImageIcon(ImageIO.read(new File(path + "images/unchecked.png"))));
                // Set selected icon when checkbox state is selected
                this.setSelectedIcon(new ImageIcon(ImageIO.read(new File(path + "images/checked.png"))));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            this.setIcon(new ImageIcon(ImageIO.read(...)));
//            this.setBackground(Color.RED);
//            this.setOpaque(true);
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
