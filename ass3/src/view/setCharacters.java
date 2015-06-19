package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class setCharacters extends JFrame {

	
	private JTextField nameField;
	private JTextField contatntField;
	private JPanel panel = null;
	private JButton btnCancel;
	private JButton btnOk;
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;

	public setCharacters() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());
	
		nameField = new JTextField();
		nameField.setBounds(161, 46, 116, 22);
		panel.add(nameField);
		nameField.setColumns(10);
		
		contatntField = new JTextField();
		contatntField.setBounds(161, 96, 204, 77);
		panel.add(contatntField);
		contatntField.setColumns(10);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(40, 217, 97, 25);
		panel.add(btnCancel);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(309, 217, 97, 25);
		panel.add(btnOk);
		panel.add(getLblwarningMessage());
		panel.add(getwarningIcon()); 
		
		JLabel lblName = new JLabel("name:");
		lblName.setBounds(43, 49, 56, 16);
		panel.add(lblName);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setBounds(40, 96, 86, 16);
		panel.add(lblDescription);
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("set characters");
		}
		return panel;
	}
	public void addcancelListener(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addOkListener(ActionListener l) {
		btnOk.addActionListener(l);
	}
	public String getNameFiled()
	{
		return this.nameField.getText();
	}
	public String getContantFiled()
	{
		return this.contatntField.getText();
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public JLabel getwarningIcon()
	{
		    warningIcon= new JLabel("");
		    warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
	        warningIcon.setBounds(10,187, 30, 25);
		    warningIcon.setVisible(false);
		    
		    return warningIcon;
	}
	
	/**getLblwarningMessage() returns the warning message*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblwarningMessage.setSize(450, 25);
		lblwarningMessage.setLocation(45, 187);
		warningIcon.setVisible(true);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
		public void undisplayWarningMessage() {
			warningIcon.setVisible(false);
		lblwarningMessage.setVisible(false);
		}
		
}
