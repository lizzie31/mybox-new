package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class createNewGroupGUI extends JFrame{

	private JFrame frame;
	/**
	 * the new group name.
	 */
	private JTextField groupname;
	private JButton btnAdd;
	private JButton btnCancel;
	private JComboBox comboBox;
	private JPanel panel;

	public createNewGroupGUI() {
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
		
		JLabel lblGroupMembers = new JLabel("group members:");
		lblGroupMembers.setBounds(25, 115, 104, 16);
		panel.add(lblGroupMembers);
		
		groupname = new JTextField();
		groupname.setBounds(160, 40, 116, 22);
		panel.add(groupname);
		groupname.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(160, 112, 116, 22);
		panel.add(comboBox);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(32, 217, 97, 25);
		panel.add(btnCancel);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(281, 217, 97, 25);
		panel.add(btnAdd);
		
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
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
	public void close() {
		this.setVisible(false);
		dispose();
	}

	public JTextField getGroupname() {
		return groupname;
	}

	public void setWarningMessageVisibleTrue(String string) {
		// TODO Auto-generated method stub
		
	}




	
}
