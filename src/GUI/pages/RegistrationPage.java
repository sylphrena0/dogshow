package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import config.ConfigParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RegistrationPage extends JPanel implements ConfigParameters {
    private Controller controller;

    public RegistrationPage() {
        this.controller = Controller.getInstance();
        addComponents();
    }



    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(80,80,160,80));

        JPanel registrationPanel = new RoundedPanel();
        registrationPanel.setLayout(new BorderLayout());
        registrationPanel.setBackground(headerColor);

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(headerFont);
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel gridPanel = new JPanel(new GridLayout(5, 2, 40,40));
        gridPanel.setOpaque(false);

        JPanel ageAndColor = new JPanel(new GridLayout(1, 2, 40, 40));
        ageAndColor.setOpaque(false);

        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        RoundedCheckbox socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        RoundedCheckbox grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        RoundedCheckbox fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        ageAndColor.add(age);
        ageAndColor.add(color);

        gridPanel.add(familyName);
        gridPanel.add(markings);

        gridPanel.add(familyEmail);
        gridPanel.add(obedience);

        gridPanel.add(name);
        gridPanel.add(socialization);

        gridPanel.add(breed);
        gridPanel.add(grooming);

        gridPanel.add(ageAndColor);
        gridPanel.add(fetch);

        RoundedButton registerButton = new RoundedButton("Register", greenButtonColor, Color.BLACK, controller);

        ImageLoaderButton imageLoaderButton = new ImageLoaderButton();

        RoundedButton uploadButton = new RoundedButton("Upload Dog Picture", purpleButtonColor, Color.WHITE, controller);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);

        GroupLayout contentLayout = new GroupLayout(contentPanel);
        contentLayout.setAutoCreateGaps(true);
        contentLayout.setAutoCreateContainerGaps(true);


        contentLayout.setHorizontalGroup(
                contentLayout.createSequentialGroup()
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(registrationHeader)
                                .addComponent(gridPanel, 1040, 1040, 1040)
                                .addComponent(registerButton, 1040, 1040, 1040)
                        )
                        .addGap(40)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(imageLoaderButton, 500, 500, 500)
                                .addComponent(uploadButton, 500, 500, 500)
                        )
        );
        contentLayout.setVerticalGroup(
                contentLayout.createSequentialGroup()
                        .addComponent(registrationHeader)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(gridPanel, 535, 535, 535)
                                .addComponent(imageLoaderButton, 535, 535, 535)
                        )
                        .addGap(40)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(registerButton, 75, 75, 75)
                                .addComponent(uploadButton, 75, 75, 75)
                        )

        );

        contentPanel.setLayout(contentLayout);
        registrationPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(registrationPanel);


    }
}
