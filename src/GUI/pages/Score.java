package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.views.TableView;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;

public class Score extends TableView implements ConfigParameters {

    public Score() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("Score: Balto");
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
        ScoreInput obedience = new ScoreInput("Obedience Score", controller);
        ScoreInput socialization = new ScoreInput("Socialization Score", controller);
        ScoreInput grooming = new ScoreInput("Grooming Score", controller);
        ScoreInput fetch = new ScoreInput("Fetch Score", controller);

        RoundedButton back = new RoundedButton("Back", mutedPurpleColor, Color.WHITE, controller);
        RoundedButton winnerBanner = new RoundedButton("2022 Balto Award Winner", lightPurpleButtonColor, Color.BLACK, controller);

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