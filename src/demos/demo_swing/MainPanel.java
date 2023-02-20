package demos.demo_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame implements ActionListener {
    private final int WIDTH=800, HEIGHT=600;
    private JPanel containerPanel, cardPanel;
    private ButtonPanel buttonPanel;
    private ImagePanel imagePanel;
    private ContentPanel contentPanel;
    private static MainPanel instance;
    private MainPanel(String title) {
        //init setup
        instance = this;
        setTitle(title);
        //setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenDimension = getToolkit().getScreenSize();
        setLocation(screenDimension.width/2 - WIDTH/2, screenDimension.height/2 - HEIGHT/2);
        addComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting UI look and feel!");
            System.exit(0);
        }
        pack();
        setVisible(true);
    }

    public static MainPanel getInstance() {
        return getInstance("Demo");
    }

    public static MainPanel getInstance(String title) {
        if (instance == null) {
            new MainPanel(title); //constructor sets instance variable
        } else if (!instance.getTitle().equals(title)) {
            instance.setTitle(title);
        }
        return instance;
    }

    private void addComponents() {
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        buttonPanel = new ButtonPanel();
        containerPanel.add(buttonPanel, BorderLayout.LINE_END);

        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout(10, 10));
        //add the various panels to the switched to this container
        imagePanel = new ImagePanel();
        contentPanel = new ContentPanel();

        cardPanel.add(imagePanel, "Image");
        cardPanel.add(contentPanel, "Content");

        containerPanel.add(cardPanel, BorderLayout.CENTER);
        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
        cardLayout.show(cardPanel, actionCommand);
        containerPanel.invalidate();
        containerPanel.repaint();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MainPanel.getInstance("Test Demo"));
    }

}
