package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class forgget_password extends JFrame{

	private JFrame frame;
	private JPanel FirstPanel=null;
	/**@param emailField is the email text field*/
	private JTextField emailField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnOk=null;
	private JButton btnCancel=null;
	private JLabel lblForggetPassword=null;
	private JLabel lblUserName=null; 
	private JLabel lblwarningMessage = null;

	
	

	/**
	 * Create the application.
	 */
	public forgget_password() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400,200, 300, 300);
		this.setSize(500, 300);
		this.setContentPane(getFirstPanel());
		this.setTitle("My Box/ forgot password");
		this.setVisible(true);
	}
	private JPanel getFirstPanel() {
		if (FirstPanel == null) {
			FirstPanel = new JPanel();
			FirstPanel.setBackground(SystemColor.inactiveCaption);
			FirstPanel.setLayout(null);
			//FirstPanel.add(gettextfield1(), null);
			FirstPanel.add(getbtnOk(), null);
			FirstPanel.add(getbtncancel(), null);
			FirstPanel.add(getlblEmailAdress(), null);
			//FirstPanel.add(getlblUserName(), null);
			FirstPanel.add(getlblForgettPassword(), null);
			FirstPanel.add(gettextfield(),null);
			FirstPanel.add(getLblwarningMessage(),null);
			
			
		}
		return FirstPanel;
		}
	private JTextField gettextField_2() {
		textField_2 = new JTextField();
		textField_2.setBounds(249, 81, 86, 20);
		textField_2.setColumns(10);
		return textField_2;
	}
/**gettextfield() returns the email text field*/
	public JTextField gettextfield()
	{
		emailField = new JTextField();
		emailField.setBounds(249, 77, 86, 20);
		emailField.setColumns(10);
	
	return emailField;
	}
	
	public JTextField gettextfield1(){
		
		textField_1 = new JTextField();
		textField_1.setBounds(249, 112, 86, 20);
		textField_1.setColumns(10);
		
		return textField_1;
		
	}
	/**getbtnOk() returns ok button*/
	public JButton getbtnOk(){	
		
	   btnOk = new JButton("ok");
	   btnOk.setBounds(106, 156, 89, 23);
	
	   return btnOk;
	}
	/**getbtncancel() returns cancel button*/
	public JButton getbtncancel()
	{	
	   btnCancel = new JButton("cancel");
	   btnCancel.setBounds(246, 156, 89, 23);
	
	   return btnCancel;
	}
	/**getlblEmailAdress() returns email address*/
	public JLabel getlblEmailAdress()
	{
	   JLabel lblEmailAdress = new JLabel("Email Adress");
	   lblEmailAdress.setFont(new Font("Arial Black", Font.PLAIN, 11));
	   lblEmailAdress.setBounds(106, 79, 89, 14);
	
	   return lblEmailAdress;
	}
	/**getlblUserName() returns user name lable*/
	public JLabel getlblUserName()
	{
	lblUserName = new JLabel("User Name");
	lblUserName.setBounds(123, 115, 72, 14);

    return lblUserName;
	}
	/**getlblForgettPassword() returns the forget password lable*/
	public JLabel getlblForgettPassword()
	{
	   lblForggetPassword = new JLabel("please enter your email adsress:");
	   lblForggetPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
	   lblForggetPassword.setBounds(113, 27, 229, 20);
	
	   return lblForggetPassword;
	}
	/**getTextUserMail() returns the email text field*/
	 public String getTextUserMail() {
			return emailField.getText();
		}
	 
	 public void addOkActionListener(ActionListener listener){
	 		btnOk.addActionListener(listener);
	 	}
	 
	 
	 public void addCancelActionListener(ActionListener listener){
	 		btnCancel.addActionListener(listener);
	 	}
	 /**getLblwarningMessage() returns the lable of warning message*/
	 public JLabel getLblwarningMessage() {
			if(lblwarningMessage == null){
				lblwarningMessage = new JLabel("");
				lblwarningMessage.setForeground(Color.RED);
				lblwarningMessage.setBounds(10, 165, 200, 30);
				lblwarningMessage.setVisible(false);
			}
			return lblwarningMessage;
		}
	 /**setWarningMessageVisibleTrue() sets the lable of warning message visible*/
		public void setWarningMessageVisibleTrue() {
			lblwarningMessage.setVisible(true);	
		}
		/**setWarningMessageVisibleTrue(String st) sets st warning message visible*/
		public void setWarningMessageVisibleTrue(String st) {
			lblwarningMessage.setText(st);
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 210, 245, 20);
			lblwarningMessage.setVisible(true);	
			
		}
		/**undisplayWarningMessage() sets the warning message not visible*/
	 	public void undisplayWarningMessage() {
			lblwarningMessage.setVisible(false);
			
		}
}
