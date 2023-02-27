/**
 * 
 */
package demos.demo_structure;

/**
 * @author lopmilc
 *
 */
public class StartUpScreen extends BasicImagePanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public StartUpScreen(ControllingFrame controller, String imageFile) {
		super(controller);
		setImageFromPackageFile("images", imageFile);
	}
	

}
