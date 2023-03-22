package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class TableLayout extends JPanel implements ConfigParameters {
    private final Controller controller;

    protected int group2width;

    protected int group1height;

    protected int gridPaddingRegistration = Scaling.relativeHeight(4);;

    public TableLayout() {
        this.controller = Controller.getInstance();
    }

    protected void addComponents(JComponent headerRow,
                               JComponent gridPanel00, JComponent gridPanel01,
                               JComponent gridPanel10, JComponent gridPanel11,
                               JComponent gridPanel20, JComponent gridPanel21,
                               JComponent gridPanel30, JComponent gridPanel31,
                               JComponent gridPanel40, JComponent gridPanel41,
                               JComponent gridButton,
                               JComponent imageLoader,
                               JComponent uploadButton) {

        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,2*pagePadding, pagePadding));

        JPanel registrationPanel = new RoundedPanel();
        registrationPanel.setLayout(new BorderLayout());
        registrationPanel.setBackground(headerColor);

        JPanel gridPanel = new JPanel(new GridLayout(5, 2, gridPaddingRegistration,gridPaddingRegistration));
        gridPanel.setOpaque(false);


        gridPanel.add(gridPanel00);
        gridPanel.add(gridPanel01);

        gridPanel.add(gridPanel10);
        gridPanel.add(gridPanel11);

        gridPanel.add(gridPanel20);
        gridPanel.add(gridPanel21);

        gridPanel.add(gridPanel30);
        gridPanel.add(gridPanel31);

        gridPanel.add(gridPanel40);
        gridPanel.add(gridPanel41);


        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);

        GroupLayout contentLayout = new GroupLayout(contentPanel);
        contentLayout.setAutoCreateGaps(true);
        contentLayout.setAutoCreateContainerGaps(true);

        int group1width = Scaling.relativeWidth(2.0*((100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4))/3.0));
        group2width = Scaling.relativeWidth((100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4))/3);
        contentLayout.setHorizontalGroup(
                contentLayout.createSequentialGroup()
                        .addGap(pagePadding)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(headerRow)
                                .addComponent(gridPanel, group1width, group1width, group1width)
                                .addComponent(gridButton, group1width, group1width, group1width)
                        )
                        .addGap(gridPaddingRegistration)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(imageLoader, group2width, group2width, group2width)
                                .addComponent(uploadButton, group2width, group2width, group2width)
                        )
                        .addGap(pagePadding)

        );

        double contentHeight = 75.5 - 2.0*4.5 - 2.0*4 - 4.2;
        group1height = Scaling.relativeHeight(56.4/64.48 * contentHeight);
        int group2height = Scaling.relativeHeight(8.08/64.48 * contentHeight);

        contentLayout.setVerticalGroup(
                contentLayout.createSequentialGroup()
                        .addGap(pagePadding)
                        .addComponent(headerRow)
                        .addGap(gridPaddingRegistration)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(gridPanel, group1height, group1height, group1height)
                                .addComponent(imageLoader, group1height, group1height, group1height)
                        )
                        .addGap(gridPaddingRegistration)
                        .addGroup(contentLayout.createParallelGroup()
                                .addComponent(gridButton, group2height, group2height, group2height)
                                .addComponent(uploadButton, group2height, group2height, group2height)
                        )
                        .addGap(pagePadding)

        );

        contentPanel.setLayout(contentLayout);
        registrationPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(registrationPanel);


    }
}

