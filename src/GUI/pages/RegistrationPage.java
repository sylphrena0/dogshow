package GUI.pages;

import GUI.Controller;
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

        GridLayout gridLayout = new GridLayout(1,3,40,40);
        JPanel gridPanel = new JPanel(gridLayout);

        GridLayout gridColumnLayout = new GridLayout(5, 1, 40,40);
        JPanel gridColumnPanel1 = new JPanel(gridColumnLayout);
        JPanel gridColumnPanel2 = new JPanel(gridColumnLayout);

        RoundedTextField familyName = new RoundedTextField();
//        familyName.configureInputField();


    }
}
