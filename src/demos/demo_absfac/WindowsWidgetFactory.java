package demos.demo_absfac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class WindowsWidgetFactory implements WidgetFactory {

	
	@Override
	public JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setMaximumSize(SIZE);
		button.setFocusPainted(false);
		button.setBackground(new Color(59, 89, 182));
		Font font = new Font("Tahoma", Font.BOLD, 14);
		button.setFont(font);
		return button;
	}
}
