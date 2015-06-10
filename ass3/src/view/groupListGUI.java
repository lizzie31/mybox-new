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

public class groupListGUI extends JFrame {

	private JFrame frame;
	private JPanel panel=null;
	private JButton btnCancel=null;
	private String[] values = null;
	private User user;
	private JList list_1;
	

	public groupListGUI(User u) {
		this.user=u;
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
		
		JLabel lblYourInterestGroup = new JLabel("Your interest groups:");
		lblYourInterestGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInterestGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourInterestGroup.setBounds(81, 28, 186, 23);
		panel.add(lblYourInterestGroup);
		values=new String[user.getInterestGroupInDB().size()];
		for(int i=0;i<user.getInterestGroupInDB().size();i++){
	
		values[i]=user.getInterestGroupInDB().get(i).getGroupNumber();
		}
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
			
			btnCancel = new JButton("Back to main menu");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnCancel.setBounds(51, 368, 186, 37);
			panel.add(btnCancel);
			
			list_1 = new JList();
			list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			list_1.setBackground(new Color(135, 206, 235));
			list_1.setModel(new AbstractListModel() {
				
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list_1.setBounds(51, 81, 186, 234);
			panel.add(list_1);
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
