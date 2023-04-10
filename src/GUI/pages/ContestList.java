package GUI.pages;

import GUI.Controller;
import GUI.components.ContestTable;
import GUI.components.ContestTableModel;
import GUI.components.RoundedPanel;
import GUI.components.Table;
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

        String[] columnNames = {"REG ID", "Name", "Obedience", "Socialization", "Grooming", "Fetch", "Eligible"};
        Object[][] data = {
                {"1234", "Balto", 7, 10, 9, 7, false}
        };

        ContestTableModel model = new ContestTableModel(columnNames, data);
        JTable table = new ContestTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Table newContentPane = new Table();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }
}
