package GUI;

import GUI.components.IconButton;
import GUI.components.RoundedPasswordField;
import GUI.components.RoundedTextField;
import GUI.pages.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.sun.nio.sctp.AbstractNotificationHandler;
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
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Controller extends JFrame implements ActionListener, ConfigParameters, MouseListener {
    private JButton homeNav, recordsNav, registrationNav, scoreNav;
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

    private void configureNavButton(JButton button, Dimension size) {
        button.setMinimumSize(size);
        button.setBackground(pageColor);
        button.setForeground(Color.WHITE);
        button.setFont(headerFont);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
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

        homeNav = new JButton(" Home ");
        homeNav.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        recordsNav = new JButton(" Records ");
        recordsNav.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        registrationNav = new JButton(" Registration ");
        registrationNav.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        scoreNav = new JButton(" Score ");
        scoreNav.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        Dimension button = new Dimension(Scaling.relativeHeight(7), Scaling.relativeHeight(6.5));

        configureNavButton(homeNav, button);
        configureNavButton(recordsNav, button);
        configureNavButton(registrationNav, button);
        configureNavButton(scoreNav, button);

        homeNav.setBackground(backgroundColor);

        homeNav.setActionCommand("HOME");
        recordsNav.setActionCommand("RECORDS");
        registrationNav.setActionCommand("REGISTRATION");
        scoreNav.setActionCommand("SCORE");

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
            return;
        } catch (FileNotFoundException e) {
            homePage.switchAuthPanel(); //database does not exist, thus user is not registered
        } catch (IOException ignored) { }


        RecordList recordList = new RecordList();
        registrationPage = new Registration();
        ScoreList scoreList = new ScoreList();

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

                //validate that user entered credentials
                if (username.getText().equals("Username")) {
                    username.setInvalid("Please specify a username");
                } if (String.valueOf(password.getPassword()).equals("Password")) {
                    password.setInvalid("Please specify a password");
                }

                //attempt to connect to database
                if (!Database.login(username.getText(), password.getPassword())) { //connects to database. false if incorrect credentials
                    username.setInvalid("Invalid username or password");
                }

                System.out.println("LOGIN");


            }
            case "REGISTER" -> {
                RoundedTextField name = homePage.getRegisterName();
                RoundedTextField email = homePage.getRegisterEmail();
                RoundedTextField username = homePage.getRegisterUsername();
                RoundedPasswordField password = homePage.getRegisterPassword();
                String passwordString = new String(password.getPassword());
                RoundedPasswordField passwordConfirm = homePage.getRegisterConfirmPassword();

                //validate name
                if (name.getText().equals("Name")) {
                    name.setInvalid("Please specify a name");
                } else if (!name.getText().matches("^[a-zA-Z\\s]*$")) {
                    name.setInvalid("Invalid name");
                }

                //validate email
                if (email.getText().equals("Email")) {
                    email.setInvalid("Please specify an email");
                } else if (!email.getText().matches("^(.+)@(.+)$")) {
                    email.setInvalid("Invalid email");
                }

                //validate username
                if (username.getText().equals("Username")) {
                    username.setInvalid("Please specify a username");
                } else if (!username.getText().matches("^(\\w){4,16}$")) {
                    username.setInvalid("Must be 4-16 characters");
                } else if (username.getText().matches("^(\\w){4,16}$")) {
                    username.setInvalid("Only letters, numbers, and underscores allowed");
                }

                //validate password
                if (passwordString.equals("Password")) {
                    password.setInvalid("Please specify a password");
                } else if (!passwordString.matches("(.{8,40})")) {
                    password.setInvalid("Must be 8-40 characters");
                } else if (passwordString.matches("\\d") || passwordString.matches("[a-zA-Z]") || passwordString.matches("[!@#$%^&*_]") ) {
                    password.setInvalid("Enter one digit, one char, and one special char");
                } else if (!String.valueOf(password.getPassword()).matches("^[\\w!@#$%^&*]*$")) {
                    password.setInvalid("Invalid character included");
                } else if (!Arrays.equals(password.getPassword(), passwordConfirm.getPassword())) {
                    password.setInvalid("Passwords do not match");
                    passwordConfirm.setInvalid("Passwords do not match");
                } if (new String(passwordConfirm.getPassword()).equals("Confirm Password")) {
                    passwordConfirm.setInvalid("Please specify a password");
                }

                Database.register(username.getText(), password.getPassword(), name.getText(), email.getText());

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
