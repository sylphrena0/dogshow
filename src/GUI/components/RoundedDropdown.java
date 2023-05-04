package GUI.components;

import GUI.Controller;
import utilities.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedDropdown extends JComboBox<String> implements Parameters {
    private Shape shape;
    private final String text;
    private final Color color;

    public RoundedDropdown(String text, Controller controller) {
        super(new String[] {});
        this.color = inputColor;
        this.text = text;
//        this.setSelectedIndex(0);
        this.addActionListener(controller);
        this.setForeground(Color.WHITE);
        this.setBackground(inputColor);
        this.setFont(inputFont);
        this.setBorder(componentInsets);
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

    @Override
    public Object getSelectedItem()
    {
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
