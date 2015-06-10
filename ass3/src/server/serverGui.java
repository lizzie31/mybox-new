package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class serverGui extends JFrame{
	//private JPanel contentPane;
	/**all the buttons, labels and text fields */
	private JButton loginBut = null;
	
	private JLabel lblUserName = null;
    private JLabel lblwarningMessage = null;
	private JLabel lblPassword = null;
	private JLabel lblPort = null;
	private JLabel lblScheam = null;
	
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private JTextField textFieldPort;
	private JTextField textFieldScheam;
	
	
	private Font myFont;
	/**
	 * constructor 
	 * this constructor add all the Label, Button,TextField to the Frame
	 */
	public serverGui() {
		myFont = new Font("Serif",Font.BOLD,17);
		getContentPane().setLayout(null);
		getContentPane().add(getLogin());
		getContentPane().add(getLblwarningMessage());
		getContentPane().add(getLblUserName());
		getContentPane().add(getLblPassword());
		getContentPane().add(getTextFieldUser());
		getContentPane().add(getTextFieldPass());
		getContentPane().add(getTextFieldport());
		getContentPane().add(getLblPort());
		getContentPane().add(getLblScheam());
		getContentPane().add(getTextFieldScheam());
		
		this.setTitle("My Box/Login");
		this.setBounds(500, 200, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	
	}
	
	/*****************************************Getters and Setters of Label, Button,TextField *****************************/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("Can't login to server");
			lblwarningMessage.setForeground(Color.RED);
			lblwarningMessage.setBounds(10, 20, 200, 30);
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
		lblwarningMessage.setBounds(15, 10, 245, 30);
		lblwarningMessage.setVisible(true);	
		
	}

	public JButton getLogin() {
		
		if(loginBut == null ) {
			loginBut = new JButton("Login");
			loginBut.setBounds(150, 200, 80, 20);
		}
		return loginBut;
	}

	
	public JLabel getLblUserName() {
		if(lblUserName == null) {
			lblUserName = new JLabel("user name:");
			lblUserName.setBounds(15, 70, 90, 20);
			lblUserName.setFont(myFont);
		}
		return lblUserName;
	}
	
	public JLabel getLblPort() {
		if(lblPort == null) {
			lblPort = new JLabel("port: ");
			lblPort.setBounds(15, 142, 90, 20);
			lblPort.setFont(myFont);
		}
		return lblPort;
	}

	public JLabel getLblPassword() {
		if(lblPassword == null){
			lblPassword = new JLabel("password :");
			lblPassword.setBounds(15, 105, 90, 20);
			lblPassword.setFont(myFont);
		}
		return lblPassword;
	}
	
	public JLabel getLblScheam() {
		if(lblScheam == null){
			lblScheam = new JLabel("Scheam:");
			lblScheam.setBounds(15, 40, 90, 20);
			lblScheam.setFont(myFont);
		}
		return lblScheam;
	}

	public JTextField getTextFieldUser() {
		if(textFieldUser == null) {
			textFieldUser = new JTextField();
			textFieldUser.setBounds(105, 73, 170, 20);
			textFieldUser.setColumns(10);
		}
		return textFieldUser;
	}

	public JTextField getTextFieldPass() {
		if(textFieldPass == null) {
			textFieldPass = new JTextField();
			textFieldPass.setBounds(105, 107, 170, 20);
			textFieldPass.setColumns(10);
		}
		return textFieldPass;
	}
	
	public JTextField getTextFieldport() {
		if(textFieldPort == null) {
			textFieldPort = new JTextField();
			textFieldPort.setBounds(105, 143, 170, 20);
			textFieldPort.setColumns(10);
		}
		return textFieldPort;
	}
	
	public JTextField getTextFieldScheam() {
		if(textFieldScheam == null) {
			textFieldScheam = new JTextField();
			textFieldScheam.setBounds(105, 43, 170, 20);
			textFieldScheam.setColumns(10);
		}
		return textFieldScheam;
	}
	
	public void setTextFieldPass(String str) {
		textFieldPass.setText(str);
	}
	
	public void setTextFieldUser(String str) {
		textFieldUser.setText(str);
	}
	
	public void setTextFieldPort(String str) {
		textFieldPort.setText(str);
	}
	
	public void setTextFieldscheam(String str) {
		textFieldScheam.setText(str);
	}
	
	
	
	public void ClearText(){
		textFieldUser.setText("");
		textFieldPass.setText("");
		textFieldPort.setText("");
		textFieldScheam.setText("");
	}

	public String getTextUserName() {
		return textFieldUser.getText();
	}
	
	public String getTextPassword() {
		
		return textFieldPass.getText();
	}
	
	public String getTextPort() {
		
		return textFieldPort.getText();
	}
	public String getTextScheam() {
		
		return textFieldScheam.getText();
	}
	/**
	 * add to login button ActionListener
	 * @param listener
	 */
	public void addLoginActionListener(ActionListener listener){
		loginBut.addActionListener(listener);
	}

	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
	
}

