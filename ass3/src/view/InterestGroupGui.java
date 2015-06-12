package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import Model.User;
import Model.interestGroups;

public class InterestGroupGui extends AbstractGui{
	private JPanel panel=null;
	private JButton btnCancel=null;
	/**@param values is an array that saves all groups names*/
	private String[] values = null;
	/**@param groupInformation is the interest groups information*/
	private interestGroups groupInformation=null;
	/**@param list_1 is a list of all the interest groups*/
	private JList list_1;
	
	public InterestGroupGui(interestGroups iGR) {
		this.groupInformation=iGR;
		initialize();
		this.setVisible(true);
	}


	/** Initialize the contents of the frame.*/
	private void initialize() {
		this.setBounds(400, 200, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());;
	}
	
	
	private JPanel getCreatePanel(){	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);;
			panel.add(getbtnCancel());
			panel.add(getList());
			panel.add(getLabelWelcom());
			panel.add(getlblGroupFiles());
		}
		return panel;
	}
	
	
	private JLabel getlblGroupFiles() {
		JLabel lblGroupFiles = new JLabel("group files:");
		lblGroupFiles.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblGroupFiles.setBounds(94, 76, 96, 32);
		
		return lblGroupFiles;
	}
/********************************************get components to JPanel*********************************************************/
	public JLabel getLabelWelcom()
	{
		JLabel lblWelcomToThe = new JLabel("welcom to the group:"+groupInformation.getGroupName());
		lblWelcomToThe.setBackground(new Color(30, 144, 255));
		lblWelcomToThe.setForeground(new Color(0, 0, 255));
		lblWelcomToThe.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblWelcomToThe.setBounds(116, 15, 242, 50);
		
		return lblWelcomToThe;
	}
	public JButton getbtnCancel() {
		btnCancel = new JButton("Back to main menu");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(53, 394, 183, 32);
		
		return btnCancel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public JList getList()
	{
		values=new String[groupInformation.getFilesForRead().size()];
		values=new String[groupInformation.getFilesForRead().size()];
		for(int i=0;i<groupInformation.getFilesForRead().size();i++)
		values[i]=groupInformation.getFilesForRead().get(i).getFileName();
		list_1 = new JList();
		list_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		list_1.setForeground(new Color(0, 0, 0));
		list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list_1.setBackground(new Color(0, 191, 255));
		list_1.setModel(new AbstractListModel() {
			public int getSize() 
			{return values.length; }
			public Object getElementAt(int index) 
			{return values[index]; }});
		list_1.setBounds(50, 119, 186, 242);
		
		return list_1;
	}
	/************************************************add listeners*******************************************************************/
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addListActionListener(ListSelectionListener e)
	{
		list_1.addListSelectionListener(e);
	}
	
	//
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
