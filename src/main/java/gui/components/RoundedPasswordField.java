package gui.components;

import gui.Controller;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

// Author: Graywolf
// Source: https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder

public class RoundedPasswordField extends JPasswordField {
    private Shape shape;
    private final Color color;
    private final String placeholder;
    public RoundedPasswordField(String placeholder, Controller controller) {
        setOpaque(false);
        this.color = Constants.inputColor;
        this.placeholder = placeholder;
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(Constants.inputColor);
        this.setCaretColor(Color.WHITE);
        this.setBorder(Constants.componentInsets);
        this.setFont(Constants.inputFont);
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

    public void setInvalid(String error) {
        // set a red placeholder error for 3 seconds before reverting to normal
        this.setForeground(Constants.errorRed);
        this.setEchoChar((char) 0); // sets password to visible
        this.setText(error);
        JPasswordField passwordField = this;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        passwordField.setText(placeholder);
                        passwordField.setForeground(Color.WHITE);
                    }
                },
                3000
        );

    }

}
