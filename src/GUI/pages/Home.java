package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedButton;
import GUI.components.RoundedPanel;
import GUI.components.RoundedPasswordField;
import GUI.components.RoundedTextField;
import net.miginfocom.swing.MigLayout;
import utilities.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Home extends JPanel implements ConfigParameters {
    private final Controller controller;
    private CardLayout authLayout;
    private JPanel authPanel;
    RoundedTextField registerName, registerEmail , registerUsername, loginUsername;
    RoundedPasswordField registerPassword, registerConfirmPassword, loginPassword;
    public Home() {
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

        authPanel.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding, pagePadding, pagePadding));
        authPanel.setOpaque(false);

        ///////////////////////////////////
        /////////// Login Panel ///////////
        ///////////////////////////////////
        RoundedPanel loginPanel = new RoundedPanel();

        JLabel loginHeader = new JLabel("Welcome! Please Login:");
        loginHeader.setFont(headerFont);
        loginHeader.setForeground(Color.WHITE);
        loginHeader.setHorizontalAlignment(SwingConstants.LEFT);

        loginUsername = new RoundedTextField("Username", controller);
        loginPassword = new RoundedPasswordField("Password", controller);
        RoundedButton loginButton = new RoundedButton("Login", lightPurpleButtonColor, Color.BLACK, controller);

        loginButton.setActionCommand("LOGIN");

        loginPanel.setLayout(new MigLayout(
                "insets %d, gap %d, al left".formatted(gridPadding, gridPadding), // Layout Constraints
                "[fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill, sg][fill, sg][fill, sg][fill, sg]" // Row constraints
        ));

        loginPanel.add(loginHeader, "wrap");
        loginPanel.add(loginUsername, "width 25%!, wrap");
        loginPanel.add(loginPassword, "width 25%!, wrap");
        loginPanel.add(loginButton);

        ////////////////////////////////////
        //////// Registration Panel ////////
        ////////////////////////////////////
        RoundedPanel registerPanel = new RoundedPanel();
        registerPanel.setBackground(pageColor);

        JLabel registerHeader = new JLabel("Welcome! Please Register:");
        registerHeader.setFont(headerFont);
        registerHeader.setForeground(Color.WHITE);
        registerHeader.setHorizontalAlignment(SwingConstants.LEFT);

        registerName = new RoundedTextField("Name", controller);
        registerEmail = new RoundedTextField("Email", controller);
        registerUsername = new RoundedTextField("Username", controller);
        registerPassword = new RoundedPasswordField("Password", controller);
        registerConfirmPassword = new RoundedPasswordField("Confirm Password", controller);
        RoundedButton registerButton = new RoundedButton("Register", lightPurpleButtonColor, Color.BLACK, controller);

        registerButton.setActionCommand("REGISTER");



        registerPanel.setLayout(new MigLayout(
                "insets %d, gap %d, al left".formatted(gridPadding, gridPadding), // Layout Constraints
                "[fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill, sg][fill, sg][fill, sg][fill, sg]" // Row constraints
        ));

        registerPanel.add(registerHeader, "span 2, wrap");

        registerPanel.add(registerName, "width 25%!");
        registerPanel.add(registerPassword, "width 25%!, wrap");

        registerPanel.add(registerEmail, "width 25%!");
        registerPanel.add(registerConfirmPassword, "width 25%!, wrap");

        registerPanel.add(registerUsername, "width 25%!");

        registerPanel.add(registerButton);

        ////////////////////////////////////

        authPanel.add(loginPanel);
        authPanel.add(registerPanel);
        authLayout.first(authPanel);

        try {
            //scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("images/dogs.png"))); //load the image to a imageIcon
            Image scaledImage = imageIcon.getImage().getScaledInstance(screenSize.width, (int) (screenSize.width*15.0/64.0),  java.awt.Image.SCALE_SMOOTH); //scale it the smooth way
            imageIcon = new ImageIcon(scaledImage);  //transform it back

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

    public void hideAuthPanel() {
        //remove auth panel
        authPanel.setVisible(false);
    }

    public RoundedTextField getRegisterName() {
        return registerName;
    }

    public RoundedTextField getRegisterEmail() {
        return registerEmail;
    }

    public RoundedTextField getRegisterUsername() {
        return registerUsername;
    }

    public RoundedPasswordField getRegisterPassword() {
        return registerPassword;
    }

    public RoundedPasswordField getRegisterConfirmPassword() {
        return registerConfirmPassword;
    }

    public RoundedTextField getLoginUsername() {
        return loginUsername;
    }

    public RoundedPasswordField getLoginPassword() {
        return loginPassword;
    }
}
