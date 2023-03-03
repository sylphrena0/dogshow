package GUI;

import GUI.pages.HomePage;
import GUI.pages.RecordsPage;
import GUI.pages.RegistrationPage;
import GUI.pages.ContestPage;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame implements ActionListener, ConfigParameters {
    private JButton home, records, registration, contest;
    private static Controller instance;
    private HomePage homePage;
    private CardLayout pageLayout;
    private JPanel pagePanel;

    private Controller(String title) {
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
        return getInstance("Home - Dog Show");
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
        button.setFont(headerFont);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
    }
    private void addComponents() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBackground(backgroundColor);

        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(screenSize.width, 120));
        navPanel.setBackground(headerColor);
        navPanel.setForeground(Color.WHITE);

        JLabel header = new JLabel("  Williamsport Area Kennel Club");
        header.setFont(headerFont);
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

        JPanel titlePanel = new JPanel();
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

        pageLayout = new CardLayout();
        pagePanel = new JPanel(pageLayout);

        homePage = new HomePage();
        RecordsPage recordsPage = new RecordsPage();
        RegistrationPage registrationPage = new RegistrationPage();
        ContestPage contestPage = new ContestPage();

        pagePanel.add("HOME", homePage);
        pagePanel.add("RECORDS", recordsPage);
        pagePanel.add("REGISTRATION", registrationPage);
        pagePanel.add("CONTEST", contestPage);

        containerPanel.add(pagePanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            case "HOME" -> {
                home.setBackground(backgroundColor);
                records.setBackground(headerColor);
                registration.setBackground(headerColor);
                contest.setBackground(headerColor);

                setTitle("Home - Dog Show");
                pageLayout.show(pagePanel, "HOME");
            }
            case "RECORDS" -> {
                records.setBackground(backgroundColor);
                home.setBackground(headerColor);
                registration.setBackground(headerColor);
                contest.setBackground(headerColor);

                setTitle("Records - Dog Show");
                pageLayout.show(pagePanel, "RECORDS");
            }
            case "REGISTRATION" -> {
                registration.setBackground(backgroundColor);
                home.setBackground(headerColor);
                records.setBackground(headerColor);
                contest.setBackground(headerColor);

                setTitle("Registration - Dog Show");
                pageLayout.show(pagePanel, "REGISTRATION");
            }
            case "CONTEST" -> {
                contest.setBackground(backgroundColor);
                home.setBackground(headerColor);
                records.setBackground(headerColor);
                registration.setBackground(headerColor);

                setTitle("Contest - Dog Show");
                pageLayout.show(pagePanel, "CONTEST");
            }
            case "LOGIN" -> {
                System.out.println("LOGIN");
            }
            case "REGISTER" -> {
                System.out.println("REGISTER");
            }
            case "LOGIN_PAGE" -> {
                System.out.println("LOGIN_PAGE");
                homePage.switchAuthPanel();
            }
            case "REGISTER_PAGE" -> {
                System.out.println("REGISTER_PAGE");
                homePage.switchAuthPanel();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> Controller.getInstance("Dog Show"));
    }

}
