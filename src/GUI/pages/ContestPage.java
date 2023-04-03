package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedPanel;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class ContestPage extends JPanel implements ConfigParameters {
    private final Controller controller;
    public static JList b;

    public ContestPage() {
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

        //create a new label
        JLabel l= new JLabel("select year");

        //String array to store weekdays
        String dogs[]= { " "};

        //create list
        b= new JList(dogs);

        //set a selected index
        b.setSelectedIndex(2);

        //add list to panel
        contestPanel.add(b);

        //set the size of frame
        contestPanel.setSize(400,400);

        contestPanel.show();

    }
}
