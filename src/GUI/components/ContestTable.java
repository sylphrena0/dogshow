package GUI.components;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;


public class ContestTable extends JTable {

    private static final long serialVersionUID = 1L;
    private final int ROWHEIGHT = 100;
    private StringBuilder output;

    public ContestTable(ContestTableModel model){
        super(model);
        getSelectionModel().addListSelectionListener(new RowListener());
        setRowHeight(ROWHEIGHT);
        setPreferredScrollableViewportSize(new Dimension());
        setFillsViewportHeight(true);

        setDefaultRenderer(ImageIcon.class, new TableComponentRenderer(getDefaultRenderer(ImageIcon.class)));
        setDefaultRenderer(JButton.class, new TableComponentRenderer(getDefaultRenderer(JButton.class)));
    }
    private void outputSelection(){
        output.append(String.format("Lead: %d, %d. ",
                getSelectionModel().getLeadSelectionIndex(),
                getColumnModel().getSelectionModel().getLeadSelectionIndex()));
        output.append("Rows:");
        for (int c : getSelectedRows()){
            output.append(String.format(" %d", c));
        }

        output.append(". Columns:");
        for (int c : getSelectedColumns()){
            output.append(String.format(" %d", c));
        }
        output.append(" Name: " + getValueAt(getSelectedRow(), 0) + ".\n");
    }

    private class RowListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent event){
            if (event.getValueIsAdjusting()){
                return;
            }
            output = new StringBuilder();
            output.append("ROW SELECTION EVENT. ");
            outputSelection();
            System.out.println(output);
        }
    }

}
