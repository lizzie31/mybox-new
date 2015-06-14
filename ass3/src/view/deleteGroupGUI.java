package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import Model.interestGroups;

public class deleteGroupGUI extends JFrame{

	
	private JButton btnOk;
	/**@param comboBox is a comboBox of all the groups*/
	private JComboBox comboBox;
	private JLabel lblChooseAGroup;
	private JButton btnCancel;
	private JPanel panel;
	private ArrayList<interestGroups> allinterestgroups;
	
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
		
		JButton btnOk = new JButton("ok");
		btnOk.setBounds(269, 171, 97, 25);
		panel.add(btnOk);
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
			for (int i=1;i<allinterestgroups.size();i++)
			{
				comboBox.addItem(allinterestgroups.get(i).getGroupName());
			}
			panel.add(comboBox);
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
