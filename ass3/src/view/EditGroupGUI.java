package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import Model.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


public class EditGroupGUI  extends JFrame {

	private JFrame frame;
   private JButton btnCancle=null;
   private JPanel panel;
   private JButton btnchangP;
   private JButton btnDeleteFile;
   private JLabel lblFileInThe;
   private JComboBox comboBox ;
   private ArrayList<file> groupfils;
   private JLabel warningIcon=null;
   private JLabel lblwarningMessage=null;
   private interestGroups currgroup;
   private ArrayList<file> allfiles=null;
   private JLabel lblNewLabel_1;
   private JComboBox comboBox_1;
   private JButton btnaddfile;
   private JCheckBox chckbxForRead;
   private JCheckBox chckbxupdate;
   private int flag=0;
	/**
	 * Create the application.
	 */
	public EditGroupGUI(interestGroups s,ArrayList<file> f) {
		this.currgroup=s;
		this.allfiles=f;
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setBounds(400, 200, 300, 300);
		this.setSize(546,383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getCreatePanel());
		
		
		btnCancle = new JButton("cancle");
		btnCancle.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnCancle.setBounds(431, 299, 89, 23);
		panel.add(btnCancle);
		
		btnchangP = new JButton("change file permmsion");
		btnchangP.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnchangP.setBounds(247, 85, 167, 32);
		panel.add(btnchangP);
		
		btnDeleteFile = new JButton("delete file");
		btnDeleteFile.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnDeleteFile.setBounds(247, 42, 167, 32);
		panel.add(btnDeleteFile);
		
		lblFileInThe = new JLabel("file in the group:");
		lblFileInThe.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblFileInThe.setBounds(51, 28, 121, 23);
		panel.add(lblFileInThe);
		
		warningIcon= new JLabel("");
		warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
		warningIcon.setBounds(68, 299, 42, 25);
		warningIcon.setVisible(false);
		panel.add(getLblwarningMessage() );
		panel.add(warningIcon);
		
	
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNewLabel.setIcon(new ImageIcon(EditGroupGUI.class.getResource("/view/2085-springlike-green-nature-butterfly-abstract-background.jpg")));
		lblNewLabel.setBounds(-20, -61, 584, 473);
		panel.add(lblNewLabel);
	}
	private JPanel getCreatePanel(){	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("editGroup");
		
			comboBox = new JComboBox();
			comboBox.setBounds(51, 68, 121, 20);
			panel.add(comboBox);	
			comboBox.addItem(" ");
			
			lblNewLabel_1 = new JLabel("files not in group");
			lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(51, 176, 130, 20);
			panel.add(lblNewLabel_1);
			
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(51, 207, 121, 20);
			panel.add(comboBox_1);
			
			btnaddfile = new JButton("add file");
			btnaddfile.setFont(new Font("Arial Black", Font.PLAIN, 11));
			btnaddfile.setBounds(259, 206, 155, 23);
			panel.add(btnaddfile);
			
			chckbxForRead = new JCheckBox("for read");
			chckbxForRead.setBounds(13, 234, 97, 23);
			panel.add(chckbxForRead);
			
			chckbxupdate = new JCheckBox("for update");
			chckbxupdate.setBounds(112, 234, 97, 23);
			panel.add(chckbxupdate);
			for (int i=0;i<currgroup.getFilesForRead().size();i++)
			{
				String s=currgroup.getFilesForRead().get(i).getFileName();
				comboBox.addItem(s);
			}
			for (int i=0;i<allfiles.size();i++)
			{
				for (int j=0;j<currgroup.getFilesForRead().size();j++)
					if(((allfiles.get(i).getFileName()).equals(currgroup.getFilesForRead().get(j).getFileName()))) flag=1;
				
				if(flag!=1)comboBox_1.addItem(allfiles.get(i).getFileName());
			}

		}
		return panel;
	}
	
	public String getPermmision(String s)
	{
		for (int i=0;i<currgroup.getFilesForUpdate().size();i++)
		{
			if(currgroup.getFilesForUpdate().get(i).getFileName().equals(s)) return " -Update";
		}
		return " -Read";
	}
	
	public void addcancel(ActionListener l) {
		btnCancle.addActionListener(l);
	}
	public void adddeletefile(ActionListener l) {
		btnDeleteFile.addActionListener(l);
	}
	public void addAddfile(ActionListener l) {
		btnaddfile.addActionListener(l);
	}
	public void addchangeP(ActionListener l) {
		btnchangP.addActionListener(l);
	}
	/**addSelectfile() combobox action listennet*/
	public void addSelecfile(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	/**addSelectfile() combobox action listennet*/
	public void addSelecfile2(ActionListener e)
	{
		comboBox_1.addActionListener(e);
	}
	public void addchecklist(ActionListener l) {
		
		chckbxForRead.addActionListener(l);
		chckbxupdate.addActionListener(l);
	}
	/*public void addchecklistRead(ActionListener l) {
		
		chckbxForRead.addActionListener(l);
	}
	public void addchecklistupdate(ActionListener l) {
		
		chckbxupdate.addActionListener(l);
	}*/
	
	
	
	
	
	/**getLblwarningMessage() returns the warning message*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("123");
			lblwarningMessage.setSize(252, -24);
			lblwarningMessage.setLocation(110, 320);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningMessage.setBounds(110, 310, 252, -18);
		lblwarningMessage.setVisible(true);	
		warningIcon.setVisible(true);
		
	}
	public void showsuceedmessege(String str)
 	{
 		Component frame=null;
 	JOptionPane.showMessageDialog(this, str);
 	}


	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JComboBox getComboBox_1() {
		return comboBox_1;
	}

	public JCheckBox getChckbxForRead() {
		return chckbxForRead;
	}

	public JCheckBox getChckbxupdate() {
		return chckbxupdate;
	}
	
	
	
}
