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

import javax.swing.ImageIcon;

public class deleteGroupGUI extends JFrame{

	
	private JButton btnOk;
	/**@param comboBox is a comboBox of all the groups*/
	private JComboBox comboBox;
	private JLabel lblChooseAGroup;
	private JButton btnCancel;
	private JPanel panel;
	private JButton btndelete;
	private ArrayList<interestGroups> allinterestgroups;
	private JLabel label;
	private JLabel warningIcon=null;
	private JLabel lblwarningM;
	
	public deleteGroupGUI(ArrayList<interestGroups> allinterestgroups ) {
		this.allinterestgroups=allinterestgroups;
		getContentPane().setLayout(null);
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
		lblGroupName.setBounds(110, 310, 252, -18);
	}
	private JPanel getCreatePanel(){	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("deleteGroup");
			comboBox = new JComboBox();
			comboBox.setBounds(243, 80, 123, 22);
			
			comboBox.addItem(" ");
			for (int i=0;i<allinterestgroups.size();i++)
			{
				comboBox.addItem(allinterestgroups.get(i).getGroupName());
			}
				warningIcon= new JLabel("");
				warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
				warningIcon.setBounds(68, 299, 42, 25);
				warningIcon.setVisible(false);
				
				panel.add(getLblwarningM());
				panel.add(warningIcon);
				
				btndelete = new JButton("Delete");
				btndelete.setFont(new Font("Tahoma", Font.BOLD, 11));
				btndelete.setBounds(269, 171, 97, 25);
				panel.add(btndelete);
				
				btnCancel = new JButton("cancel");
				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnCancel.setBounds(90, 171, 97, 25);
				panel.add(btnCancel);
			
				
				lblChooseAGroup = new JLabel("choose a group");
				lblChooseAGroup.setFont(new Font("Arial Black", Font.PLAIN, 14));
				lblChooseAGroup.setBounds(83, 80, 132, 19);
				panel.add(lblChooseAGroup);
			panel.add(comboBox);
			
			label = new JLabel("");
			label.setIcon(new ImageIcon(deleteGroupGUI.class.getResource("/view/2085-springlike-green-nature-butterfly-abstract-background.jpg")));
			label.setBounds(0, 0, 484, 462);
			panel.add(label);
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
	
	public JLabel getLblwarningM() {
		
		lblwarningM = new JLabel("please");
		lblwarningM.setForeground(new Color(255, 0, 0));
		lblwarningM.setFont(new Font("Arial Black", Font.PLAIN, 13));
		
		lblwarningM.setVisible(false);
		return lblwarningM;
	}

	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningM.setText(st);
		lblwarningM.setForeground(Color.RED);
		lblwarningM.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningM.setBounds(130, 299, 296, 25);
		lblwarningM.setVisible(true);	
		warningIcon.setVisible(true);
		
	}
	/**showsuceedmessege() shows a message that the group was deleted successfully to the DB*/
	public void showsuceedmessege()
 	{
 		Component frame=null;
 	JOptionPane.showMessageDialog(this, "the group was deleted successfully:)");
 	}

	
}
