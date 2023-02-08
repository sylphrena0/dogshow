package demo_absfac;

import java.awt.Dimension;

import javax.swing.JButton;

public interface WidgetFactory {
	final int WIDTH = 100, HEIGHT = 100;
	final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	public JButton createButton(String text);
}
