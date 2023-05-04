package GUI.components;

import utilities.Parameters;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableComponentRenderer implements TableCellRenderer, Parameters {

    private TableCellRenderer defaultRenderer;

    public TableComponentRenderer(TableCellRenderer defaultRenderer){
        this.defaultRenderer = defaultRenderer;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        JComponent c = (JComponent) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!(value instanceof Boolean)){((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);} //set alignment to center as JLabel for all columns except boolean
        c.setAlignmentX(Component.CENTER_ALIGNMENT); //only works for boolean
        c.setBorder(BorderFactory.createEmptyBorder());

        if (row == -1) {//make font bold if c is header
            c.setFont(c.getFont().deriveFont(Font.BOLD, Utilities.relativeHeight(2)));
        }
        if (row % 2 == 0){
            c.setBackground(pageColor);
        }
        else {
            c.setBackground(backgroundColor);
        }

        return c;
    }

}
