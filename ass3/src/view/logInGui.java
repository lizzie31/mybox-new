package view;


import java.awt.EventQueue;

import view.warningGui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.logInCon;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class logInGui extends JFrame{

	private JFrame frame;
	private JPanel FirstPanel=null;
/**@param userNameField is the text field of user name*/
	JTextField userNameField=null;
	/**@param passwordField is the text field of password*/
	public JButton btnLogin=null;
	/**@param btnfrgtPass is the forget password button */
	public JButton btnfrgtPass;
	private JLabel lblName=null;
	private JLabel lblPassword=null;
	private JLabel lblwarningMessage = null;
	/**@param l is the logInController*/
	private logInCon l=null;
	/**@param wor is the warningGui*/
	private warningGui wor=null;
	private JPasswordField passwordField=null;

	/**
	 * Create the application.
	 */
	public logInGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 300, 300);
		
		this.setSize(500, 300);
		this.setContentPane(getFirstPanel());
		this.setTitle("myBox/login");
		this.setVisible(true);
		}
	/**getTextUserName() returns user name text field*/	
	 public String getTextUserName() {
		return userNameField.getText();
	}
	 
	/**getTextPassword() returns the password text field*/
      public String getTextPassword() {
		String str = new String(passwordField.getText());
		return str;
	 }
    /**getlblname() returns the user name lable*/  
     public JLabel getlblname(){
        lblName = new JLabel("Username :");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblName.setBounds(90, 44, 87, 18);
		
		return lblName;
     }
  
     /**gettextfield() returns the user name text field*/
     public  JTextField gettextfield(){
    	 userNameField = new JTextField();
    	 userNameField.setBounds(217, 42, 86, 20);
    	 userNameField.setColumns(10);
   	   
	   return userNameField;
    } 
   
     /**getbtnLogin() returns log in button*/
     public JButton getbtnLogin(){
		btnLogin = new JButton("Login!");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBackground(SystemColor.menu);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(79, 131, 140, 23);
		
		return btnLogin;
     }
     /**getbtnfrgtPass() returns the forget password button*/
     public JButton getbtnfrgtPass(){
		btnfrgtPass = new JButton("Forgot Password");
		btnfrgtPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnfrgtPass.setBackground(SystemColor.menu);
		btnfrgtPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnfrgtPass.setBounds(257, 131, 140, 23);
		
		return btnfrgtPass;
     }
     
     public void addLoginActionListener(ActionListener listener){
 		btnLogin.addActionListener(listener);
 	}
     
     public void addfrgtPassActionListener(ActionListener listener){
  		btnfrgtPass.addActionListener(listener);
  	}
     
 	
 	private JPanel getFirstPanel() {
		if (FirstPanel == null) {
			FirstPanel = new JPanel();
			FirstPanel.setBackground(SystemColor.inactiveCaption);
			FirstPanel.setForeground(SystemColor.activeCaption);
			FirstPanel.setLayout(null);
			FirstPanel.add(getlblname(), null);
			FirstPanel.add(gettextfield(), null);
			FirstPanel.add(getbtnLogin(), null);
			FirstPanel.add(getbtnfrgtPass(), null);
			FirstPanel.add(getLblwarningMessage());
			
			passwordField = new JPasswordField();
			passwordField.setToolTipText("");
			passwordField.setBounds(217, 73, 86, 20);
			FirstPanel.add(passwordField);
			
			JLabel password = new JLabel("Password :");
			password.setHorizontalAlignment(SwingConstants.CENTER);
			password.setFont(new Font("Arial Black", Font.PLAIN, 12));
			password.setBounds(90, 73, 87, 27);
			FirstPanel.add(password);
			
		}
		return FirstPanel; 
		
	}
 	/**getLblwarningMessage() returns a label with a warning message that user name of password is in correct*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("The username or password is wrong!");
			lblwarningMessage.setHorizontalAlignment(SwingConstants.CENTER);
			lblwarningMessage.setForeground(new Color(255, 0, 0));
			lblwarningMessage.setBounds(10, 165,350, 30);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue() sets the warning message visible*/
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	/** setWarningMessageVisibleTrue(String st) sets a warning message of the string st*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(10, 165, 350, 30);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
 	/**ClearText() clears the text fields of user name and password*/
 	public void ClearText(){
 		userNameField.setText("");
 		passwordField.setText("");
	}
 	/**close() closes the current window*/
 	public void close(){
 		this.setVisible(false);
 		dispose();
 	}
}
	

