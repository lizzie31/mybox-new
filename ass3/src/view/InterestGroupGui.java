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

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class InterestGroupGui extends AbstractGui{

	/**values is an array that saves all groups names*/
	private String[] values = null;
	/** groupInformation is the interest groups information*/
	private interestGroups groupInformation=null;
	/**list_1 is a list of all the files in interest group*/
	private JList list_1;
	/** gui variables*/
	private JButton btnOpen=null;
	private JPanel panel=null;
	private JButton btnCancel=null;
	private JButton btnSetContant=null;
	private JButton btnSetChar =null;
	private JButton btnAddToDB=null;
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;
	
	/**constructor
	 * 
	 * @param iGR
	 */
	public InterestGroupGui(interestGroups iGR) {
		this.groupInformation=iGR;
		initialize();
		this.setVisible(true);
	}


	/** Initialize the contents of the frame.*/
	private void initialize() {
		this.setBounds(400, 200, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,444);
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
			panel.add(getbtnOpen());
			panel.add(getbtnSetContant());
			panel.add(getbtnSetChar());
			panel.add(getbtnAddToDB());
			panel.add(getwarningIcon());
	        panel.add(getLblwarningMessage());
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(InterestGroupGui.class.getResource("/view/searchgui.jpg")));
			label.setBounds(0, 0, 484, 405);
			panel.add(label);
		}
		return panel;
	}
	

/********************************************get components to JPanel*********************************************************/
	public JLabel getLabelWelcom()
	{
		JLabel lblWelcomToThe = new JLabel("welcom to the group:"+groupInformation.getGroupName());
		lblWelcomToThe.setBackground(new Color(30, 144, 255));
		lblWelcomToThe.setForeground(new Color(0, 0, 0));
		lblWelcomToThe.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblWelcomToThe.setBounds(92, 0, 350, 50);
		
		return lblWelcomToThe;
	}
	
	public JLabel getlblGroupFiles() {
		JLabel lblGroupFiles = new JLabel("group files:");
		lblGroupFiles.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblGroupFiles.setBounds(71, 41, 96, 32);
		
		return lblGroupFiles;
	}
	
	public JButton getbtnOpen()
	{
		btnOpen = new JButton("open");
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOpen.setBounds(249, 84, 121, 30);
		
		return btnOpen;
	}
	
	public JButton getbtnSetContant()
	{
		btnSetContant = new JButton("set contant");
		btnSetContant.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSetContant.setBounds(249, 125, 121, 32);
		
		return btnSetContant;
	}
	
	public JButton getbtnSetChar()
	{
		btnSetChar = new JButton("set charecters");
		btnSetChar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSetChar.setBounds(249, 168, 121, 32);
		
		return btnSetChar;
	}
	
	public JButton getbtnAddToDB()
	{
		btnAddToDB = new JButton("add to my files");
		btnAddToDB.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddToDB.setBounds(249, 211, 121, 32);
		
		return btnAddToDB;
	}
	
	public JButton getbtnCancel() {
		btnCancel = new JButton("Back to main menu");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(132, 356, 177, 30);
		
		return btnCancel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public JList getList()
	{
		values=new String[groupInformation.getFilesForRead().size()];
		for(int i=0;i<groupInformation.getFilesForRead().size();i++)
		values[i]=groupInformation.getFilesForRead().get(i).getFileName();
		list_1 = new JList();
		list_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		list_1.setForeground(new Color(0, 0, 0));
		list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list_1.setBackground(new Color(176, 224, 230));
		list_1.setModel(new AbstractListModel() {
			public int getSize() 
			{return values.length; }
			public Object getElementAt(int index) 
			{return values[index]; }});
		list_1.setBounds(21, 73, 186, 242);
		
		return list_1;
	}
	
	public JLabel getwarningIcon()
	{
	    warningIcon= new JLabel("");
	    warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
	    warningIcon.setBounds(31, 320, 30, 25);
	    warningIcon.setVisible(false);
	    
	    return warningIcon;
	}
	
	/**getLblwarningMessage() returns the warning message*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningMessage.setSize(327, 19);
		lblwarningMessage.setLocation(67, 326);
		warningIcon.setVisible(true);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
		public void undisplayWarningMessage() {
			warningIcon.setVisible(false);
		    lblwarningMessage.setVisible(false);
		
	}
	/************************************************add listeners*******************************************************************/
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addListActionListener(ListSelectionListener e)
	{
		list_1.addListSelectionListener(e);
	}
	
	
	public void addOpen(ActionListener l) {
		btnOpen.addActionListener(l);
	}
	
	public void addtomefiles(ActionListener l) {
		btnAddToDB.addActionListener(l);
	}
	
	public void addupdate(ActionListener l) {
		btnSetContant.addActionListener(l);
	}
	
	/***********************************************getters and setters*****************************************************/
	public JList getList1()
	{
		return list_1;
	}
	
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
