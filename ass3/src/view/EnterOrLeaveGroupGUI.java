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

public class EnterOrLeaveGroupGUI extends JFrame {

	private JFrame frmEnterLeaveGroup;
	private JTextField textField;
	private JButton btnCancel;
	private JButton btnSendToSystem;
	private JPanel panel=null;
	private String[] values;
	private User user;
	private ArrayList<interestGroups> groups=null;
 
	public EnterOrLeaveGroupGUI(User u, ArrayList<interestGroups> message) {
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
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,361);
		this.setContentPane(getCreatePanel());
		
		
		JLabel lblUsername = new JLabel("Your username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(31, 37, 113, 16);
		panel.add(lblUsername);
		
		JLabel lblChooseEnterleave = new JLabel("Choose join or leave:");
		lblChooseEnterleave.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseEnterleave.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseEnterleave.setBounds(31, 85, 150, 16);
		panel.add(lblChooseEnterleave);
		
		
		JLabel lblChooseGroup = new JLabel("Choose group:");
		lblChooseGroup.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseGroup.setBounds(31, 130, 113, 16);
        panel.add(lblChooseGroup);
		
		textField = new JTextField();
		textField.setText(user.getUserName());
		textField.setBounds(215, 33, 131, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(215, 130, 131, 22);
		//int size = user.getAllGroupInDB().size();
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
		
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(215, 83, 131, 22);
		panel.add(comboBox_1);
		comboBox_1.addItem("Join group");
		comboBox_1.addItem("Leave group");
		
		btnCancel = new JButton("Back to main menu");

		btnCancel.setBounds(39, 219, 158, 32);
		panel.add(btnCancel);
		
		btnSendToSystem = new JButton("send to system administrator");
		btnSendToSystem.setBounds(261, 219, 171, 32);
		panel.add(btnSendToSystem);
		btnSendToSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "your request was send to the system administrator!");
			}
		});
	
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	

	
	public void close() {
		setVisible(false);
		dispose();
	}
}
