package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedPanel;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class ContestList extends JPanel implements ConfigParameters {
    private final Controller controller;

    public ContestList() {
        this.controller = Controller.getInstance();
        addComponents();
    }

    private void addComponents() {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,2*pagePadding, pagePadding));

        JPanel contestPanel = new RoundedPanel();
        contestPanel.setBackground(pageColor);
        this.add(contestPanel);

        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Eligible"};
        Object[][] data = {
                {"4912", "Balto", 10, 9, 6, 8, false}
        };
        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        contestPanel.setLayout(new BorderLayout());
        contestPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        contestPanel.add(table, BorderLayout.CENTER);
    }
}
