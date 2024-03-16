package gui;

import com.formdev.flatlaf.FlatDarculaLaf;
import db.Database;
import gui.components.*;
import gui.pages.Record;
import gui.pages.*;
import utilities.Parameters;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends JFrame implements ActionListener, Parameters, MouseListener {
    private NavButton homeNav, recordsNav, registrationNav, scoreNav;
    private Registration registrationPage;
    RecordList recordList;
    ScoreList scoreList;
    Record record;
    Score score;
    private static Controller instance;
    private Home homePage;
    private CardLayout pageLayout;
    private JPanel pagePanel;

    private Controller(String title) {
        Logger logger = Logger.getLogger(getClass().getName());

        FlatDarculaLaf.setup();
        instance = this;
        setTitle(title);
        setResizable(false);
        setUndecorated(true);
        setSize(screenSize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Utilities.getImageIcon("icon.png").getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Database.disconnect();
            }
        });
        addComponents();

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());


            ////////////////////////////////////
            ////// Dropdown Customization //////
            ////////////////////////////////////
            UIManager.put("ComboBox.focusedBackground", inputColor);
            UIManager.put("ComboBox.selectionBackground", inputColor);
            UIManager.put("ComboBox.popupInsets", new Insets(3, 3, 3, 3)); //outer inset for set of items in dropdown menu
            UIManager.put("ComboBox.selectionInsets", new Insets(3, 3, 3, 3)); //insets for items in dropdown menu
            ////////////////////////////////////

            //set background color of disabled buttons
            UIManager.put("Button.disabledBackground", pageColor);

            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting UI look and feel!");
            System.exit(0);
        }
        setVisible(true);
    }

    public static Controller getInstance() {
        return getInstance("Home - Dog Show");
    }

    public static Controller getInstance(String title) {
        if (instance == null) {
            new Controller(title); //constructor sets instance variable
        } else if (!instance.getTitle().equals(title)) {
            instance.setTitle(title);
        }
        return instance;
    }

    private void addComponents() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBackground(backgroundColor);

        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(screenSize.width, Utilities.relativeHeight(6.5)));
        navPanel.setBackground(pageColor);
        navPanel.setForeground(Color.WHITE);

        JLabel header = new JLabel("  Williamsport Area Kennel Club");
        header.setFont(headerFont);
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.LEFT);

        homeNav = new NavButton(" Home ");
        recordsNav = new NavButton(" Records ");
        registrationNav = new NavButton(" Registration ");
        scoreNav = new NavButton(" Score ");

        homeNav.setBackground(backgroundColor);

        homeNav.setActionCommand("HOME");
        recordsNav.setActionCommand("RECORD-LIST");
        registrationNav.setActionCommand("REGISTRATION");
        scoreNav.setActionCommand("SCORE-LIST");

        recordsNav.setEnabled(false);
        registrationNav.setEnabled(false);
        scoreNav.setEnabled(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(pageColor);
        titlePanel.add(header);

        IconButton closeButton = new IconButton("close.png", (int) (Utilities.relativeHeight(6.5) * 0.75), (int) (Utilities.relativeHeight(6.5) * 0.75), getInstance());
        closeButton.addActionListener(actionEvent -> Controller.super.dispose());

        GroupLayout navLayout = new GroupLayout(navPanel);
        navLayout.setAutoCreateGaps(false);
        navLayout.setAutoCreateContainerGaps(false);
        navLayout.setHorizontalGroup(navLayout.createSequentialGroup().addComponent(titlePanel).addComponent(homeNav).addComponent(recordsNav).addComponent(registrationNav).addComponent(scoreNav).addComponent(closeButton));
        navLayout.setVerticalGroup(navLayout.createSequentialGroup().addGroup(navLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titlePanel).addComponent(homeNav).addComponent(recordsNav).addComponent(registrationNav).addComponent(scoreNav).addComponent(closeButton, Utilities.relativeHeight(6.5), Utilities.relativeHeight(6.5), Utilities.relativeHeight(6.5))));

        navPanel.setLayout(navLayout);
        containerPanel.add(navPanel, BorderLayout.NORTH);

        pageLayout = new CardLayout();
        pagePanel = new JPanel(pageLayout);

        homePage = new Home();

        if (!Database.databaseExists()) {
            homePage.switchAuthPanel(); //database does not exist, thus user is not registered
        }

        recordList = new RecordList();
        scoreList = new ScoreList();

        score = new Score();
        record = new Record();

        registrationPage = new Registration();


        pagePanel.add("HOME", homePage);
        pagePanel.add("RECORD-LIST", recordList);
        pagePanel.add("RECORD", record);
        pagePanel.add("REGISTRATION", registrationPage);
        pagePanel.add("SCORE-LIST", scoreList);
        pagePanel.add("SCORE", score);

        containerPanel.add(pagePanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { // button action listeners

        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            ////////////////////////////
            ////////// Navbar //////////
            ////////////////////////////
            case "HOME" -> {
                homeNav.setBackground(backgroundColor);
                recordsNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Home - Dog Show");
                pageLayout.show(pagePanel, "HOME");
            }
            case "RECORD-LIST" -> {
                recordsNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Records - Dog Show");
                pageLayout.show(pagePanel, "RECORD-LIST");
            }
            case "REGISTRATION" -> {
                registrationNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                recordsNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Registration - Dog Show");
                pageLayout.show(pagePanel, "REGISTRATION");
            }
            case "SCORE-LIST" -> {
                scoreNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                recordsNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);

                setTitle("Score - Dog Show");
                pageLayout.show(pagePanel, "SCORE-LIST");

            }

            ////////////////////////////
            ////// Authentication //////
            ////////////////////////////
            case "LOGIN" -> {
                //get credentials
                RoundedTextField username = homePage.getLoginUsername();
                RoundedPasswordField password = homePage.getLoginPassword();
                boolean error = false;

                //validate that user entered credentials
                if (username.getText().equals("Username")) {
                    username.setInvalid("Please specify a username");
                    error = true;
                }
                if (String.valueOf(password.getPassword()).equals("Password")) {
                    password.setInvalid("Please specify a password");
                    error = true;
                }

                //attempt to connect to database
                if (!error) {
                    if (!Database.login(username.getText(), password.getPassword())) { //connects to database. false if incorrect credentials
                        JOptionPane.showMessageDialog(this, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("Logged in!");
                        recordsNav.setEnabled(true);
                        registrationNav.setEnabled(true);
                        scoreNav.setEnabled(true);
                        homePage.hideAuthPanel();

                        recordList.setData(Database.getScores(false), Database.getYears());
                        scoreList.setData(Database.getScores(true));
                    }
                }

            }
            case "REGISTER" -> { //sample user is admin / 1@CTASF_!
                RoundedTextField name = homePage.getRegisterName();
                RoundedTextField email = homePage.getRegisterEmail();
                RoundedTextField username = homePage.getRegisterUsername();
                RoundedPasswordField password = homePage.getRegisterPassword();
                String passwordString = new String(password.getPassword());
                RoundedPasswordField passwordConfirm = homePage.getRegisterConfirmPassword();

                boolean error = false;

                //validate name
                if (name.getText().equals("Name")) {
                    name.setInvalid("Please specify a name");
                    error = true;
                } else if (!name.getText().matches("^[a-zA-Z\\s]*$")) {
                    name.setInvalid("Invalid name");
                    error = true;
                }

                //validate email
                if (email.getText().equals("Email")) {
                    email.setInvalid("Please specify an email");
                    error = true;
                } else if (!email.getText().matches("^(.+)@(.+)$")) {
                    email.setInvalid("Invalid email");
                    error = true;
                }

                //validate username
                if (username.getText().equals("Username")) {
                    username.setInvalid("Please specify a username");
                    error = true;
                } else if (!username.getText().matches("^.{4,16}$")) {
                    username.setInvalid("Must be 4-16 characters");
                    error = true;
                } else if (!username.getText().matches("^(\\w){4,16}$")) {
                    username.setInvalid("Only letters, numbers, and underscores allowed");
                    error = true;
                }

                //validate password
                if (passwordString.equals("Password")) {
                    password.setInvalid("Please specify a password");
                    error = true;
                } else if (!passwordString.matches("(.{8,40})")) {
                    password.setInvalid("Must be 8-40 characters");
                    error = true;
                } else if (passwordString.matches("\\d") || passwordString.matches("[a-zA-Z]") || passwordString.matches("[!@#$%^&*_]")) {
                    password.setInvalid("Enter one digit, one char, and one special char");
                    error = true;
                } else if (!String.valueOf(password.getPassword()).matches("^[\\w!@#$%^&*]*$")) {
                    password.setInvalid("Invalid character included");
                    error = true;
                } else if (!Arrays.equals(password.getPassword(), passwordConfirm.getPassword())) {
                    password.setInvalid("Passwords do not match");
                    passwordConfirm.setInvalid("Passwords do not match");
                    error = true;
                }
                if (new String(passwordConfirm.getPassword()).equals("Confirm Password")) {
                    passwordConfirm.setInvalid("Please specify a password");
                    error = true;
                }
                if (!error) {
                    Database.register(username.getText(), password.getPassword(), name.getText(), email.getText());
                    recordsNav.setEnabled(true);
                    registrationNav.setEnabled(true);
                    scoreNav.setEnabled(true);
                    homePage.hideAuthPanel();
                    System.out.println("Registered user and created database!");
                }

                System.out.println("REGISTER");
            }

            ////////////////////////////
            /////// Registration ///////
            ////////////////////////////
            case "REGISTER-DOG" -> {
                //get fields:
                RoundedTextField familyName = registrationPage.getFamilyName();
                RoundedTextField familyEmail = registrationPage.getFamilyEmail();
                RoundedTextField dogName = registrationPage.getDogName();
                RoundedTextField breed = registrationPage.getBreed();
                RoundedTextField age = registrationPage.getAge();
                RoundedTextField color = registrationPage.getDogColor();
                RoundedTextField markings = registrationPage.getMarkings();
                RoundedCheckbox obedience = registrationPage.getObedience();
                RoundedCheckbox socialization = registrationPage.getSocialization();
                RoundedCheckbox grooming = registrationPage.getGrooming();
                RoundedCheckbox fetch = registrationPage.getFetch();
                int year = Calendar.getInstance().get(Calendar.YEAR);
                ImageLoaderButton imageLoaderButton = registrationPage.getImageLoaderButton();

                String obedienceString = obedience.isSelected() ? " " : "-"; //" " if registered, "-" if not
                String socializationString = socialization.isSelected() ? " " : "-";
                String groomingString = grooming.isSelected() ? " " : "-";
                String fetchString = fetch.isSelected() ? " " : "-";

                //validate fields
                boolean error = false;
                if (familyName.getText().equals("Family Name")) {
                    familyName.setInvalid("Please specify a family name");
                    error = true;
                } else if (!familyName.getText().matches("^[a-zA-Z\\s]*$")) {
                    familyName.setInvalid("Invalid family name");
                    error = true;
                }

                if (familyEmail.getText().equals("Family Email")) {
                    familyEmail.setInvalid("Please specify a family email");
                    error = true;
                } else if (!familyEmail.getText().matches("^(.+)@(.+)$")) {
                    familyEmail.setInvalid("Invalid family email");
                    error = true;
                }

                if (dogName.getText().equals("Dog Name")) {
                    dogName.setInvalid("Please specify a dog name");
                    error = true;
                } else if (!dogName.getText().matches("^[a-zA-Z\\s]*$")) {
                    dogName.setInvalid("Invalid dog name");
                    error = true;
                }

                if (breed.getText().equals("Breed")) {
                    breed.setInvalid("Please specify a breed");
                    error = true;
                } else if (!breed.getText().matches("^[a-zA-Z\\s]*$")) {
                    breed.setInvalid("Invalid breed");
                    error = true;
                }

                if (age.getText().equals("Age")) {
                    age.setInvalid("Please specify an age");
                    error = true;
                } else if (!age.getText().matches("^(100)?\\d{1,2}$")) { //valid age is 0-100
                    age.setInvalid("Age must be between 0-100 years");
                    error = true;
                }

                if (color.getText().equals("Color")) {
                    color.setInvalid("Please specify a color");
                    error = true;
                } else if (!color.getText().matches("^[a-zA-Z\\s]*$")) {
                    color.setInvalid("Invalid color");
                    error = true;
                }

                if (!markings.getText().matches("^[a-zA-Z\\s]*$")) {
                    markings.setInvalid("Invalid markings");
                    error = true;
                }

                if (!(obedience.isSelected() || socialization.isSelected() || grooming.isSelected() || fetch.isSelected())) {
                    JOptionPane.showMessageDialog(this, "You must register for at least one contest.", "No Contest Selected", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                //verify that image is selected
                if (registrationPage.getImage() == null) {
                    JOptionPane.showMessageDialog(this, "You must select an image.", "No Image Selected", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                if (!error) {
                    int regID = Database.registerContestant(familyName.getText(), familyEmail.getText(), dogName.getText(), breed.getText(), Integer.parseInt(age.getText()), color.getText(), markings.getText(), obedienceString, socializationString, groomingString, fetchString, registrationPage.getImage(), year);
                    scoreList.addRow(new Object[]{regID, dogName.getText(), obedienceString, socializationString, groomingString, fetchString, obedience.isSelected() && socialization.isSelected() && grooming.isSelected() && fetch.isSelected()});
                    JOptionPane.showMessageDialog(this, "Successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    //reset fields
                    familyName.setText("Family Name");
                    familyEmail.setText("Family Email");
                    dogName.setText("Dog Name");
                    breed.setText("Breed");
                    age.setText("Age");
                    color.setText("Color");
                    markings.setText("Markings");
                    obedience.setSelected(false);
                    socialization.setSelected(false);
                    grooming.setSelected(false);
                    fetch.setSelected(false);
                    imageLoaderButton.setImage(null);
                }


            }

            ////////////////////////////
            ////////// Scores //////////
            ////////////////////////////
            case "SCORE-SAVE" -> {
                ScoreInput obedience = score.getObedience();
                ScoreInput socialization = score.getSocialization();
                ScoreInput grooming = score.getGrooming();
                ScoreInput fetch = score.getFetch();
                int regID = score.getRegID();

                boolean error = false;
                //TODO:  setting error message like in registration (currently, setting text of JLabel in ScoreInput to message causes the grid to resize)
                if (obedience.isEnabled() && !obedience.getScores().matches("^(([0-9]|10);){3}([0-9]|10)$")) {
                    JOptionPane.showMessageDialog(this, "Scores must be between 0-10", "Invalid Score(s)", JOptionPane.ERROR_MESSAGE);
                    error = true;
                } else if (socialization.isEnabled() && !socialization.getScores().matches("^(([0-9]|10);){3}([0-9]|10)$")) {
                    JOptionPane.showMessageDialog(this, "Scores must be between 0-10", "Invalid Score(s)", JOptionPane.ERROR_MESSAGE);
                    error = true;
                } else if (grooming.isEnabled() && !grooming.getScores().matches("^(([0-9]|10);){3}([0-9]|10)$")) {
                    JOptionPane.showMessageDialog(this, "Scores must be between 0-10", "Invalid Score(s)", JOptionPane.ERROR_MESSAGE);
                    error = true;
                } else if (fetch.isEnabled() && !fetch.getScores().matches("^(([0-9]|10);){3}([0-9]|10)$")) {
                    JOptionPane.showMessageDialog(this, "Scores must be between 0-10", "Invalid Score(s)", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                //set scores
                if (!error) {
                    String obedienceScore = obedience.isEnabled() ? obedience.getScores() : "-";
                    String socializationScore = socialization.isEnabled() ? socialization.getScores() : "-";
                    String groomingScore = grooming.isEnabled() ? grooming.getScores() : "-";
                    String fetchScore = fetch.isEnabled() ? fetch.getScores() : "-";
                    Database.editScore(regID, obedienceScore, socializationScore, groomingScore, fetchScore);

                    JOptionPane.showMessageDialog(this, "Saved scores!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    pageLayout.show(pagePanel, "SCORE-LIST");

                    //update score in table
                    scoreList.setData(Database.getScores(true));
                }


            }
            case "SCORE-COMMIT" -> {
                boolean error = false;
                for (Object[] row : Database.getScores(true)) {
                    String obedienceScore = (String) row[2];
                    String socializationScore = (String) row[3];
                    String groomingScore = (String) row[4];
                    String fetchScore = (String) row[5];

                    if (obedienceScore.equals(" ") || socializationScore.equals(" ") || groomingScore.equals(" ") || fetchScore.equals(" ")) { //check if any scores are unentered
                        error = true;
                        JOptionPane.showMessageDialog(this, "All scores must be entered before committing.", "Missing Scores", JOptionPane.ERROR_MESSAGE);
                        break; //don't send duplicate error messages
                    }
                }

                if (!error) {
                    Database.commitScores();
                    JOptionPane.showMessageDialog(this, "Successfully committed scores!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    recordsNav.setBackground(backgroundColor);
                    homeNav.setBackground(pageColor);
                    registrationNav.setBackground(pageColor);
                    scoreNav.setBackground(pageColor);


                    recordList.setData(Database.getScores(false), Database.getYears());
                    scoreList.setData(Database.getScores(true));

                    scoreList.repaint();
                }

            }

            ////////////////////////////
            /////// Image Button ///////
            ////////////////////////////
            case "SET-ICON" -> {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, & GIF Images", "png", "jpg", "gif");
                chooser.setFileFilter(filter);

                int returnValue = chooser.showOpenDialog(this);
                String selectedFile;
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                    registrationPage.setDogImage(selectedFile);
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { //table event handler
        // TODO: Add balto winner logic and field
        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        if (column == 7) {
            int regID = (int) target.getValueAt(row, 0);
            Object[] result = Database.getContestant(regID);

            if ((Boolean) result[14]) { //if current
                pageLayout.show(pagePanel, "SCORE");
                score.setData(result);
            } else {
                pageLayout.show(pagePanel, "RECORD");
                record.setData(result);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static Dimension screenSize() {
        Controller controller = Controller.getInstance();
        Insets screenInsets = controller.getToolkit().getScreenInsets(controller.getGraphicsConfiguration());
        Rectangle screenSize = controller.getGraphicsConfiguration().getBounds();
        return new Dimension(screenSize.width - screenInsets.right - screenInsets.left, screenSize.height - screenInsets.bottom - screenInsets.top);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Controller.getInstance("Dog Show"));
    }
}
