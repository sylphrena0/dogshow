package GUI;

import config.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class RegistrationPage extends JPanel implements ConfigParameters {
    private Controller controller;

    public RegistrationPage() {
        this.controller = Controller.getInstance("Registration - Dog Show");
        addComponents();
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());

    }
}
