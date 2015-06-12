package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Model.User;
import controllers.logInCon;

import javax.swing.JCheckBox;

public class createNewGroupGUI extends JFrame{

	
	/**
	 * @param groupname is the new group name.
	 */
	private JTextField groupname;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panel;
	private JLabel lblwarningMessage = null;
	/**@param l is the log in controller*/
	private logInCon l=null;
	/**@param wor is the warning gui window*/
	private warningGui wor=null;
	/**@param chckbxNewCheckBox is a comboBox of all userNames*/
	private JCheckBox chckbxNewCheckBox;
	/**
	 * @param users the array of all users in DB.
	 */
	private ArrayList<User> users=new ArrayList<>();

	public createNewGroupGUI(ArrayList<User> usersarr) {
		this.users=usersarr;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());
		
		JLabel lblGroupName = new JLabel("group name:");
		lblGroupName.setBounds(25, 43, 104, 16);
		panel.add(lblGroupName);
		
		groupname = new JTextField();
		groupname.setBounds(160, 40, 116, 22);
		panel.add(groupname);
		groupname.setColumns(10);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(66, 386, 97, 25);
		panel.add(btnCancel);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(302, 386, 97, 25);
		panel.add(btnAdd);
		
		lblwarningMessage = new JLabel();
		lblwarningMessage.setForeground(new Color(255, 0, 0));
		lblwarningMessage.setBounds(32, 300, 434, 50);
		panel.add(lblwarningMessage);
		lblwarningMessage.setVisible(false);
	}
		
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
		
			for(int i=1;i>users.size();i++)
			chckbxNewCheckBox = new JCheckBox(""+users.get(i).getUserName());
			chckbxNewCheckBox.setBounds(66, 172, 97, 23);
			panel.add(chckbxNewCheckBox);
			this.setTitle("create new group");
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addAdd(ActionListener l) {
		btnAdd.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}

	public JTextField getGroupname() {
		return groupname;
	}
/**setWarningMessageVisibleTrue() sets the warning message visible*/
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	/**setWarningMessageVisibleTrue(String st) sets st warning message visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets warning message not visible*/
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);

 	}
 	/**showsuceedmessege() shows a message that the group was added sucssesfuly to the DB*/
 	public void showsuceedmessege()
 	{
 		Component frame=null;
 	JOptionPane.showMessageDialog(frame, "the group was added sucssefuly to DB :)");
 	}
}
