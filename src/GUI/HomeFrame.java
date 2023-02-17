package GUI;

import javax.swing.*;

public class HomeFrame extends JPanel {
    private Controller controller;
    private JPanel loginPanel, registrationPanel, dogImagePanel;
    private JPasswordField passwordField;
    private JTextField usernameField;

    public HomeFrame() {
        this.controller = Controller.getInstance("Home - Dog Show");
        addComponents();
    }

    private void addComponents() {
        loginPanel = new JPanel();

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

    }
}
