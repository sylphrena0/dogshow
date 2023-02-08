package demo_absfac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class LinuxWidgetFactory implements WidgetFactory {
	
	
	@Override
	public JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setMaximumSize(SIZE);
		Border outer = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE.darker(), Color.BLACK);
		Border inner = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		button.setBorder(BorderFactory.createCompoundBorder(outer, inner));
		button.setVerticalTextPosition(AbstractButton.CENTER);
		button.setHorizontalTextPosition(AbstractButton.LEADING);
		Font font = new Font("Arial", Font.BOLD, 14);
		button.setFont(font);
		return button;
	}
}
