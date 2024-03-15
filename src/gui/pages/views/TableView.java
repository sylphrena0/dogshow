package gui.pages.views;

import gui.components.ImageLoaderButton;
import gui.components.RoundedButton;
import gui.components.RoundedPanel;
import gui.components.RoundedTextField;
import net.miginfocom.swing.MigLayout;
import utilities.Parameters;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel implements Parameters {
    protected ImageLoaderButton imageButton;

    protected int gridPaddingRegistration = Utilities.relativeHeight(3);
    protected MigLayout tableLayout;
    protected RoundedPanel contentPanel;

    protected void addComponents(JLabel header,
                                 RoundedTextField familyName, RoundedTextField markings,
                                 RoundedTextField email, JComponent obedience,
                                 RoundedTextField name, JComponent socialization,
                                 RoundedTextField breed, JComponent grooming,
                                 RoundedTextField age, RoundedTextField color, JComponent fetch,
                                 RoundedButton back, RoundedButton register,
                                 ImageLoaderButton imageLoaderButton) {

        this.imageButton = imageLoaderButton;

        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(PAGE_PADDING, PAGE_PADDING, PAGE_PADDING, PAGE_PADDING));

        contentPanel = new RoundedPanel();
        contentPanel.setBackground(pageColor);
        tableLayout = new MigLayout(
                "fill, insets %d, gap %d".formatted(gridPaddingRegistration, gridPaddingRegistration), // Layout Constraints
                "[sgx, fill][sgx, fill][sgx, fill]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[sgy, fill][sgy, fill][sgy, fill][sgy, fill][sgy, fill][sgy, fill][sgy, fill]" // Row constraints
        );
        contentPanel.setLayout(tableLayout);

        contentPanel.add(header, "span 3, wrap");

        contentPanel.add(familyName);
        contentPanel.add(markings);
        contentPanel.add(imageButton, "spany 5, wrap");

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

        this.add(contentPanel, BorderLayout.CENTER);
    }
}

