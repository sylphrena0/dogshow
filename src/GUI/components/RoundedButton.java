package GUI.components;

import GUI.Controller;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Shape shape;
    private final Color color;
    public RoundedButton(Color color) {
        this.color = color;
        setOpaque(false); // As suggested by @AVD in comment.
    }

    public RoundedButton(String text, Color color, Color textColor, Controller controller) {
        this.color = color;
        setOpaque(false); // As suggested by @AVD in comment.
        JButton button = this;
        button.setText(text);
        button.addActionListener(controller);
        button.setForeground(textColor);
        button.setFont(new Font("Caveat", Font.BOLD, Scaling.relativeHeight(2)));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

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
