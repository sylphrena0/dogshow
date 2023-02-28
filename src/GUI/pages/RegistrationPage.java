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

        GridLayout gridLayout = new GridLayout(5, 2, 40,40);
        JPanel gridPanel = new JPanel(gridLayout);
        gridPanel.setOpaque(false);



        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);

        gridPanel.add(familyName);
        gridPanel.add(familyEmail);
        gridPanel.add(name);
        gridPanel.add(breed);
        gridPanel.add(age);
        gridPanel.add(color);
        gridPanel.add(obedience);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);

        registrationPanel.add(gridPanel);



    }
}
