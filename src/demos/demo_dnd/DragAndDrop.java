package demos.demo_dnd;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class DragAndDrop extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 800, HEIGHT = 600;


	public DragAndDrop() {
		setTitle("Drag Image");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponents();
		setVisible(true);
	}

	private void addComponents() {
		ImagePanel panel = new ImagePanel("/home/sylphrena/Pictures/SPS Internship (1).JPG");
		
		add(new JScrollPane(panel), BorderLayout.CENTER);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> new DragAndDrop());
	}
}