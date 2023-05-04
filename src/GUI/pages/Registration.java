package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.views.TableView;
import utilities.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Registration extends TableView {
    private final ImageLoaderButton imageLoaderButton;
    RoundedTextField familyName, familyEmail, name, breed, age, color, markings;
    RoundedCheckbox obedience, socialization, grooming, fetch;
    Image scaledImage;

    public Registration() {
        Controller controller = Controller.getInstance();

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(headerFont);
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        familyName = new RoundedTextField("Family Name", controller);
        familyEmail = new RoundedTextField("Family Email", controller);
        name = new RoundedTextField("Dog Name", controller);
        breed = new RoundedTextField("Breed", controller);
        age = new RoundedTextField("Age", controller);
        color = new RoundedTextField("Color", controller);

        markings = new RoundedTextField("Identifiable Markings", controller);
        obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        imageLoaderButton = new ImageLoaderButton(controller);
        imageLoaderButton.setActionCommand("SET-ICON");

        RoundedButton registerButton = new RoundedButton("Register", lightPurpleButtonColor, Color.BLACK, controller);
        registerButton.setActionCommand("REGISTER-DOG");


        JPanel buttons = new JPanel(new GridLayout(1, 2, gridPaddingRegistration, gridPaddingRegistration));
        buttons.add(registerButton);

        addComponents(registrationHeader,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                age, color, fetch,
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
    public RoundedTextField getFamilyName() {
        return familyName;
    }

    public RoundedTextField getFamilyEmail() {
        return familyEmail;
    }

    public RoundedTextField getDogName() {
        return name;
    }

    public RoundedTextField getBreed() {
        return breed;
    }

    public RoundedTextField getAge() {
        return age;
    }

    public RoundedTextField getDogColor() {
        return color;
    }

    public RoundedTextField getMarkings() {
        return markings;
    }

    public RoundedCheckbox getObedience() {
        return obedience;
    }

    public RoundedCheckbox getSocialization() {
        return socialization;
    }

    public RoundedCheckbox getGrooming() {
        return grooming;
    }

    public RoundedCheckbox getFetch() {
        return fetch;
    }

    public ImageLoaderButton getImageLoaderButton() { return imageLoaderButton; }

    public byte[] getImage() {
        //get scaledImage and turn to byte array:
        if (scaledImage != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                BufferedImage image = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                image.getGraphics().drawImage(scaledImage, 0, 0 , null);
                ImageIO.write(image, "png", bos);
                return bos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }
}