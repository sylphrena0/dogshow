package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TableLayout extends JPanel implements ConfigParameters {
    private final Controller controller;

    protected JComponent imagePanel;

    protected int gridPaddingRegistration = Scaling.relativeHeight(4);;

    public TableLayout() {
        this.controller = Controller.getInstance();
    }

    protected void addComponents(JComponent header,
                               JComponent inputPanel00, JComponent inputPanel01,
                               JComponent inputPanel10, JComponent inputPanel11,
                               JComponent inputPanel20, JComponent inputPanel21,
                               JComponent inputPanel30, JComponent inputPanel31,
                               JComponent inputPanel40, JComponent inputPanel41,
                               JComponent register,
                               JComponent imageLoaderButton,
                               JComponent upload) {

        this.imagePanel = imageLoaderButton;
        imagePanel.setSize(new Dimension(Scaling.relativeWidth((100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4))/3), Scaling.relativeHeight(8.08/64.48 * 75.5 - 2.0*4.5 - 2.0*4 - 4.2)));


        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,2*pagePadding, pagePadding));

        JPanel registrationPanel = new RoundedPanel();
        registrationPanel.setLayout(new BorderLayout());
        registrationPanel.setBackground(headerColor);


        JPanel inputPanel = new JPanel(new GridLayout(5, 2, gridPaddingRegistration, gridPaddingRegistration));
        inputPanel.setOpaque(false);

        inputPanel.add(inputPanel00); inputPanel.add(inputPanel01);
        inputPanel.add(inputPanel10); inputPanel.add(inputPanel11);
        inputPanel.add(inputPanel20); inputPanel.add(inputPanel21);
        inputPanel.add(inputPanel30); inputPanel.add(inputPanel31);
        inputPanel.add(inputPanel40); inputPanel.add(inputPanel41);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);

        GridBagLayout contentLayout = new GridBagLayout();
        contentPanel.setLayout(contentLayout);
        contentPanel.setSize(new Dimension(Scaling.relativeWidth(100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4)), Scaling.relativeHeight(75.5 - 2.0*4.5 - 2.0*4 - 4.2)));

        GridBagConstraints twoColumnConstraints = new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 10, GridBagConstraints.BOTH, new Insets(pagePadding,pagePadding,gridPaddingRegistration,pagePadding), 0, 0);
        contentPanel.add(header,twoColumnConstraints);

        GridBagConstraints oneColumnConstraints = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, GridBagConstraints.BOTH, new Insets(0,pagePadding,gridPaddingRegistration,gridPaddingRegistration), 0, 0);
        contentPanel.add(inputPanel, oneColumnConstraints);

        oneColumnConstraints.gridx = 1;
        oneColumnConstraints.insets = new Insets(0,0, gridPaddingRegistration, pagePadding);
        oneColumnConstraints.weightx = 0.0;
        oneColumnConstraints.weighty = 0.0;
        oneColumnConstraints.fill = GridBagConstraints.NONE;
        contentPanel.add(imageLoaderButton, oneColumnConstraints);

        twoColumnConstraints.gridy = 2;
        twoColumnConstraints.insets = new Insets(0, pagePadding, pagePadding, pagePadding);
        twoColumnConstraints.weightx = 1.0;
        twoColumnConstraints.weighty = 1.0;
        contentPanel.add(register, twoColumnConstraints);

        registrationPanel.add(contentPanel, BorderLayout.CENTER);

//        System.out.println(Scaling.absHeight(contentPanel.getHeight()));
        System.out.println(Scaling.absHeight(register.getHeight()));

        this.add(registrationPanel);
    }
    private void configureGBC() {

    }
}

