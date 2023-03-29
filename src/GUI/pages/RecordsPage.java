package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class RecordsPage extends TableLayout {

    public RecordsPage() {
        Controller controller = Controller.getInstance();

        JLabel registrationHeader = new JLabel("Balto:");
        registrationHeader.setFont(new Font("Caveat", Font.BOLD, Scaling.relativeHeight(4.2)));
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);

        JPanel ageAndColor = new JPanel(new GridLayout(1, 2, gridPaddingRegistration, gridPaddingRegistration));
        ageAndColor.setOpaque(false);
        ageAndColor.add(age);
        ageAndColor.add(color);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        RoundedCheckbox socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        RoundedCheckbox grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        RoundedCheckbox fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        RoundedButton winnerBanner = new RoundedButton("2022 Balto Award Winner", greenButtonColor, Color.BLACK, controller);

        IconButton imageLoaderButton = new ImageLoaderButton(controller);

        addComponents(registrationHeader,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                ageAndColor, fetch,
                winnerBanner,
                imageLoaderButton,
                null);
    }


}
