package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedPanel;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class RecordsPage extends JPanel implements ConfigParameters {

    public RecordsPage() {
        Controller controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,2*pagePadding, pagePadding));

        JPanel recordsPanel = new RoundedPanel();
        recordsPanel.setBackground(headerColor);
        this.add(recordsPanel);
    }
}
