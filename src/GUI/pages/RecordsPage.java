package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedPanel;
import config.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class RecordsPage extends JPanel implements ConfigParameters {
    private Controller controller;

    public RecordsPage() {
        this.controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(80,80,160,80));

        JPanel recordsPanel = new RoundedPanel();
        recordsPanel.setBackground(headerColor);
        this.add(recordsPanel);
    }
}
