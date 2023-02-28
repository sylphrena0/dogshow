package GUI.roundedComponents;

import GUI.Controller;
import config.ConfigParameters;

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
        setOpaque(false); // As suggested by @AVD in comment.
        JTextField textField = this;
        textField.addActionListener(controller);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        textField.setFont(inputFont);
        textField.setText(placeholder);

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