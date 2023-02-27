package demos.demo_structure;

import javax.swing.SwingUtilities;
/**
 * @author lopmilc
 *
 */
public class DogShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->ControllingFrame.getInstance("Test Demo"));
	}

}
