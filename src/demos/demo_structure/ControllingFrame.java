/**
 * 
 */
package demos.demo_structure;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

//import image_dnd.ImagePanel;
/**
 * @author lopmilc
 *
 */
public class ControllingFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 900, HEIGHT = 600;

	// Container panel has two components, 1. The info panel, and 2. the
	// corresponding button panel.
	private JPanel containerPanel;
	private JPanel informationPanel;
	private JPanel buttonPanel;

	private StartUpScreen authScreen;
	private ButtonPanelForAuthentication authButtons;

	private static ControllingFrame instance = null;

	private ControllingFrame(String title) {
		// initial setup
		instance = this;
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLocationRelativeTo(null); //sets the pop up screen to the bottom right
		// corner
		Dimension screenDimension = getToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - WIDTH / 2, screenDimension.height / 2 - HEIGHT / 2);
		addComponents();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.out.println("Erros setting UI look and feel!");
			System.exit(0);
		}
		pack();
		setVisible(true);
	}

	public static ControllingFrame getInstance() {

		return getInstance("Demo");
	}

	public static ControllingFrame getInstance(String title) {
		if (instance == null) {
			new ControllingFrame(title);
		} else {
			if (!instance.getTitle().equals(title))
				instance.setTitle(title);
		}
		return instance;
	}

	private void addComponents() {

		setLayout(new BorderLayout());

		containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());

		informationPanel = new JPanel();
		informationPanel.setLayout(new CardLayout(10, 10));

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new CardLayout(10, 10));

		// add the various information panels to be switched to the info container
		// add the corresponding buttons to the buttons panel

		authScreen = new StartUpScreen(this, "hexley_key_450.png");
		informationPanel.add(authScreen, "TITLE");
		containerPanel.add(informationPanel, BorderLayout.CENTER);

		authButtons = new ButtonPanelForAuthentication();
		buttonPanel.add(authButtons, "AUTH");
		containerPanel.add(buttonPanel, BorderLayout.LINE_END);

		add(containerPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		Object o = null;
		switch (actionCommand) {
		case "LOGIN":
			o = e.getSource();
			if (o instanceof JTextField) {
				JTextField tf = (JTextField) o;
				System.out.println("Login field:  " + tf.getText());
			}
			break;
		case "PASSWORD":
			o = e.getSource();
			if (o instanceof JPasswordField) {
				JPasswordField tf = (JPasswordField) o;
				char[] password = tf.getPassword();
				System.out.println("Password field:  " + new String(password));
			}
			break;
		}
//		
		// CardLayout cardLayout = (CardLayout)(cardPanel.getLayout());
		// cardLayout.show(cardPanel, actionCommand);
///		containerPanel.invalidate();
//		containerPanel.repaint();
	}

}
