package GUI.components;

import GUI.Controller;
import utilities.Parameters;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedCheckbox extends JCheckBox implements Parameters {
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
            this.setIcon(Utilities.getScaledImageIcon("unchecked.png", (int) (Utilities.relativeHeight(6.5)*0.5), (int) (Utilities.relativeHeight(6.5)*0.5))); //set default icon for checkbox
            this.setIcon(Utilities.getScaledImageIcon("checked.png", (int) (Utilities.relativeHeight(6.5)*0.5), (int) (Utilities.relativeHeight(6.5)*0.5))); //set default icon for checkbox
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
