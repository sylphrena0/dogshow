package GUI.components;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    private String[] columnNames = {"REG ID", "Name", "Obedience", "Socialization", "Grooming", "Fetch", "Eligible"};
    private Object[][] data = {
            {"1234", "Balto", 7, 10, 9, 7, false}
    };

    public Table(){
        super(new GridLayout(1,0));

        ContestTableModel  model = new ContestTableModel(columnNames, data);
        JTable table = new ContestTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Table newContentPane = new Table();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }
}
