package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Model.interestGroups;

public class deleteGroupGUI extends JFrame{

	
	private JButton btnOk;
	/**@param comboBox is a comboBox of all the groups*/
	private JComboBox comboBox;
	private JLabel lblChooseAGroup;
	private JButton btnCancel;
	private JPanel panel;
	private JButton btndelete;
	private ArrayList<interestGroups> allinterestgroups;
	/**@param lblwarningMessage is a lable of a warning message*/
	private JLabel lblwarningMessage=null;
	
	public deleteGroupGUI(ArrayList<interestGroups> allinterestgroups ) {
		this.allinterestgroups=allinterestgroups;
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
		
		JLabel lblGroupName = new JLabel("group name:");
		lblGroupName.setBounds(25, 43, 104, 16);
	
		
		lblChooseAGroup = new JLabel("choose a group");
		lblChooseAGroup.setBounds(24, 44, 108, 16);
		panel.add(lblChooseAGroup);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(43, 171, 97, 25);
		panel.add(btnCancel);
		
		btndelete = new JButton("Delete");
		btndelete.setBounds(269, 171, 97, 25);
		panel.add(btndelete);
		panel.add(getLblwarningMessage() );
	}
	private JPanel getCreatePanel(){	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("deleteGroup");
			comboBox = new JComboBox();
			comboBox.setBounds(185, 41, 123, 22);
			
			comboBox.addItem(" ");
			for (int i=0;i<allinterestgroups.size();i++)
			{
				comboBox.addItem(allinterestgroups.get(i).getGroupName());
			}
			panel.add(comboBox);
		}
		return panel;
	}
	
	public void adddelete(ActionListener l) {
		btndelete.addActionListener(l);
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	/**addSelectGroup() combobox action listennet*/
	public void addSelectGroup(ActionListener e)
	{
		comboBox.addActionListener(e);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
	/**getLblwarningMessage() returns the warning message*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("123");
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningMessage.setBounds(33, 80, 226, 32);
		lblwarningMessage.setVisible(true);	
		
	}
	/**showsuceedmessege() shows a message that the group was deleted successfully to the DB*/
	public void showsuceedmessege()
 	{
 		Component frame=null;
 	JOptionPane.showMessageDialog(this, "the group was added sucssefuly to DB :)");
 	}

	
}
