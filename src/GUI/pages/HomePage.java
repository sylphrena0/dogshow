package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedButton;
import GUI.components.RoundedPanel;
import GUI.components.RoundedPasswordField;
import GUI.components.RoundedTextField;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomePage extends JPanel implements ConfigParameters {
    private final Controller controller;
    private CardLayout authLayout;
    private JPanel authPanel;
    public HomePage() {
        this.controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {

        int authContentHeight = Scaling.relativeHeight(17);

        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());

        authLayout = new CardLayout();
        authPanel = new JPanel(authLayout);

        authPanel.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding, pagePadding, pagePadding));
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

        RoundedButton loginButton = new RoundedButton("Login", greenButtonColor, Color.BLACK, controller);
        loginButton.setActionCommand("LOGIN");

        RoundedButton registerPageButton = new RoundedButton("Register", purpleButtonColor, Color.WHITE, controller);
        registerPageButton.setActionCommand("REGISTER_PAGE");

        GridLayout buttonLayout = new GridLayout(1,2, gridPadding, gridPadding);
        JPanel loginButtonPanel = new JPanel(buttonLayout);

        loginButtonPanel.add(loginButton);
        loginButtonPanel.add(registerPageButton);
        loginButtonPanel.setOpaque(false);

        RoundedTextField loginUsername = new RoundedTextField("Username", controller);
        RoundedPasswordField loginPassword = new RoundedPasswordField("Password", controller);

        GridLayout loginContentLayout = new GridLayout(3,1, gridPadding,gridPadding);
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
                                .addGap(Scaling.relativeWidth(2.1))
                                .addComponent(loginHeader)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                                .addGap(Scaling.relativeWidth(4.2))
                                .addComponent(loginContent, Scaling.relativeWidth(17.4), Scaling.relativeWidth(17.4), Scaling.relativeWidth(17.4))
                        )
        );
        loginLayout.setVerticalGroup(
                loginLayout.createSequentialGroup()
                        .addGap(gridPadding)
                        .addComponent(loginHeader)
                        .addGap(gridPadding)
                        .addComponent(loginContent, authContentHeight, authContentHeight, authContentHeight)
                        .addGap(Scaling.relativeHeight(5.5))


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

        RoundedButton registerButton = new RoundedButton("Register", greenButtonColor, Color.BLACK, controller);
        registerButton.setActionCommand("REGISTER");

        RoundedButton loginPageButton = new RoundedButton("Login", purpleButtonColor, Color.WHITE, controller);
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

        GridLayout registerContentLayout = new GridLayout(3,2, gridPadding, gridPadding);
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
                                .addGap(Scaling.relativeWidth(2.1))
                                .addComponent(registerHeader)
                        )
                        .addGroup(GroupLayout.Alignment.LEADING, registerLayout.createSequentialGroup()
                                .addGap(Scaling.relativeWidth(4.2))
                                .addComponent(registerContent, Scaling.relativeWidth(36), Scaling.relativeWidth(36), Scaling.relativeWidth(36))
                        )
        );
        registerLayout.setVerticalGroup(
                registerLayout.createSequentialGroup()
                        .addGap(gridPadding)
                        .addComponent(registerHeader)
                        .addGap(gridPadding) //40
                        .addComponent(registerContent, authContentHeight, authContentHeight, authContentHeight)
                        .addGap(Scaling.relativeHeight(5.5))

        );
        registerPanel.setLayout(registerLayout);

        authPanel.add(loginPanel);
        authPanel.add(registerPanel);
        authLayout.first(authPanel);

        try {
            //scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("images/dogs.png"))); // load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance(screenSize.width, (int) (screenSize.width*15.0/64.0),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
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
