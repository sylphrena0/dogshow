package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame implements ActionListener {
    private JPanel containerPanel, headerPanel, navPanel;
    private static Controller instance;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Color backgroundColor = new Color(0x212121);
    private static Color headerColor = new Color(0x303030);
    private Controller(String title) {
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

    public static Controller getInstance() {
        return getInstance("Dog Show");
    }

    public static Controller getInstance(String title) {
        if (instance == null) {
            new Controller(title); //constructor sets instance variable
        } else if (!instance.getTitle().equals(title)) {
            instance.setTitle(title);
        }
        return instance;
    }

    private void configureNavButton(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setBackground(headerColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Caveat",1, 75));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
    }
    private void addComponents() {



        containerPanel = new JPanel();
        containerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        containerPanel.setBackground(backgroundColor);

        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(screenSize.width, 120));
        headerPanel.setBackground(headerColor);

        JLabel header = new JLabel("  Williamsport Area Kennel Club");
        header.setFont(new Font("Caveat",1, 75));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.LEFT);

        JButton home  = new JButton(" Home ");
        JButton records = new JButton(" Records ");
        JButton registration = new JButton(" Registration ");
        JButton contest = new JButton(" Contest ");

        Dimension button = new Dimension(200, 120);

        configureNavButton(home, button);
        configureNavButton(records, button);
        configureNavButton(registration, button);
        configureNavButton(contest, button);

        home.setBackground(backgroundColor);

        home.setActionCommand("HOME");
        records.setActionCommand("RECORDS");
        registration.setActionCommand("REGISTRATION");
        contest.setActionCommand("CONTEST");



        navPanel = new JPanel();
        navPanel.setLayout(new BorderLayout());
        navPanel.setBackground(headerColor);
        navPanel.add(header);

        GroupLayout navLayout = new GroupLayout(headerPanel);
        navLayout.setAutoCreateGaps(false);
        navLayout.setAutoCreateContainerGaps(false);
        navLayout.setHorizontalGroup(
                navLayout.createSequentialGroup()
                        .addComponent(navPanel)
                        .addComponent(home)
                        .addComponent(records)
                        .addComponent(registration)
                        .addComponent(contest)
        );
        navLayout.setVerticalGroup(
                navLayout.createSequentialGroup()
                        .addGroup(navLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(navPanel)
                                .addComponent(home)
                                .addComponent(records)
                                .addComponent(registration)
                                .addComponent(contest)
                        )
        );

        headerPanel.setLayout(navLayout);
        containerPanel.add(headerPanel);
        containerPanel.add(home);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            case "HOME":
                //CALL HOME
                HomeFrame home = new HomeFrame();
                containerPanel.add(home, FlowLayout.CENTER);
                break;
            case "RECORDS":
                //CALL RECORDS
                System.out.println("RECORDS");
                break;
            case "REGISTRATION":
                //CALL REGISTRATION
                System.out.println("REGISTRATION");
                break;
            case "CONTEST":
                //CALL CONTEST
                System.out.println("CONTEST");
                break;
        }
    }
}
