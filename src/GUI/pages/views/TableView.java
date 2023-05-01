package GUI.pages.views;

import GUI.components.*;
import net.miginfocom.swing.MigLayout;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel implements ConfigParameters {
    protected JComponent imagePanel;

    protected int gridPaddingRegistration = Scaling.relativeHeight(3);

    protected void addComponents(JComponent header,
                                 JComponent familyName, JComponent markings,
                                 JComponent email, JComponent obedience,
                                 JComponent name, JComponent socialization,
                                 JComponent breed, JComponent grooming,
                                 JComponent age, JComponent color, JComponent fetch,
                                 JComponent back, JComponent register,
                                 JComponent imageLoaderButton) {

        this.imagePanel = imageLoaderButton;

        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding, 2 * pagePadding, pagePadding));

        JPanel tablePanel = new RoundedPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(pageColor);

        JPanel contentPanel = new JPanel();
        contentPanel.setMaximumSize(new Dimension(Scaling.relativeWidth(100.0 - 4 * Scaling.heightToWidth(4.5) - Scaling.heightToWidth(4)), Scaling.relativeHeight(75.5 - 2.0 * 4.5 - 2.0 * 4 - 4.2)));
        contentPanel.setOpaque(false);

        contentPanel.setLayout(new MigLayout(
                "insets %d, gap %d, fill".formatted(gridPaddingRegistration, gridPaddingRegistration), // Layout Constraints
                "[fill, sg][fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill, sg][fill, sg][fill, sg][fill, sg][fill, sg][fill, sg][fill, sg]" // Row constraints
        ));

        contentPanel.add(header, "span 3, wrap");

        contentPanel.add(familyName);
        contentPanel.add(markings);
        contentPanel.add(imagePanel, "span 1 5, wrap");

        contentPanel.add(email);
        contentPanel.add(obedience, "wrap");

        contentPanel.add(name);
        contentPanel.add(socialization, "wrap");

        contentPanel.add(breed);
        contentPanel.add(grooming, "wrap");

        contentPanel.add(age, "split 2");
        contentPanel.add(color, "gap %d".formatted(gridPaddingRegistration));
        contentPanel.add(fetch, "wrap");

        if (back != null) {
            contentPanel.add(back, "span 3, split 2, wmax 5%");
            contentPanel.add(register, "gap %d".formatted(gridPaddingRegistration / 2));
        } else {
            contentPanel.add(register, "span 4");
        }


        tablePanel.add(contentPanel, BorderLayout.CENTER);

        this.add(tablePanel);
    }
}

