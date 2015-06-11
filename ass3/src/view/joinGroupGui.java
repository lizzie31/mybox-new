package view;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;

import Model.User;
import Model.interestGroups;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.ArrayList;

public class joinGroupGui extends JFrame {

	private JFrame frmEnterLeaveGroup;
	private JButton btnCancel;
	private JButton btnSendToSystem;
	private JPanel panel=null;
	private JLabel lblwarningMessage=null;
	private String[] values;
	private User user;
	private ArrayList<interestGroups> groups=null;
	private JComboBox<String> comboBox;
 
	public joinGroupGui(User u, ArrayList<interestGroups> message) {
		setBackground(new Color(0, 191, 255));
		setForeground(SystemColor.activeCaption);
		setResizable(false);
		setTitle("Join or leave the group");
		this.user=u;
		this.groups=message;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(400, 200,200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,200);
		this.setContentPane(getCreatePanel());
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(282, 36, 131, 22);
		if(groups.size() > 0)
		{
			values=new String[groups.size()];
			for(int i=0;i<groups.size();i++){
		
			values[i]=groups.get(i).getGroupName();
			}
			
			for(int i = 0; i < values.length; i++)
				comboBox.addItem(values[i]);
		}
			
		panel.add(comboBox);
		
		btnCancel = new JButton("Back to main menu");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnCancel.setBounds(33, 86, 158, 32);
		panel.add(btnCancel);
		
		btnSendToSystem = new JButton("send to system administrator");
		btnSendToSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSendToSystem.setBounds(231, 86, 171, 32);
		panel.add(btnSendToSystem);
		/*
		btnSendToSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "your request was send to the system administrator!");
			}
		});
		*/
	
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
			
			JLabel lblSelectTheGroup = new JLabel("select the group you want to join:");
			lblSelectTheGroup.setFont(new Font("Arial Black", Font.PLAIN, 13));
			lblSelectTheGroup.setBounds(10, 27, 274, 37);
			panel.add(lblSelectTheGroup);
			panel.add(getLblwarningMessage());
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addSelectGroup(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	
	public void addSendToSystem(ActionListener e)
	{
		btnSendToSystem.addActionListener(e);
	}
	
	
	public JComboBox getComboBox() {
		return this.comboBox;
		
	}
	
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			//lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
			//lblwarningMessage.setForeground(new Color(255, 0, 0));
			//lblwarningMessage.setBounds(33, 129, 226, 32);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningMessage.setBounds(33, 129, 226, 32);
		lblwarningMessage.setVisible(true);	
		
	}
	
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
	
	public void close() {
		setVisible(false);
		dispose();
	}


}
