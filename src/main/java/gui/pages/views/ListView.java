package gui.pages.views;

import gui.Controller;
import gui.components.RoundedPanel;
import gui.components.TableComponentRenderer;
import net.miginfocom.swing.MigLayout;
import utilities.Constants;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;

public class ListView extends JPanel {

    private final Controller controller;
    protected JTable table;
    private String[] columnNames;
    private DefaultTableModel tableModel;
    ImageIcon inspect = Utilities.getScaledImageIcon("inspect.png", (int) (Utilities.relativeHeight(6.5) * 0.5), (int) (Utilities.relativeHeight(6.5) * 0.5));
    public ListView() {
        this.controller = Controller.getInstance();
    }

    protected void addComponents(JComponent header, JComponent option, String[] columnNames) {
        // "this" is the JPanel we are adding to the super, as this class extends JPanel
        this.setBackground(Constants.backgroundColor);
        this.setPreferredSize(Constants.pageSize);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(Constants.PAGE_PADDING, Constants.PAGE_PADDING, Constants.PAGE_PADDING, Constants.PAGE_PADDING));

        this.columnNames = columnNames;

        JPanel listPanel = new RoundedPanel();
        listPanel.setLayout(new MigLayout(
                "fill", // Layout Constraints
                "[fill][fill]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill][fill]" // Row constraints
        ));
        listPanel.setBackground(Constants.pageColor);

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class getColumnClass(int column) {
                if (column == 7) {
                    return ImageIcon.class;
                } else if (column == 6) {
                    return Boolean.class;
                } else if (column == 0) {
                    return Integer.class;
                }
                return getValueAt(0, column).getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        table = new JTable(tableModel);
        table.addMouseListener(controller);
        table.setDefaultRenderer(String.class, new TableComponentRenderer(new TableComponentRenderer(table.getDefaultRenderer(String.class))));
        table.setDefaultRenderer(Boolean.class, new TableComponentRenderer(new TableComponentRenderer(table.getDefaultRenderer(Boolean.class))));
        table.setDefaultRenderer(Integer.class, new TableComponentRenderer(new TableComponentRenderer(table.getDefaultRenderer(String.class))));
        table.setDefaultRenderer(ImageIcon.class, new TableComponentRenderer(new TableComponentRenderer(table.getDefaultRenderer(ImageIcon.class))));
        table.setBackground(null);
        table.setOpaque(false);
        table.setFocusable(false);
        table.setRowHeight(Utilities.relativeHeight(6.5));
        table.setPreferredSize(new Dimension(Utilities.relativeWidth(100 - 4.5), Utilities.relativeHeight(6.5)));
        table.setFont(Constants.inputFont);

        JScrollPane tableScrollable = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableScrollable.setOpaque(false);
        tableScrollable.setBackground(Constants.transparent);
        tableScrollable.setBorder(BorderFactory.createEmptyBorder());
        tableScrollable.getViewport().setOpaque(false);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Constants.backgroundColor);
        tableHeader.setPreferredSize(new Dimension(Utilities.relativeWidth(100 - 4.5), Utilities.relativeHeight(6.5)));
        tableHeader.setBorder(BorderFactory.createEmptyBorder());
        tableHeader.setDefaultRenderer(new TableComponentRenderer(new TableComponentRenderer(tableHeader.getDefaultRenderer())));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);


        listPanel.add(header, "gapbefore 2%, gaptop 4%, w 70%");
        listPanel.add(option, "gap %d %d %d %d, wrap".formatted(Constants.GRID_PADDING, Constants.GRID_PADDING, Constants.GRID_PADDING, Constants.GRID_PADDING / 4));
        listPanel.add(tableScrollable, "span 2, h 91%!, dock south");

        this.add(listPanel, BorderLayout.CENTER);

    }

    public void setData(Object[][] data) {
        if (data.length == 0) {
            return;
        }
        // add a column to the end of each row with inspect
        for (int i = 0; i < data.length; i++) {
            data[i] = Arrays.copyOf(data[i], data[i].length + 2);
            data[i][data[i].length - 2] = (data[i][data[i].length - 3] != "-" && data[i][data[i].length - 4] != "-" && data[i][data[i].length - 5] != "-"  && data[i][data[i].length - 6] != "-"); //determines if balto eligible by checking that all contests are registered
            data[i][data[i].length - 1] = inspect;
        }

        for (int i = 0; i < data.length; i++) { // for each row
            for (int j = 2; j < 6; j++) { // for scores in each row
                if (((String) data[i][j]).matches("^((\\d|10);){3}(\\d|10)$")) { //i f scores are saved
                    double average = 0.0;
                    for (String n : ((String) data[i][j]).split(";")) { // get each score
                        average += Double.parseDouble(n); // add to average
                    }
                    data[i][j] = String.format("%.3g%n",average / 4.0); // set field to average, formatted to 3 sig figs
                }
            }
        }

        tableModel.setDataVector(data, columnNames);
        table.setPreferredSize(new Dimension(Utilities.relativeWidth(100 - 4.5), data.length * Utilities.relativeHeight(6.5)));
    }

    public void addRow(Object[] row) {
        row = Arrays.copyOf(row, row.length + 1);
        row[row.length - 1] = inspect;
        tableModel.addRow(row);
        table.setPreferredSize(new Dimension(Utilities.relativeWidth(100 - 4.5), table.getHeight() + Utilities.relativeHeight(6.5)));
    }

}
