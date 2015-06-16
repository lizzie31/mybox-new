package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.AbstractListModel;

import Model.User;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;

public class groupListGUI extends JFrame {

	private JPanel panel=null;
	private JButton btnCancel=null;
	/**@param values is an array that saves all groups names*/
	private String[] values = null;
	private User user;
	/**@param list_1 is a list of all the interest groups*/
	private JList list_1;
	private JLabel label;
	

	public groupListGUI(User u) {
		this.user=u;
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(500, 200, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,442);
		this.setContentPane(getCreatePanel());
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(groupListGUI.class.getResource("/view/long.jpg")));
		label.setBounds(0, 0, 384, 403);
		panel.add(label);
		values=new String[user.getInterestGroupInDB().size()];
		for(int i=0;i<user.getInterestGroupInDB().size();i++){
	
		values[i]=user.getInterestGroupInDB().get(i).getGroupName();
		}
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
			
			JLabel lblYourInterestGroup = new JLabel("Your interest groups:");
			lblYourInterestGroup.setHorizontalAlignment(SwingConstants.CENTER);
			lblYourInterestGroup.setFont(new Font("Arial Black", Font.PLAIN, 14));
			lblYourInterestGroup.setBounds(50, 26, 186, 23);
			panel.add(lblYourInterestGroup);
			
			btnCancel = new JButton("Back to main menu");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnCancel.setBounds(50, 355, 186, 37);
			panel.add(btnCancel);
			
			list_1 = new JList();
			list_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
			list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			list_1.setBackground(new Color(176, 224, 230));
			list_1.setModel(new AbstractListModel() {
				
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list_1.setBounds(50, 71, 186, 234);
			panel.add(list_1);
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addListActionListener(ListSelectionListener e)
	{
		list_1.addListSelectionListener(e);
	}
	/**GetList() returns the list of goups*/
	public JList GetList(){
		return this.list_1;
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
