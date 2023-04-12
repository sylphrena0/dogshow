package GUI.components;

import utilities.ConfigParameters;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableComponentRenderer implements TableCellRenderer, ConfigParameters {

    private TableCellRenderer defaultRenderer;

    public TableComponentRenderer(){
        this.defaultRenderer = new DefaultTableCellRenderer();;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        JLabel c = (JLabel) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setHorizontalAlignment(SwingConstants.CENTER);
        c.setBorder(BorderFactory.createEmptyBorder());

        if (row%2 == 0){
            c.setBackground(pageColor);
        }
        else {
            c.setBackground(backgroundColor);
        }
        return c;
    }

}
