package gui.components;

import gui.Controller;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;

public class RoundedDropdown extends JComboBox<String> {
    private final String text;
    public RoundedDropdown(String text, Controller controller) {
        // TODO: make this actually rounded
        super(new String[] {});
        this.text = text;
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(Constants.inputColor);
        this.setFont(Constants.inputFont);
        this.setBorder(Constants.componentInsets);
    }

    @Override
    public Object getSelectedItem() {
        Object selected = super.getSelectedItem();
        if (selected == null)
            selected = text;
        return selected;
    }

    public void setOptions(String[] options) {
        this.removeAllItems();
        for (String option : options) {
            this.addItem(option);
        }
    }
}
