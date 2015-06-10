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
	private JTextField textField=null;
	private JTextField textField_1=null;
	public JButton btnLogin=null;
	public JButton btnfrgtPass;
	private JLabel lblName=null;
	private JLabel lblPassword=null;
	private JLabel lblwarningMessage = null;
	private logInCon l=null;
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
		
	 public String getTextUserName() {
		return textField.getText();
	}
	 
	
      public String getTextPassword() {
		String str = new String(textField_1.getText());
		return str;
	 }
      
     public JLabel getlblname(){
        lblName = new JLabel("user name");
        lblName.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblName.setBounds(90, 23, 79, 14);
		
		return lblName;
     }
     
     public JLabel getlblpassword(){
        lblPassword = new JLabel("password");
        lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPassword.setBounds(90, 60, 70, 14);
		
		return lblPassword;
     }
     public  JTextField gettextfield(){
 	   textField = new JTextField();
	   textField.setBounds(246, 20, 86, 20);
   	   textField.setColumns(10);
   	   
	   return textField;
    } 
     public  JTextField gettextfield_1(){  
        textField_1 = new JTextField();
		textField_1.setBounds(246, 57, 86, 20);

		textField_1.setColumns(10);
		
		return textField_1;
     }
     
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
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("user name or password is wrong");
			lblwarningMessage.setForeground(new Color(255, 0, 0));
			lblwarningMessage.setBounds(10, 165, 200, 30);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(10, 165, 245, 30);
		lblwarningMessage.setVisible(true);	
		
	}
	
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
 	
 	public void ClearText(){
		textField.setText("");
		textField_1.setText("");
	}
 	public void close(){
 		this.setVisible(false);
 		dispose();
 	}
}
	

