package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import Model.GroupsRequests;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

public class requestsGUI extends JFrame {

	private JFrame frame;
	private JPanel panel = null;
	private JButton btnCancel;
	private ArrayList<GroupsRequests> allrequests;
	private JList list;
	private String[] values = null;
	private JButton btnAprrove;

	public requestsGUI(ArrayList<GroupsRequests> allrequests) {
		this.allrequests=allrequests;
		initialize();
		this.setVisible(true);
	}

	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());
		
		JLabel lblNewLabel = new JLabel("Requests List");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(60, 23, 137, 26);
		panel.add(lblNewLabel);
		
		btnAprrove = new JButton("aprrove");
		btnAprrove.setBounds(46, 325, 89, 23);
		panel.add(btnAprrove);
		
		JButton btnDeny = new JButton("deny");
		btnDeny.setBounds(210, 325, 89, 23);
		panel.add(btnDeny);
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			
			btnCancel = new JButton("cancel");
			btnCancel.setBounds(358, 324, 97, 25);
			panel.add(btnCancel);
			panel.add(getList1());
		panel.add(list);
			
		}
		return panel;
	}
	public JList getList1()
	{
		String str;
		values=new String[allrequests.size()];
		for(int i=0;i<allrequests.size();i++)
		{
			str=(""+allrequests.get(i).getUserName()+" is asking to "+allrequests.get(i).getRequestType()+" "+allrequests.get(i).getGroupName()+" group");
	     	values[i]=str;
		}
		list = new JList();
		list.setFont(new Font("Arial Black", Font.PLAIN, 12));
		list.setForeground(new Color(0, 0, 0));
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBackground(new Color(0, 191, 255));
		list.setModel(new AbstractListModel() {
			public int getSize() 
			{return values.length; }
			public Object getElementAt(int index) 
			{return values[index]; }});
		list.setBounds(49, 60, 328, 231);
		
		return list;
	}
	public void addcancelRequest(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addapproveRequest(ActionListener l) {
		btnAprrove.addActionListener(l);
	}
	public void addListActionListener(ListSelectionListener e)
	{
		list.addListSelectionListener(e);
	}
 	/**showsuceedmessege() shows a message that the group was added sucssesfuly to the DB*/
 	public void showsuceedmessege(String st)
 	{
 		
 		Component frame=null;
 	JOptionPane.showMessageDialog(frame, st);
 	this.close();
 	}
	
	public void close() {
		this.setVisible(false);
		dispose();
	}

	public JList getList() {
		return list;
	}
	
	
}
