package view;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;

import Model.User;
import Model.file;
import Model.interestGroups;
import controllers.logInCon;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;

public class chooseAdvancedRegularGUI extends JFrame{

	
	/**
	 * @param groupname is the new group name.
	 */

	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel panel;
	private JLabel lblwarningMessage = null;
	private JLabel lblChooseGroupsTo;
	private JLabel lblChooseGroupsTo_1;
	private User user;
	/**@param l is the log in controller*/
	private logInCon l=null;
	private int flag;

	/**
	 *the users that the admin choose to be in the group.
	 */
	private ArrayList<String> usersgroup=new ArrayList<>();
	
	/**
	 *every check box will contain user in the system and the administrator will choose.
	 */
	private ArrayList<JCheckBox> groupsRead=new ArrayList<>();
	private ArrayList<JCheckBox> groupsUpdate=new ArrayList<>();
	

	/**
	 * @param users the array of all users in DB.
	 */
	private ArrayList<interestGroups> groupsR;
	private ArrayList<interestGroups> groupsU;


	public chooseAdvancedRegularGUI(User userDetails) {
		this.groupsR=userDetails.getInterestGroupInDB();
		this.groupsU=userDetails.getInterestGroupInDB();
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
		
		lblChooseGroupsTo = new JLabel("Choose groups to read");
		lblChooseGroupsTo.setBounds(36, 46, 132, 14);
		panel.add(lblChooseGroupsTo);
		
		
		lblChooseGroupsTo_1 = new JLabel("Choose groups to update ");
		lblChooseGroupsTo_1.setBounds(272, 46, 154, 14);
		panel.add(lblChooseGroupsTo_1);
		if(groupsR.size() == 0)
		{
			lblChooseGroupsTo.setVisible(false);
			lblChooseGroupsTo_1.setVisible(false);
			btnAdd.setEnabled(false);
			setWarningMessageVisibleTrue("You are NOT in any group, please contact with administrator");
		}
		
		else
		{
			lblChooseGroupsTo.setVisible(true);
			lblChooseGroupsTo_1.setVisible(true);
			btnAdd.setEnabled(true);
			undisplayWarningMessage();
		}
		
	}
		
	private JPanel getCreatePanel(){
		
		int j=130;
		int k=80;
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
	
				for(int i=0;i<groupsR.size();i++)
				{
					groupsRead.add(new JCheckBox(""+groupsR.get(i).getGroupName()));
					if((i+1)%5==0)
					{
						j=10;
						groupsRead.get(i).setBounds(k+=100,j, 97, 23);
					}
	         
					else  
						groupsRead.get(i).setBounds(k,j, 97, 23);
	 
					panel.add(groupsRead.get(i));
					j+=30;
				}
			
				j=130;
				k=280;
				for(int i=0;i<groupsU.size();i++)
				{
					groupsUpdate.add(new JCheckBox(""+groupsU.get(i).getGroupName()));
					if((i+1)%5==0)
					{
						j=10;
						groupsUpdate.get(i).setBounds(k+=100,j, 97, 23);
					}
	         
					else  
						groupsUpdate.get(i).setBounds(k,j, 97, 23);
	 
					panel.add(groupsUpdate.get(i));
					j+=30;
				}
			
		}
		return panel;	
	}
	

	public void addchecklist(ActionListener l) {
		for (int i=0;i<groupsRead.size();i++)
		groupsRead.get(i).addActionListener(l);
	}
	
	public void addchecklistUpdate(ActionListener l) {
		for (int i=0;i<groupsUpdate.size();i++)
		groupsUpdate.get(i).addActionListener(l);
	}
	
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addAdd(ActionListener l) {
		btnAdd.addActionListener(l);
	}



	   
	   
/*public void itemStateChanged(ItemEvent e) {
   
    Object source = e.getItemSelectable();

    if (source == users.get(1)) 
      System.out.println("11");
    }
   // } else if (source == glassesButton) {
        //...make a note of it...
    //} else if (source == hairButton) {
        //...make a note of it...
    //} else if (source == teethButton) {
        //...make a note of it...*/
    
		/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
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

	public ArrayList<JCheckBox> getGroupsReadList() {
		return groupsRead;
	}

	public void setGroupsReadList(ArrayList<JCheckBox> groupslist) {
		this.groupsRead = groupslist;
	}

	public ArrayList<JCheckBox> getGroupsUpdateList() {
		return groupsUpdate;
	}

	public void setGroupsUpdateList(ArrayList<JCheckBox> groupsUpdate) {
		this.groupsUpdate = groupsUpdate;
	}
}
