package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class RecordsPage extends TableLayout implements ConfigParameters{

    public RecordsPage() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("View Records: Balto");
        recordsHeader.setFont(headerFont);
        recordsHeader.setForeground(Color.WHITE);
        recordsHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedDropdown year = new RoundedDropdown(new String[] {"2023", "2022 "}, null, controller);
        RoundedDropdown names = new RoundedDropdown(new String[] {"Sylphrena", "Dom"}, null, controller);

        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        RoundedCheckbox socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        RoundedCheckbox grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        RoundedCheckbox fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        RoundedButton winnerBanner = new RoundedButton("2022 Balto Award Winner", greenButtonColor, Color.BLACK, controller);

        IconButton imageLoaderButton = new ImageLoaderButton(controller);

        addComponents(recordsHeader, year, names,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                age, color, fetch,
                winnerBanner,
                imageLoaderButton,
                null);
    }


}
