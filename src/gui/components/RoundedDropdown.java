package gui.components;

import gui.Controller;
import utilities.Parameters;

import javax.swing.*;
import java.awt.*;

public class RoundedDropdown extends JComboBox<String> implements Parameters {
    private final String text;
    public RoundedDropdown(String text, Controller controller) {
        // TODO: make this actually rounded
        super(new String[] {});
        // this.color = inputColor;
        // this.setSelectedIndex(0);
        this.text = text;
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(inputColor);
        this.setFont(inputFont);
        this.setBorder(componentInsets);
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
