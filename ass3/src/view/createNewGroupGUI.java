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
	private JTextField textField;
	private JButton btnOk;
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
		
		textField = new JTextField();
		textField.setBounds(160, 40, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(160, 112, 116, 22);
		panel.add(comboBox);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(32, 217, 97, 25);
		panel.add(btnCancel);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(281, 217, 97, 25);
		panel.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "the group add sucssfuly");
			}
		});
	
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
	public void addok(ActionListener l) {
		btnOk.addActionListener(l);
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
