package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedButton;
import GUI.components.RoundedCheckbox;
import GUI.pages.views.ListView;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class RecordList extends ListView {
    public RecordList() {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("View Contestant Records:");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);


        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Balto Winner",
                ""};

        ImageIcon inspect = new ImageIcon((new ImageIcon("images/inspect.png")).getImage().getScaledInstance((int) (Scaling.relativeHeight(6.5)*0.5), (int) (Scaling.relativeHeight(6.5)*0.5),  java.awt.Image.SCALE_SMOOTH));  // use scaled icon

        Object[][] data = {
                {"5121", "Balto", 10, 8, 2, 7, true, inspect},
                {"9231", "Fideo", 6, 3, 5, 1, true, inspect},
                {"9823", "Black", 6, 1, 3, 0, false, inspect},
                {"2934", "Percy", 10, 10, 10, 10, true, inspect},
                {"2214", "Cerberus", 5, 1, 2, 3, true, inspect},
                {"1213", "Precious", 6, 1, 3, 0, true, inspect},
                {"5121", "Balto", 10, 8, 2, 7, true, inspect},
                {"9231", "Fideo", 6, 3, 5, 1, true, inspect},
                {"9823", "Black", 6, 1, 3, 0, false, inspect},
                {"2934", "Percy", 10, 10, 10, 10, true, inspect},
                {"2214", "Cerberus", 5, 1, 2, 3, true, inspect},
                {"1213", "Precious", 6, 1, 3, 0, true, inspect},
        };


        addComponents(recordsListHeader, null, baltoEligible, commit, columnNames, data);
    }
}
