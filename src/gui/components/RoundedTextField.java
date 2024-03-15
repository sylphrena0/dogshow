package gui.components;

import gui.Controller;
import utilities.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

// Author: Graywolf
// Source: https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
public class RoundedTextField extends JFormattedTextField implements Parameters {
    private Shape shape;
    private Color color;
    private final String placeholder;
    public RoundedTextField(String placeholder, Controller controller) {
        this.color = inputColor;
        this.placeholder = placeholder;
        this.setOpaque(false); // As suggested by @AVD in comment.
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(color);
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
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public RoundedTextField(String placeholder, Boolean enabled, Controller controller) {
        this(placeholder, controller);
        if (Boolean.FALSE.equals(enabled)) {
            this.setEnabled(false);
        }
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
        //set a red placeholder error for 3 seconds before reverting to normal
        this.setForeground(errorRed);
        this.setText(error);
        RoundedTextField textField = this;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        textField.setText(placeholder);
                        textField.setForeground(Color.WHITE);
                        textField.setEnabled(true);
                    }
                },
                3000
        );

    }
}