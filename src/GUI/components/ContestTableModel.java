package GUI.components;

import javax.swing.table.AbstractTableModel;

public class ContestTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private Object[][] data;
    public ContestTableModel(String[] columnNames, Object[][] data){
        this.columnNames = columnNames;
        this.data = data;
    }
    public int getRowCount() {
        return 0;
    }

    public int getColumnCount() {
        return 0;
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col){
        if (col < 7){
            return false;
        }else {
            return true;
        }
    }

}
