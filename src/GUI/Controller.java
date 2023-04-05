package GUI;

import GUI.components.IconButton;
import GUI.pages.Home;
import GUI.pages.Record;
import GUI.pages.Registration;
import GUI.pages.ContestPage;
import com.formdev.flatlaf.FlatDarculaLaf;
import net.miginfocom.layout.PlatformDefaults;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame implements ActionListener, ConfigParameters {
    private JButton home, records, registration, contest;
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

        home  = new JButton(" Home ");
        home.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        records = new JButton(" Records ");
        records.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        registration = new JButton(" Registration ");
        registration.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        contest = new JButton(" Contest ");
        contest.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        Dimension button = new Dimension(Scaling.relativeHeight(7), Scaling.relativeHeight(6.5));

        configureNavButton(home, button);
        configureNavButton(records, button);
        configureNavButton(registration, button);
        configureNavButton(contest, button);

        home.setBackground(backgroundColor);

        home.setActionCommand("HOME");
        records.setActionCommand("RECORDS");
        registration.setActionCommand("REGISTRATION");
        contest.setActionCommand("CONTEST");

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
                        .addComponent(home)
                        .addComponent(records)
                        .addComponent(registration)
                        .addComponent(contest)
                        .addComponent(closeButton)
        );
        navLayout.setVerticalGroup(
                navLayout.createSequentialGroup()
                        .addGroup(navLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titlePanel)
                                .addComponent(home)
                                .addComponent(records)
                                .addComponent(registration)
                                .addComponent(contest)
                                .addComponent(closeButton, Scaling.relativeHeight(6.5), Scaling.relativeHeight(6.5), Scaling.relativeHeight(6.5))
                        )
        );

        navPanel.setLayout(navLayout);
        containerPanel.add(navPanel, BorderLayout.NORTH);

        pageLayout = new CardLayout();
        pagePanel = new JPanel(pageLayout);

        homePage = new Home();
        Record recordsPage = new Record();
        registrationPage = new Registration();
        ContestPage contestPage = new ContestPage();

        pagePanel.add("HOME", homePage);
        pagePanel.add("RECORDS", recordsPage);
        pagePanel.add("REGISTRATION", registrationPage);
        pagePanel.add("CONTEST", contestPage);

        containerPanel.add(pagePanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand) {
            case "HOME" -> {
                home.setBackground(backgroundColor);
                records.setBackground(pageColor);
                registration.setBackground(pageColor);
                contest.setBackground(pageColor);

                setTitle("Home - Dog Show");
                pageLayout.show(pagePanel, "HOME");
            }
            case "RECORDS" -> {
                records.setBackground(backgroundColor);
                home.setBackground(pageColor);
                registration.setBackground(pageColor);
                contest.setBackground(pageColor);

                setTitle("Records - Dog Show");
                pageLayout.show(pagePanel, "RECORDS");
            }
            case "REGISTRATION" -> {
                registration.setBackground(backgroundColor);
                home.setBackground(pageColor);
                records.setBackground(pageColor);
                contest.setBackground(pageColor);

                setTitle("Registration - Dog Show");
                pageLayout.show(pagePanel, "REGISTRATION");
            }
            case "CONTEST" -> {
                contest.setBackground(backgroundColor);
                home.setBackground(pageColor);
                records.setBackground(pageColor);
                registration.setBackground(pageColor);

                setTitle("Contest - Dog Show");
                pageLayout.show(pagePanel, "CONTEST");
            }
            case "LOGIN" -> {
                System.out.println("LOGIN");
            }
            case "REGISTER" -> {
                System.out.println("REGISTER");
            }
            case "LOGIN_PAGE" -> {
                System.out.println("LOGIN_PAGE");
                homePage.switchAuthPanel();
            }
            case "REGISTER_PAGE" -> {
                System.out.println("REGISTER_PAGE");
                homePage.switchAuthPanel();
            }
            case "SET_ICON" -> {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, & GIF Images", "png", "jpg", "gif" );
                chooser.setFileFilter(filter);

                int returnValue = chooser.showOpenDialog(this);
                String selectedFile = "";
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile().getAbsolutePath();
                    registrationPage.setDogImage(selectedFile);


                }
            }
        }
    }
    public static Dimension screenSize() {
        Controller controller = Controller.getInstance();
            Insets screenInsets = controller.getToolkit().getScreenInsets(controller.getGraphicsConfiguration());
            Rectangle screenSize = controller.getGraphicsConfiguration().getBounds();
            return new Dimension(screenSize.width - screenInsets.right - screenInsets.left,
                                  screenSize.height - screenInsets.bottom - screenInsets.top);
    }


    public static void main(String[] args) {
        PlatformDefaults.setLogicalPixelBase(PlatformDefaults.BASE_REAL_PIXEL);
        SwingUtilities.invokeLater(()-> Controller.getInstance("Dog Show"));
    }

}
