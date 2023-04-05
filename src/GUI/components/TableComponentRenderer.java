package GUI.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableComponentRenderer implements TableCellRenderer {

    private TableCellRenderer defaultRenderer;

    public TableComponentRenderer(TableCellRenderer defaultRenderer){
        this.defaultRenderer = defaultRenderer;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        if (value instanceof Component)
            return (Component) value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
