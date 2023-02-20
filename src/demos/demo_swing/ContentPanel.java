package demos.demo_swing;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private JPanel textPanel;

    public ContentPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        addComponents();
    }

    private void addComponents() {
        JTextArea textArea = new JTextArea(20,15);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setEditable(true);

        JScrollPane textScrollPane = new JScrollPane(textArea);

        setLayout(new BorderLayout());
        add(textScrollPane, BorderLayout.CENTER);
        textArea.setText("This is a sample text - type here!");
        textArea.repaint();
    }
}
