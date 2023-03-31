package GUI.components;

import GUI.Controller;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedDropdown extends JComboBox implements ConfigParameters {
    private Shape shape;
    private String text;
    private final Color color;

    public RoundedDropdown(String[] options, String text, Controller controller) {
        super(options);
        this.color = inputColor;
        this.text = text;
        setOpaque(false); // As suggested by @AVD in comment.
        JComboBox dropdown = this;
        dropdown.setSelectedIndex(0);
        dropdown.addActionListener(controller);
        dropdown.setForeground(Color.WHITE);
        dropdown.setBackground(inputColor);
        dropdown.setFont(inputFont);
        dropdown.setBorder(new EmptyBorder(30, 30, 30, 30));

        dropdown.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
                basicComboPopup.setBorder(null);
                return basicComboPopup;
            }
        });

//        dropdown.setRenderer(new MyComboBoxRenderer());
//        dropdown.setEditor(new MyComboBoxEditor());

//        dropdown.setFocusPainted(false);
//        dropdown.setBorderPainted(false);
        Border border = BorderFactory.createLineBorder(Color.yellow);
        UIManager.put("ComboBox.Border", border);

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
}
