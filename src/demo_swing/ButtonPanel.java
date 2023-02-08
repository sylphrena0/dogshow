package demo_swing;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private MainPanel mainPanel;
    private final int WIDTH=200, HEIGHT=600;

    public ButtonPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
    }

    private void addComponents() {
        JButton b1 = new JButton("Image");
        JButton b2 = new JButton("Content");

        Dimension largeSize = new Dimension(100, 100);
        b1.setMaximumSize(largeSize);
        b2.setMaximumSize(largeSize);
        b1.addActionListener(mainPanel);
        b2.addActionListener(mainPanel);

        add(b1);
        add(b2);

    }

}
