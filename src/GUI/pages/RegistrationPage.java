package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

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
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,2*pagePadding, pagePadding));

        JPanel registrationPanel = new RoundedPanel();
        registrationPanel.setLayout(new BorderLayout());
        registrationPanel.setBackground(headerColor);

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(headerFont);
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel gridPanel = new JPanel(new GridLayout(5, 2, gridPadding,gridPadding));
        gridPanel.setOpaque(false);

        JPanel ageAndColor = new JPanel(new GridLayout(1, 2, gridPadding, gridPadding));
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


        int group1width = Scaling.relativeWidth(36);
        int group2width = Scaling.relativeWidth(17.4);
        contentLayout.setHorizontalGroup(
                contentLayout.createSequentialGroup()
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(registrationHeader)
                                .addComponent(gridPanel, group1width, group1width, group1width)
                                .addComponent(registerButton, group1width, group1width, group1width)
                        )
                        .addGap(gridPadding)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(imageLoaderButton, group2width, group2width, group2width)
                                .addComponent(uploadButton, group2width, group2width, group2width)
                        )
        );

        int group1height = Scaling.relativeHeight(30);
        int group2height = Scaling.relativeHeight(4);
        contentLayout.setVerticalGroup(
                contentLayout.createSequentialGroup()
                        .addComponent(registrationHeader)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(gridPanel, group1height, group1height, group1height)
                                .addComponent(imageLoaderButton, group1height, group1height, group1height)
                        )
                        .addGap(gridPadding)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(registerButton, group2height, group2height, group2height)
                                .addComponent(uploadButton, group2height, group2height, group2height)
                        )

        );

        contentPanel.setLayout(contentLayout);
        registrationPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(registrationPanel);


    }
}
