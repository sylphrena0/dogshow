/**
 * 
 */
package demos.demo_structure;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author lopmilc
 *
 */
public class ButtonPanelForAuthentication extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllingFrame controller;
	private final int WIDTH = 350, HEIGHT = 600;
    private JPasswordField passwordField;
    private JTextField loginField;

	public ButtonPanelForAuthentication() {
		this.controller = ControllingFrame.getInstance();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		addComponents();
	}

	private void addComponents() {
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
    
        JLabel label1 = new JLabel("Enter your login: ", JLabel.CENTER);
        label1.setLabelFor(loginField);
        
        JLabel label2 = new JLabel("Enter the password: ", JLabel.CENTER);
        label2.setLabelFor(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(controller);
        
        JButton createAccount = new JButton("Create");
        createAccount.addActionListener(controller);  

        loginField = new JTextField("jdoe@somesuch",10);
        loginField.setActionCommand("LOGIN");
        loginField.addActionListener(controller);
        //loginField.setFocusTraversalKeysEnabled(false);

		passwordField = new JPasswordField(10);
        passwordField.setActionCommand("PASSWORD");
        passwordField.addActionListener(controller);
        //passwordField.setFocusTraversalKeysEnabled(false);

        //Lay out everything.
   
        layout.setHorizontalGroup(
        		   layout.createSequentialGroup()
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		    		  .addComponent(label1)
        		    		  .addComponent(label2)
        		    		  .addComponent(loginButton))
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		    		  .addComponent(loginField)
        		    		  .addComponent(passwordField)
        		    		  .addComponent(createAccount))
        		);
        layout.setVerticalGroup(
        		   layout.createSequentialGroup()
     		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
       		           .addComponent(label1)
       		           .addComponent(loginField))
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        		           .addComponent(label2)
        		           .addComponent(passwordField))
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
       		           .addComponent(loginButton)
        		      .addComponent(createAccount))
        		);
		
	}
}
