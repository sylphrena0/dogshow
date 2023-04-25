package GUI;

import GUI.components.IconButton;
import GUI.components.NavButton;
import GUI.components.RoundedPasswordField;
import GUI.components.RoundedTextField;
import GUI.pages.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import db.Database;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Controller extends JFrame implements ActionListener, ConfigParameters, MouseListener {
    private NavButton homeNav, recordsNav, registrationNav, scoreNav;
    private Registration registrationPage;
    private static Controller instance;
    private Home homePage;
    private CardLayout pageLayout;
    private JPanel pagePanel;

    private Controller(String title) {
        FlatDarculaLaf.setup();
        instance = this;
        setTitle(title);
        setResizable(false);
        setUndecorated(true);
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
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
            UIManager.put("ComboBox.focusedBackground" , inputColor);
            UIManager.put("ComboBox.selectionBackground" , inputColor);
            UIManager.put("ComboBox.popupInsets" , new Insets(3,3,3,3)); //outer inset for set of items in dropdown menu
            UIManager.put("ComboBox.selectionInsets" , new Insets(3,3,3,3)); //insets for items in dropdown menu
            ////////////////////////////////////

            //set background color of disabled buttons
            UIManager.put("Button.disabledBackground", pageColor);

            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.out.println("Error setting UI look and feel!");
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
        navPanel.setPreferredSize(new Dimension(screenSize.width, Scaling.relativeHeight(6.5)));
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
        recordsNav.setActionCommand("RECORDS");
        registrationNav.setActionCommand("REGISTRATION");
        scoreNav.setActionCommand("SCORE");

        recordsNav.setEnabled(false);
        registrationNav.setEnabled(false);
        scoreNav.setEnabled(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(pageColor);
        titlePanel.add(header);

        IconButton closeButton = new IconButton("close.png", (int) (Scaling.relativeHeight(6.5)*0.75), (int) (Scaling.relativeHeight(6.5)*0.75), getInstance());
        closeButton.addActionListener(actionEvent -> Controller.super.dispose());

        GroupLayout navLayout = new GroupLayout(navPanel);
        navLayout.setAutoCreateGaps(false);
        navLayout.setAutoCreateContainerGaps(false);
        navLayout.setHorizontalGroup(
                navLayout.createSequentialGroup()
                        .addComponent(titlePanel)
                        .addComponent(homeNav)
                        .addComponent(recordsNav)
                        .addComponent(registrationNav)
                        .addComponent(scoreNav)
                        .addComponent(closeButton)
        );
        navLayout.setVerticalGroup(
                navLayout.createSequentialGroup()
                        .addGroup(navLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titlePanel)
                                .addComponent(homeNav)
                                .addComponent(recordsNav)
                                .addComponent(registrationNav)
                                .addComponent(scoreNav)
                                .addComponent(closeButton, Scaling.relativeHeight(6.5), Scaling.relativeHeight(6.5), Scaling.relativeHeight(6.5))
                        )
        );

        navPanel.setLayout(navLayout);
        containerPanel.add(navPanel, BorderLayout.NORTH);

        pageLayout = new CardLayout();
        pagePanel = new JPanel(pageLayout);

        homePage = new Home();

        try {
            FileInputStream fis = new FileInputStream("src/db/salt.txt");
            fis.read();
            fis.close();
        } catch (FileNotFoundException e) {
            homePage.switchAuthPanel(); //database does not exist, thus user is not registered
        } catch (IOException ignored) { }

        RecordList recordList = new RecordList(new Object[][]{});
        ScoreList scoreList = new ScoreList(new Object[][]{});

        registrationPage = new Registration();


        pagePanel.add("HOME", homePage);
        pagePanel.add("RECORDS", recordList);
        pagePanel.add("REGISTRATION", registrationPage);
        pagePanel.add("SCORE", scoreList);

        containerPanel.add(pagePanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            case "HOME" -> {
                homeNav.setBackground(backgroundColor);
                recordsNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Home - Dog Show");
                pageLayout.show(pagePanel, "HOME");
            }
            case "RECORDS" -> {
                recordsNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Records - Dog Show");
                pageLayout.show(pagePanel, "RECORDS");
            }
            case "REGISTRATION" -> {
                registrationNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                recordsNav.setBackground(pageColor);
                scoreNav.setBackground(pageColor);

                setTitle("Registration - Dog Show");
                pageLayout.show(pagePanel, "REGISTRATION");
            }
            case "SCORE" -> {
                scoreNav.setBackground(backgroundColor);
                homeNav.setBackground(pageColor);
                recordsNav.setBackground(pageColor);
                registrationNav.setBackground(pageColor);

                setTitle("Score - Dog Show");
                pageLayout.show(pagePanel, "SCORE");

            }
            case "LOGIN" -> {
                //get credentials
                RoundedTextField username = homePage.getLoginUsername();
                RoundedPasswordField password = homePage.getLoginPassword();
                boolean error = false;

                //validate that user entered credentials
                if (username.getText().equals("Username")) {
                    username.setInvalid("Please specify a username");
                    error = true;
                } if (String.valueOf(password.getPassword()).equals("Password")) {
                    password.setInvalid("Please specify a password");
                    error = true;
                }

                //attempt to connect to database
                if (!error) {
                    if (!Database.login(username.getText(), password.getPassword())) { //connects to database. false if incorrect credentials
                        username.setInvalid("Invalid username or password");
                    } else {
                        System.out.println("Logged in!");
                        recordsNav.setEnabled(true);
                        registrationNav.setEnabled(true);
                        scoreNav.setEnabled(true);
                        homePage.hideAuthPanel();
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
                } else if (passwordString.matches("\\d") || passwordString.matches("[a-zA-Z]") || passwordString.matches("[!@#$%^&*_]") ) {
                    password.setInvalid("Enter one digit, one char, and one special char");
                    error = true;
                } else if (!String.valueOf(password.getPassword()).matches("^[\\w!@#$%^&*]*$")) {
                    password.setInvalid("Invalid character included");
                    error = true;
                } else if (!Arrays.equals(password.getPassword(), passwordConfirm.getPassword())) {
                    password.setInvalid("Passwords do not match");
                    passwordConfirm.setInvalid("Passwords do not match");
                    error = true;
                } if (new String(passwordConfirm.getPassword()).equals("Confirm Password")) {
                    passwordConfirm.setInvalid("Please specify a password");
                    error = true;
                }
                if (!error) {
                    Database.register(username.getText(), password.getPassword(), name.getText(), email.getText());
                    recordsNav.setEnabled(true);
                    registrationNav.setEnabled(true);
                    scoreNav.setEnabled(true);
                    System.out.println("Registered user and created database!");
                }

                System.out.println("REGISTER");
            }
            case "SET_ICON" -> {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, & GIF Images", "png", "jpg", "gif" );
                chooser.setFileFilter(filter);

                int returnValue = chooser.showOpenDialog(this);
                String selectedFile;
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                    registrationPage.setDogImage(selectedFile);
                }
            }
            case "BALTO" -> {
                System.out.println("Filter clicked!");
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        if (column == 7) {
            System.out.println(target.getValueAt(row, 0));
//            Database.
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    public static Dimension screenSize() {
        Controller controller = Controller.getInstance();
        Insets screenInsets = controller.getToolkit().getScreenInsets(controller.getGraphicsConfiguration());
        Rectangle screenSize = controller.getGraphicsConfiguration().getBounds();
        return new Dimension(screenSize.width - screenInsets.right - screenInsets.left,
                screenSize.height - screenInsets.bottom - screenInsets.top);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> Controller.getInstance("Dog Show"));
    }
}
