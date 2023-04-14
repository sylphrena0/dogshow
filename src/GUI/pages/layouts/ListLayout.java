package GUI.pages.layouts;

import GUI.Controller;
import GUI.components.*;
import net.miginfocom.swing.MigLayout;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ListLayout extends JPanel implements ConfigParameters {

    private final Controller controller;

    public ListLayout() {
        this.controller = Controller.getInstance();
    }

    protected void addComponents(JComponent header, JComponent year, JComponent checkbox, JComponent button, String[] columnNames, Object[][] data) {
        //"this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(backgroundColor);
        this.setPreferredSize(pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(pagePadding, pagePadding,pagePadding, pagePadding));

        JPanel listPanel = new RoundedPanel();
        listPanel.setLayout(new MigLayout(
                "fill", // Layout Constraints
                "[fill][fill][fill]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill][fill]" // Row constraints
        ));
        listPanel.setBackground(pageColor);

        JTable table = new JTable(data, columnNames);

        table.setDefaultRenderer(Object.class, new TableComponentRenderer());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBackground(null);
        table.setOpaque(false);
        table.setDefaultEditor(Object.class, null);
        table.setFocusable(false);
        table.setRowHeight(Scaling.relativeHeight(6.5));
        table.setPreferredSize(new Dimension(Scaling.relativeWidth(100 - 4.5), data.length*Scaling.relativeHeight(6.5)));
        table.setFont(inputFont);

        JScrollPane tableScrollable =  new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableScrollable.setOpaque(false);
        tableScrollable.setBackground(transparent);
        tableScrollable.setBorder(BorderFactory.createLineBorder(pageColor, 2));
        tableScrollable.getViewport().setOpaque(false);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(backgroundColor);
        tableHeader.setPreferredSize(new Dimension(Scaling.relativeWidth(100 - 4.5), Scaling.relativeHeight(6.5)));
        tableHeader.setBorder(BorderFactory.createEmptyBorder());
        tableHeader.setDefaultRenderer(new TableComponentRenderer());

        listPanel.add(header, "gapbefore 2%, gaptop 4%, w 60%");
        if (year != null) {
            listPanel.add(year, "gapy %d %d".formatted(gridPadding, gridPadding/4));
            listPanel.add(checkbox, "gap %d %d %d %d, wrap".formatted(gridPadding, gridPadding, gridPadding, gridPadding/4));
        } else if (button != null) {
            listPanel.add(checkbox, "al right, w min!, gapy %d %d".formatted(gridPadding, gridPadding/4));
            listPanel.add(button, "gap %d %d %d %d, wrap".formatted(gridPadding, gridPadding, gridPadding, gridPadding/4));
        }

        listPanel.add(tableScrollable, "span 2, h 94%!, dock south");

        this.add(listPanel, BorderLayout.CENTER);
    }

}
