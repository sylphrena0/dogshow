package GUI.components;

import GUI.Controller;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

// Author: Graywolf
// Source: https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
public class RoundedTextField extends JTextField implements ConfigParameters {
    private Shape shape;
    private Color color;
    public RoundedTextField(String placeholder, Controller controller) {
        this.color = inputColor;
        this.setOpaque(false); // As suggested by @AVD in comment.
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(inputColor);
        this.setCaretColor(Color.WHITE);
        this.setBorder(componentInsets);
        this.setFont(inputFont);
        this.setText(placeholder);

        JTextField textField = this;
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });

    }
    public RoundedTextField(String placeholder, Controller controller, Color color) {
        this(placeholder, controller);
        this.color = color;
        this.setBackground(color);
        this.setHorizontalAlignment(JTextField.CENTER);
    }
    public RoundedTextField(String placeholder, Boolean enabled, Controller controller) {
        this(placeholder, controller);
        if (!enabled) {
            this.setEnabled(false);
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