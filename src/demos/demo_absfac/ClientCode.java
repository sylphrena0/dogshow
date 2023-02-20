package demos.demo_absfac;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientCode extends JFrame {

	private final int WIDTH = 800, HEGHT = 600;
	private WidgetFactory factory;
	
	public ClientCode(String title) {
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		addComponents();
		pack();
		setVisible(true);
	}
	
	
	private void setLookAndFeel() {
		
		String os = System.getProperty("os.name");
		//os = "Windows NT";
		//os = "Linux";
		//os = "Mac OS X";
		os = "Unsupported";
		switch (os) {
		case "Windows NT":
		case "Windows 98":
		case "Windows XP":
		case "Windows 2000":
		case "Windows ME":
		case "Windows 2003":
		case "Windows 8.1":
		case "Windows 10":
			factory = new WindowsWidgetFactory();
			break;
		case "Linux":
			factory = new LinuxWidgetFactory();
			break;
		case "Mac OS X":
			factory = new MacWidgetFactory();
			break;
		default:
				factory = new DefaultWidgetFactory();
		}		
	}


	private void addComponents() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JButton b1 = factory.createButton("One");
		JButton b2 = factory.createButton("Two");
		JButton b3 = factory.createButton("Three");
		add(b1);
		add(b2);
		add(b3);
	}

	public WidgetFactory getWidgetFactory() {
		return factory;
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(()->new ClientCode("Demo"));

	}

}
