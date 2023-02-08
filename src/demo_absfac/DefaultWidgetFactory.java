package demo_absfac;

import java.awt.Font;

import javax.swing.JButton;

public class DefaultWidgetFactory implements WidgetFactory {
	
	
	@Override
	public JButton createButton(String text) {
		JButton button = new JButton(text);
		Font font = new Font("Arial", Font.BOLD, 14);
		button.setFont(font);
		return button;
	}
}
