package GUI.pages.layouts;

import GUI.Controller;
import GUI.components.*;
import net.miginfocom.swing.MigLayout;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
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

        listPanel.add(header);
        if (year != null) {
            listPanel.add(year);
            listPanel.add(checkbox, "wrap");
        } else if (button != null) {
            listPanel.add(checkbox);
            listPanel.add(button, "wrap");
        }

        listPanel.add(tableScrollable, "span 2, h 94%!, dock south");

        this.add(listPanel, BorderLayout.CENTER);
    }

}
