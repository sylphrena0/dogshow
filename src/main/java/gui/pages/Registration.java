package gui.pages;

import gui.Controller;
import gui.components.ImageLoaderButton;
import gui.components.RoundedButton;
import gui.components.RoundedCheckbox;
import gui.components.RoundedTextField;
import gui.pages.views.TableView;
import lombok.Getter;
import lombok.SneakyThrows;
import utilities.Constants;
import utilities.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Registration extends TableView {
    @Getter
    private final ImageLoaderButton imageLoaderButton;
    @Getter RoundedTextField familyName, familyEmail, dogName, breed, age, dogColor, markings;
    @Getter RoundedCheckbox obedience, socialization, grooming, fetch;
    Image scaledImage;

    public Registration() {
        Controller controller = Controller.getInstance();

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(Constants.headerFont);
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        familyName = new RoundedTextField("Family Name", controller);
        familyEmail = new RoundedTextField("Family Email", controller);
        dogName = new RoundedTextField("Dog Name", controller);
        breed = new RoundedTextField("Breed", controller);
        age = new RoundedTextField("Age", controller);
        dogColor = new RoundedTextField("Color", controller);

        markings = new RoundedTextField("Identifiable Markings", controller);
        obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        imageLoaderButton = new ImageLoaderButton(controller);
        imageLoaderButton.setActionCommand("SET-ICON");

        RoundedButton registerButton = new RoundedButton("Register", Constants.lightPurpleButtonColor, Color.BLACK, controller);
        registerButton.setActionCommand("REGISTER-DOG");


        JPanel buttons = new JPanel(new GridLayout(1, 2, gridPaddingRegistration, gridPaddingRegistration));
        buttons.add(registerButton);

        addComponents(registrationHeader,
                familyName, markings,
                familyEmail, obedience,
                dogName, socialization,
                breed, grooming,
                age, dogColor, fetch,
                null, registerButton,
                imageLoaderButton);
    }

    public void setDogImage(String file) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(file))); // load the image to a imageIcon
            scaledImage = Utilities.letterboxImage(imageIcon, imageButton.getWidth(), imageButton.getHeight());
            imageLoaderButton.setImage(scaledImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    @SneakyThrows
    public byte[] getImage() {
        // get scaledImage and turn to byte array:
        if (scaledImage != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage image = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(scaledImage, 0, 0 , null);
            ImageIO.write(image, "png", bos);
            return bos.toByteArray();
        }
        return null;
    }
}