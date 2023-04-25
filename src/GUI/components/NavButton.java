package GUI.components;

import GUI.Controller;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class NavButton extends JButton implements ConfigParameters {
    Controller controller = Controller.getInstance();
    public NavButton(String text) {
        super(text);
        this.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        this.setMinimumSize(new Dimension(Scaling.relativeHeight(7), Scaling.relativeHeight(6.5)));
        this.setBackground(pageColor);
        this.setForeground(Color.WHITE);
        this.setFont(headerFont);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.addActionListener(controller);
    }

//    @Override
//    public void setEnabled(boolean b) {
//        super.setEnabled(b);
//        this.setBackground(backgroundColor);
//    }
}
