package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.layouts.TableLayout;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class Record extends TableLayout implements ConfigParameters {

    public Record() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("View Records: Balto");
        recordsHeader.setFont(headerFont);
        recordsHeader.setForeground(Color.WHITE);
        recordsHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedDropdown year = new RoundedDropdown(new String[] {"2023", "2022 "}, null, controller);
        RoundedDropdown names = new RoundedDropdown(new String[] {"Sylphrena", "Dom"}, null, controller);

        RoundedTextField familyName = new RoundedTextField("Family Name", false, controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", false, controller);
        RoundedTextField name = new RoundedTextField("Dog Name", false, controller);
        RoundedTextField breed = new RoundedTextField("Breed", false, controller);
        RoundedTextField age = new RoundedTextField("Age", false, controller);
        RoundedTextField color = new RoundedTextField("Color", false, controller);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", false, controller);
        ScoreInput obedience = new ScoreInput("Obedience Score", false, controller);
        ScoreInput socialization = new ScoreInput("Socialization Score", false, controller);
        ScoreInput grooming = new ScoreInput("Grooming Score", false, controller);
        ScoreInput fetch = new ScoreInput("Fetch Score", false, controller);

        RoundedButton back = new RoundedButton("Back", pageColor, lightPurpleButtonColor, controller);
        RoundedButton winnerBanner = new RoundedButton("2022 Balto Award Winner", lightPurpleButtonColor, Color.BLACK, controller);
        winnerBanner.setEnabled(false);

        IconButton imageLoaderButton = new ImageLoaderButton(controller);

        addComponents(recordsHeader, year, names,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                age, color, fetch,
                back, winnerBanner,
                imageLoaderButton);
    }


}
