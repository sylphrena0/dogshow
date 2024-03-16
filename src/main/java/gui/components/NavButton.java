package gui.components;

import gui.Controller;
import utilities.Constants;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class NavButton extends JButton {
    Controller controller = Controller.getInstance();
    public NavButton(String text) {
        super(text);
        this.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        this.setMinimumSize(new Dimension(Utilities.relativeHeight(7), Utilities.relativeHeight(6.5)));
        this.setBackground(Constants.pageColor);
        this.setForeground(Color.WHITE);
        this.setFont(Constants.headerFont);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.addActionListener(controller);
    }
}
