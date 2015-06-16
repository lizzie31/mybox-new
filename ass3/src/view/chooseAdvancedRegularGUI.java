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
	private User user;
	/**@param l is the log in controller*/
	private logInCon l=null;

	/**
	 *the users that the admin choose to be in the group.
	 */
	private ArrayList<String> usersgroup=new ArrayList<>();
	
	/**
	 *every check box will contain user in the system and the administrator will choose.
	 */
	private ArrayList<JCheckBox> groupslist=new ArrayList<>();

	/**
	 * @param users the array of all users in DB.
	 */
	private ArrayList<interestGroups> groups;



	public chooseAdvancedRegularGUI(User userDetails) {
		this.groups=userDetails.getInterestGroupInDB();
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
	}
		
	private JPanel getCreatePanel(){
		
		int j=130;
		int k=80;
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
	
			for(int i=0;i<groups.size();i++)
			{
	         groupslist.add(new JCheckBox(""+groups.get(i).getGroupName()));
	         if((i+1)%5==0)
	         {
	        	 j=130;
	        	 groupslist.get(i).setBounds(k+=100,j, 97, 23);
	         }
	         
	         else  groupslist.get(i).setBounds(k,j, 97, 23);
	 
	         panel.add(groupslist.get(i));
	         j+=50;
			}
	
	}
		return panel;	
	}
	

	public void addchecklist(ActionListener l) {
		for (int i=0;i<groupslist.size();i++)
		groupslist.get(i).addActionListener(l);
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

	public ArrayList<JCheckBox> getUserslist() {
		return groupslist;
	}

	public void setUserslist(ArrayList<JCheckBox> userslist) {
		this.groupslist = userslist;
	}
 	
}