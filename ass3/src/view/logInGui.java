package view;


import java.awt.EventQueue;

import view.warningGui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.logInCon;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

public class logInGui extends JFrame{

	private JFrame frame;
	private JPanel FirstPanel=null;
/**@param userNameField is the text field of user name*/
	JTextField userNameField=null;
	/**@param passwordField is the text field of password*/
	private JTextField passwordField=null;
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
        lblName = new JLabel("user name");
        lblName.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblName.setBounds(90, 23, 79, 14);
		
		return lblName;
     }
     /**getlblpassword() returns the password lable*/
     public JLabel getlblpassword(){
        lblPassword = new JLabel("password");
        lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPassword.setBounds(90, 60, 70, 14);
		
		return lblPassword;
     }
     /**gettextfield() returns the user name text field*/
     public  JTextField gettextfield(){
    	 userNameField = new JTextField();
    	 userNameField.setBounds(246, 20, 86, 20);
    	 userNameField.setColumns(10);
   	   
	   return userNameField;
    } 
     /**gettextfield_1() reurns the password text field*/
     public  JTextField gettextfield_1(){  
    	 passwordField = new JTextField();
    	 passwordField.setBounds(246, 57, 86, 20);

    	 passwordField.setColumns(10);
		
		return passwordField;
     }
     /**getbtnLogin() returns log in button*/
     public JButton getbtnLogin(){
		btnLogin = new JButton("LogIn");
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
		btnfrgtPass = new JButton("forgot Password");
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
			FirstPanel.add(getlblpassword(), null);
			FirstPanel.add(gettextfield(), null);
			FirstPanel.add(gettextfield_1(), null);
			FirstPanel.add(getbtnLogin(), null);
			FirstPanel.add(getbtnfrgtPass(), null);
			FirstPanel.add(getLblwarningMessage());
		}
		return FirstPanel; 
		
	}
 	/**getLblwarningMessage() returns a label with a warning message that user name of password is in correct*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("user name or password is wrong");
			lblwarningMessage.setForeground(new Color(255, 0, 0));
			lblwarningMessage.setBounds(10, 165, 200, 30);
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
		lblwarningMessage.setBounds(10, 165, 245, 30);
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
	

