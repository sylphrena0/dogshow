package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedDropdown;
import GUI.pages.views.ListView;

import javax.swing.*;
import java.awt.*;

public class RecordList extends ListView {
    RoundedDropdown year;
    public RecordList() {
        Controller controller = Controller.getInstance();

        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Balto Winner",
                ""};

        JLabel recordsListHeader = new JLabel("View Contestant Records:");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        year = new RoundedDropdown("Year", controller);

        addComponents(recordsListHeader, year, columnNames);
    }

    public void setData(Object[][] data, String[] years) {
        super.setData(data);
        year.setOptions(years);
    }
}
