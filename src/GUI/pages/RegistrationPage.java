package GUI.pages;

import GUI.Controller;
import GUI.roundedComponents.RoundedCheckbox;
import GUI.roundedComponents.RoundedPanel;
import GUI.roundedComponents.RoundedTextField;
import config.ConfigParameters;

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
        this.setBorder(BorderFactory.createEmptyBorder(80,80,160,80));

        JPanel registrationPanel = new RoundedPanel();
        registrationPanel.setBackground(headerColor);
        this.add(registrationPanel);

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


        registrationPanel.add(gridPanel);



    }
}
