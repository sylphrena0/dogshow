package GUI.pages;

import GUI.*;
import GUI.roundedComponents.RoundedButton;
import GUI.roundedComponents.RoundedPanel;
import GUI.roundedComponents.RoundedPasswordField;
import GUI.roundedComponents.RoundedTextField;
import config.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage extends JPanel implements ConfigParameters {
    private Controller controller;
    private CardLayout authLayout;
    private JPanel authPanel;
    private final Dimension authPanelSize = new Dimension((int) (0.7*screenSize.width), 730);
    public HomePage() {
        this.controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {

        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());

        authLayout = new CardLayout();
        authPanel = new JPanel(authLayout);
        authPanel.setMaximumSize(authPanelSize);

        authPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
        authPanel.setOpaque(false);

        ///////////////////////////////////
        /////////// Login Panel ///////////
        ///////////////////////////////////
        JPanel loginPanel = new RoundedPanel();
        loginPanel.setBackground(headerColor);

        JLabel loginHeader = new JLabel("Welcome! Please Login:");
        loginHeader.setFont(headerFont);
        loginHeader.setForeground(Color.WHITE);
        loginHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedButton loginButton = new RoundedButton("Login", new Color(0x93c47d), Color.BLACK, controller);
        loginButton.setActionCommand("LOGIN");

        RoundedButton registerPageButton = new RoundedButton("Register", new Color(0x7c50e2), Color.WHITE, controller);
        registerPageButton.setActionCommand("REGISTER_PAGE");

        GridLayout buttonLayout = new GridLayout(1,2, 40,40);
        JPanel loginButtonPanel = new JPanel(buttonLayout);

        loginButtonPanel.add(loginButton);
        loginButtonPanel.add(registerPageButton);
        loginButtonPanel.setOpaque(false);

        RoundedTextField loginUsername = new RoundedTextField("Username", controller);
        RoundedPasswordField loginPassword = new RoundedPasswordField("Password", controller);

        GridLayout loginContentLayout = new GridLayout(3,1,40,40);
        JPanel loginContent = new JPanel(loginContentLayout);

        loginContent.add(loginUsername);
        loginContent.add(loginPassword);
        loginContent.add(loginButtonPanel);
        loginContent.setOpaque(false);

        GroupLayout loginLayout = new GroupLayout(loginPanel);
        loginLayout.setAutoCreateGaps(true);
        loginLayout.setAutoCreateContainerGaps(true);

        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                                .addGap(60)
                                .addComponent(loginHeader)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(loginContent, 500, 500, 500)
                        )
        );
        loginLayout.setVerticalGroup(
                loginLayout.createSequentialGroup()
                        .addGap(40)
                        .addComponent(loginHeader)
                        .addGap(40)
                        .addComponent(loginContent, 300, 300, 300)
                        .addGap(100)


        );

        loginPanel.setLayout(loginLayout);

        ////////////////////////////////////
        //////// Registration Panel ////////
        ////////////////////////////////////
        JPanel registerPanel = new RoundedPanel();
        registerPanel.setBackground(headerColor);

        JLabel registerHeader = new JLabel("Welcome! Please Register:");
        registerHeader.setFont(headerFont);
        registerHeader.setForeground(Color.WHITE);
        registerHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedButton registerButton = new RoundedButton("Register", new Color(0x93c47d), Color.BLACK, controller);
        registerButton.setActionCommand("REGISTER");

        RoundedButton loginPageButton = new RoundedButton("Login", new Color(0x7c50e2), Color.WHITE, controller);
        loginPageButton.setActionCommand("LOGIN_PAGE");

        JPanel registerButtonPanel = new JPanel(buttonLayout); //used buttonLayout from loginPanel

        registerButtonPanel.add(registerButton);
        registerButtonPanel.add(loginPageButton);
        registerButtonPanel.setOpaque(false);

        RoundedTextField registerName = new RoundedTextField("Name", controller);
        RoundedTextField registerEmail = new RoundedTextField("Email", controller);
        RoundedTextField registerUsername = new RoundedTextField("Username", controller);
        RoundedPasswordField registerPassword = new RoundedPasswordField("Password", controller);
        RoundedPasswordField registerConfirmPassword = new RoundedPasswordField("Confirm Password", controller);

        GridLayout registerContentLayout = new GridLayout(3,2,40,40);
        JPanel registerContent = new JPanel(registerContentLayout);

        registerContent.add(registerName);
        registerContent.add(registerPassword);

        registerContent.add(registerEmail);
        registerContent.add(registerConfirmPassword);

        registerContent.add(registerUsername);
        registerContent.add(registerButtonPanel);

        registerContent.setOpaque(false);

        GroupLayout registerLayout = new GroupLayout(registerPanel);
        registerLayout.setAutoCreateGaps(true);
        registerLayout.setAutoCreateContainerGaps(true);

        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(GroupLayout.Alignment.LEADING, registerLayout.createSequentialGroup()
                                .addGap(60)
                                .addComponent(registerHeader)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, registerLayout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(registerContent, 840, 1040, 1240)
                        )
        );
        registerLayout.setVerticalGroup(
                registerLayout.createSequentialGroup()
                        .addGap(40)
                        .addComponent(registerHeader)
                        .addGap(40)
                        .addComponent(registerContent, 300, 300, 300)
                        .addGap(100)

        );

        registerPanel.setLayout(registerLayout);

        authPanel.add(loginPanel);
        authPanel.add(registerPanel);
        authLayout.first(authPanel);

        try {
            //scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            BufferedImage dogImage = ImageIO.read(new File(path + "images/dogs.png"));
            ImageIcon imageIcon = new ImageIcon(dogImage); // load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance(screenSize.width, (int) (screenSize.width*15/64),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(scaledImage);  // transform it back

            JLabel dogImageLabel = new JLabel(imageIcon);
            this.add(dogImageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        this.add(authPanel, BorderLayout.NORTH);
    }
    public void switchAuthPanel() {
        authLayout.next(authPanel);
    }

}
