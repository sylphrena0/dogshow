package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class MainPanel extends JFrame implements ActionListener {
    private final int WIDTH=800, HEIGHT=600;
    private JPanel containerPanel, headerPanel;
    private static MainPanel instance;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private MainPanel(String title) {
        //init setup
        instance = this;
        setTitle(title);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting UI look and feel!");
            System.exit(0);
        }
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
        containerPanel.setLayout(new FlowLayout());
        containerPanel.setBackground(new Color(0x212121));

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(screenSize.width, 100));
        headerPanel.setBackground(new Color(0x303030));

        JLabel header = new JLabel(" Williamsport Area Kennel Club");
        header.setFont(new Font("Caveat",1, 75));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.LEFT);


        headerPanel.add(header);

        containerPanel.add(headerPanel);


        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
//        CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
//        cardLayout.show(cardPanel, actionCommand);
        containerPanel.invalidate();
        containerPanel.repaint();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MainPanel.getInstance("Home"));
    }

}
