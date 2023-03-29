package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import net.miginfocom.swing.MigLayout;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class TableLayout extends JPanel implements ConfigParameters {
    private final Controller controller;

    protected JComponent imagePanel;

    protected int gridPaddingRegistration = Scaling.relativeHeight(4);;

    public TableLayout() {
        this.controller = Controller.getInstance();
    }

    protected void addComponents(JComponent header, JComponent dropdowns,
                                 JComponent inputPanel00, JComponent inputPanel01,
                                 JComponent inputPanel10, JComponent inputPanel11,
                                 JComponent inputPanel20, JComponent inputPanel21,
                                 JComponent inputPanel30, JComponent inputPanel31,
                                 JComponent inputPanel40, JComponent inputPanel41,
                                 JComponent register,
                                 JComponent imageLoaderButton,
                                 JComponent upload) {

        this.imagePanel = imageLoaderButton;
//        imagePanel.setMaximumSize(new Dimension(Scaling.relativeWidth((100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4))/3), Scaling.relativeHeight(8.08/64.48 * 75.5 - 2.0*4.5 - 2.0*4 - 4.2)));


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

        JPanel contentPanel = new JPanel();
        contentPanel.setMaximumSize(new Dimension(Scaling.relativeWidth(100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4)), Scaling.relativeHeight(75.5 - 2.0*4.5 - 2.0*4 - 4.2)));
        contentPanel.setOpaque(false);

        contentPanel.setLayout(new MigLayout(
                "insets %d, gap %d, fill".formatted(gridPaddingRegistration, gridPaddingRegistration), // Layout Constraints
                "[fill, sg][fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill, sg][fill, sg][fill, sg][fill, sg][fill, sg][fill, sg][fill, sg]" // Row constraints
        ));

        if (dropdowns == null) {
            contentPanel.add(header, "span 3, wrap");
        } else {
            contentPanel.add(header, "span 2"); contentPanel.add(dropdowns, "wrap");
        }
        contentPanel.add(inputPanel00); contentPanel.add(inputPanel01); contentPanel.add(imagePanel, "span 1 5, wrap");
        contentPanel.add(inputPanel10); contentPanel.add(inputPanel11, "wrap");
        contentPanel.add(inputPanel20); contentPanel.add(inputPanel21, "wrap");
        contentPanel.add(inputPanel30); contentPanel.add(inputPanel31, "wrap");
        contentPanel.add(inputPanel40); contentPanel.add(inputPanel41, "wrap");
        if (upload != null) {
            contentPanel.add(register, "span 2");
            contentPanel.add(upload);
        } else {
            contentPanel.add(register, "span 3");
        }

        registrationPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(registrationPanel);
    }
    private void configureGBC() {

    }
}

