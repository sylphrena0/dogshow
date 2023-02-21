package GUI;

import config.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel implements ConfigParameters {
    private Controller controller;
    private JPanel homePanel, authPanel, loginPanel, registrationPanel, dogImagePanel;
    private JPasswordField passwordField;
    private JTextField usernameField;

    public HomePanel() {
        this.controller = Controller.getInstance("Home - Dog Show");
        addComponents();
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extend JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());


        CardLayout authLayout = new CardLayout();
        authPanel = new JPanel(authLayout);
        authPanel.setPreferredSize(new Dimension((int) (0.7*screenSize.width), (int) (0.5*screenSize.height)));

        authPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 600));
        authPanel.setBackground(backgroundColor);

//        authPanel.setUndecorated(true);
//        authPanel.setShape(new RoundRectangle2D.Double(x, y, 100, 100, 50, 50));


        loginPanel = new RoundedPanel();
        registrationPanel = new JPanel();

        loginPanel.setBackground(headerColor);
        registrationPanel.setBackground(headerColor);


        JLabel usernameLabel = new JLabel("Enter your login: ", JLabel.CENTER);
        usernameLabel.setLabelFor(usernameField);

        JLabel passwordLabel = new JLabel("Enter the password: ", JLabel.CENTER);
        passwordLabel.setLabelFor(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(controller);

        JButton createAccount = new JButton("Create");
        createAccount.addActionListener(controller);

        usernameField = new JTextField("jdoe@somesuch",10);
        usernameField.setActionCommand("LOGIN");
        usernameField.addActionListener(controller);

        passwordField = new JPasswordField(10);
        passwordField.setActionCommand("PASSWORD");
        passwordField.addActionListener(controller);

        GroupLayout loginLayout = new GroupLayout(loginPanel);
        loginLayout.setAutoCreateGaps(false);
        loginLayout.setAutoCreateContainerGaps(false);

        loginLayout.setHorizontalGroup(
                loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameLabel)
                                .addComponent(passwordLabel)
                                .addComponent(loginButton))
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(usernameField)
                                .addComponent(passwordField)
                                .addComponent(createAccount))
        );
        loginLayout.setVerticalGroup(
                loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(usernameLabel)
                                .addComponent(usernameField))
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(loginButton)
                                .addComponent(createAccount))
        );

        loginPanel.setLayout(loginLayout);


        authPanel.add(loginPanel);
        authPanel.add(registrationPanel);

        authLayout.first(authPanel);

        this.add(authPanel, BorderLayout.NORTH);
    }


}
