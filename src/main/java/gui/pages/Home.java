package gui.pages;

import gui.Controller;
import gui.components.RoundedButton;
import gui.components.RoundedPanel;
import gui.components.RoundedPasswordField;
import gui.components.RoundedTextField;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import utilities.Constants;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {
    private final Controller controller;
    private CardLayout authLayout;
    private JPanel authPanel;
    @Getter RoundedTextField registerName, registerEmail, registerUsername, loginUsername;
    @Getter RoundedPasswordField registerPassword, registerConfirmPassword, loginPassword;
    public Home() {
        this.controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {
        // "this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(Constants.backgroundColor);
        this.setPreferredSize(Constants.pageSize);
        this.setLayout(new BorderLayout());

        authLayout = new CardLayout();
        authPanel = new JPanel(authLayout);

        authPanel.setBorder(BorderFactory.createEmptyBorder(Constants.PAGE_PADDING, Constants.PAGE_PADDING, Constants.PAGE_PADDING, Constants.PAGE_PADDING));
        authPanel.setOpaque(false);

        ///////////////////////////////////
        /////////// Login Panel ///////////
        ///////////////////////////////////
        RoundedPanel loginPanel = new RoundedPanel();

        JLabel loginHeader = new JLabel("Welcome! Please Login:");
        loginHeader.setFont(Constants.headerFont);
        loginHeader.setForeground(Color.WHITE);
        loginHeader.setHorizontalAlignment(SwingConstants.LEFT);

        loginUsername = new RoundedTextField("Username", controller);
        loginPassword = new RoundedPasswordField("Password", controller);
        loginPassword.setActionCommand("LOGIN");

        RoundedButton loginButton = new RoundedButton("Login", Constants.lightPurpleButtonColor, Color.BLACK, controller);

        loginButton.setActionCommand("LOGIN");

        loginPanel.setLayout(new MigLayout(
                "insets %d, gap %d, al left".formatted(Constants.GRID_PADDING, Constants.GRID_PADDING), // Layout Constraints
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
        registerPanel.setBackground(Constants.pageColor);

        JLabel registerHeader = new JLabel("Welcome! Please Register:");
        registerHeader.setFont(Constants.headerFont);
        registerHeader.setForeground(Color.WHITE);
        registerHeader.setHorizontalAlignment(SwingConstants.LEFT);

        registerName = new RoundedTextField("Name", controller);
        registerEmail = new RoundedTextField("Email", controller);
        registerUsername = new RoundedTextField("Username", controller);
        registerPassword = new RoundedPasswordField("Password", controller);
        registerConfirmPassword = new RoundedPasswordField("Confirm Password", controller);
        registerConfirmPassword.setActionCommand("REGISTER");

        RoundedButton registerButton = new RoundedButton("Register", Constants.lightPurpleButtonColor, Color.BLACK, controller);

        registerButton.setActionCommand("REGISTER");



        registerPanel.setLayout(new MigLayout(
                "insets %d, gap %d, al left".formatted(Constants.GRID_PADDING, Constants.GRID_PADDING), // Layout Constraints
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

        // scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        ImageIcon imageIcon = Utilities.getImageIcon("dogs.png"); // lad the image to a imageIcon
        Image scaledImage = imageIcon.getImage().getScaledInstance(Constants.screenSize.width, (int) (Constants.screenSize.width*15.0/64.0),  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(scaledImage);  // transform it back

        JLabel dogImageLabel = new JLabel(imageIcon);
        this.add(dogImageLabel, BorderLayout.CENTER);
        this.add(authPanel, BorderLayout.NORTH);
    }

    public void switchAuthPanel() {
        authLayout.next(authPanel);
    }

    public void hideAuthPanel() {
        //remove auth panel
        authPanel.setVisible(false);
    }
}
