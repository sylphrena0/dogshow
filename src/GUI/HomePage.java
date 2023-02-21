package GUI;

import config.ConfigParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HomePage extends JPanel implements ConfigParameters {
    private Controller controller;
    private JPanel homePanel, authPanel, loginPanel, registrationPanel, dogImagePanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private Color inputColor = new Color(0x434343);
    private Dimension authPanelSize = new Dimension((int) (0.7*screenSize.width), (int) (0.5*screenSize.height));

    public HomePage() {
        this.controller = Controller.getInstance("Home - Dog Show");
        addComponents();
    }

    private void configureInputField(JTextField textField, String placeholder) {
        textField.addActionListener(controller);
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        textField.setSize(new Dimension((int) (0.5*authPanelSize.width), (int) (0.25*authPanelSize.height)));
        textField.setFont(new Font("Arial", 1, 24));
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void configurePasswordField(JPasswordField passwordField, String placeholder) {
        passwordField.addActionListener(controller);
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        passwordField.setSize(new Dimension((int) (0.5*authPanelSize.width), (int) (0.25*authPanelSize.height)));
        passwordField.setFont(new Font("Arial", 1, 24));
        passwordField.setEchoChar((char) 0); //sets password to visible
        passwordField.setText(placeholder);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*'); //sets password to invisible
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0); //sets password to visible
                }
            }
        });
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extend JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());


        CardLayout authLayout = new CardLayout();
        authPanel = new JPanel(authLayout);
        authPanel.setPreferredSize(authPanelSize);

        authPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 600));
        authPanel.setBackground(backgroundColor);

        loginPanel = new RoundedPanel();
        registrationPanel = new RoundedPanel();

        loginPanel.setBackground(headerColor);
        registrationPanel.setBackground(headerColor);

        JLabel welcomeText = new JLabel("Welcome! Please Login:");
        welcomeText.setFont(new Font("Caveat",1, 75));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);


        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(controller);
        loginButton.setBackground(new Color(0x93c47d));
        loginButton.setForeground(Color.BLACK);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(controller);
        registerButton.setBackground(new Color(0x7c50e2));
        registerButton.setForeground(Color.WHITE);

        usernameField = new RoundedTextField(inputColor);
        usernameField.setActionCommand("LOGIN");
        configureInputField(usernameField, "Username");

        passwordField = new RoundedPasswordField(inputColor);
        passwordField.setActionCommand("PASSWORD");
        configurePasswordField(passwordField, "Password");

        GroupLayout loginLayout = new GroupLayout(loginPanel);
        loginLayout.setAutoCreateGaps(true);
        loginLayout.setAutoCreateContainerGaps(true);

        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                                .addGap(60)
                                .addComponent(welcomeText)
                        )
                        .addComponent(usernameField, 400, 500, 600)
                        .addComponent(passwordField, 400, 500, 600)
                        .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(loginButton)
                                .addComponent(registerButton))
        );
        loginLayout.setVerticalGroup(
                loginLayout.createSequentialGroup()
                        .addGap(30)
                        .addComponent(welcomeText)
                        .addComponent(usernameField, 30, 50, 70)
                        .addComponent(passwordField, 30, 50, 70)
                        .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(loginButton)
                                .addComponent(registerButton))
        );

        loginPanel.setLayout(loginLayout);


        authPanel.add(loginPanel);
        authPanel.add(registrationPanel);

        authLayout.first(authPanel);

        this.add(authPanel, BorderLayout.NORTH);
    }
}
