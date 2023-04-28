package GUI.components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ScoreTableModel extends DefaultTableModel {

    public ScoreTableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

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

    @Override
    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
        super.fireTableChanged(null);
    }
}
