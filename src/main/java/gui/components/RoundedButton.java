package gui.components;

import gui.Controller;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Shape shape;
    private final Color color;
    public RoundedButton(String text, Color color, Color textColor, Controller controller) {
        this.color = color;
        this.setOpaque(false);
        this.setText(text);
        this.addActionListener(controller);
        this.setForeground(textColor);
        this.setFont(Constants.buttonFont);
        this.setBackground(color);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(color);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    @Override
    public void setEnabled(boolean b) {
        this.setRolloverEnabled(b);
    }
}
