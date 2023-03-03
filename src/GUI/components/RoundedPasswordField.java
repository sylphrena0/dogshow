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

public class RoundedPasswordField extends JPasswordField implements ConfigParameters {
    private Shape shape;
    private Color color;
    public RoundedPasswordField(String placeholder, Controller controller) {
        setOpaque(false); // As suggested by @AVD in comment.
        this.color = inputColor;
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setCaretColor(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        this.setFont(inputFont);
        this.setEchoChar((char) 0); //sets password to visible
        this.setText(placeholder);

        RoundedPasswordField passwordField = this;
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*'); //sets password to invisible
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0); //sets password to visible
                }
            }
        });
    }
    public RoundedPasswordField() {
        this.color = inputColor;
        setOpaque(false); // As suggested by @AVD in comment.

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
