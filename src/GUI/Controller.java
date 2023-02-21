package GUI;

import config.ConfigParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame implements ActionListener, ConfigParameters {
    private JPanel containerPanel, navPanel, titlePanel, bodyPanel;
    private JButton home, records, registration, contest;
    private HomePanel homePanel;
    private static Controller instance;

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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBackground(backgroundColor);

        navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(screenSize.width, 120));
        navPanel.setBackground(headerColor);
        navPanel.setForeground(Color.WHITE);

        JLabel header = new JLabel("  Williamsport Area Kennel Club");
        header.setFont(new Font("Caveat",1, 75));
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.LEFT);

        home  = new JButton(" Home ");
        records = new JButton(" Records ");
        registration = new JButton(" Registration ");
        contest = new JButton(" Contest ");

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

        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(headerColor);
        titlePanel.add(header);

        GroupLayout navLayout = new GroupLayout(navPanel);
        navLayout.setAutoCreateGaps(false);
        navLayout.setAutoCreateContainerGaps(false);
        navLayout.setHorizontalGroup(
                navLayout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addComponent(home)
                        .addComponent(records)
                        .addComponent(registration)
                        .addComponent(contest)
        );
        navLayout.setVerticalGroup(
                navLayout.createSequentialGroup()
                        .addGroup(navLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titlePanel)
                                .addComponent(home)
                                .addComponent(records)
                                .addComponent(registration)
                                .addComponent(contest)
                        )
        );

        navPanel.setLayout(navLayout);
        containerPanel.add(navPanel, BorderLayout.NORTH);

        bodyPanel = new JPanel(new CardLayout());
        bodyPanel.add(new HomePanel());

        containerPanel.add(bodyPanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            case "HOME":
                //CALL HOME
                System.out.println("HOME");
                home.setBackground(backgroundColor);
                records.setBackground(headerColor);
                registration.setBackground(headerColor);
                contest.setBackground(headerColor);
                break;
            case "RECORDS":
                //CALL RECORDS
                System.out.println("RECORDS");
                records.setBackground(backgroundColor);
                home.setBackground(headerColor);
                registration.setBackground(headerColor);
                contest.setBackground(headerColor);
                break;
            case "REGISTRATION":
                //CALL REGISTRATION
                System.out.println("REGISTRATION");
                registration.setBackground(backgroundColor);
                home.setBackground(headerColor);
                records.setBackground(headerColor);
                contest.setBackground(headerColor);
                break;
            case "CONTEST":
                //CALL CONTEST
                System.out.println("CONTEST");
                contest.setBackground(backgroundColor);
                home.setBackground(headerColor);
                records.setBackground(headerColor);
                registration.setBackground(headerColor);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> Controller.getInstance("Dog Show"));
    }

}
