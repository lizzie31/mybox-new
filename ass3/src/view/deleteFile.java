package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Model.User;
import Model.file;
import Model.interestGroups;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class deleteFile extends JFrame{
	/**allFiles is array list of all the files in the data base*/
	private ArrayList<file> allFiles;
	private JPanel panel=null;
	/**Comment is a comment to help the user understand how delete file works*/
	private JLabel Comment;
	private JLabel lblDeletePermenantly;
	/**comboBox is a comboBox of all the file names*/
	/**@param comboBox is a comboBox of all the file names*/
	private JComboBox<String> comboBox;
	/**@param lblwarningMessage is a lable of a warning message*/
	private JLabel lblwarningMessage=null;
	/**user information*/
	private User userDetails;
	/**file information*/
	private file fileDetails;
	
	/**button ok*/
	private JButton btnOk;
	private JButton btnCancel;

	/**constructor**/
	public deleteFile(User user,file file) {
		this.fileDetails=file;
		this.userDetails=user;
		
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,200);
		this.setContentPane(getCreatePanel());
	}
	private JPanel getCreatePanel(){
	if(panel==null)
	{
		panel=new JPanel();
		panel.setLayout(null);
		this.setTitle("delete file");
		if(userDetails.getUserName().equals(fileDetails.getFileOwner()))
		{
		comboBox = new JComboBox();
		comboBox.setBounds(221, 106, 132, 22);
		comboBox.addItem("");
		comboBox.addItem("no");
		comboBox.addItem("yes");
		
		lblDeletePermenantly = new JLabel("delete permenantly?");
		lblDeletePermenantly.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDeletePermenantly.setBounds(70, 108, 141, 16);
		panel.add(lblDeletePermenantly);
		
		panel.add(comboBox);
		
		Comment = new JLabel("(if you choose no the file will be deleted only from your personal space)");
		Comment.setForeground(Color.MAGENTA);
		Comment.setBounds(42, 134, 420, 16);
		panel.add(Comment);
		}
		
		btnOk = new JButton("yes");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBounds(237, 57, 97, 25);
		panel.add(btnOk);
		panel.add(getLblwarningMessage());
		
		btnCancel = new JButton("no");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(114, 57, 97, 25);
		panel.add( btnCancel);
		
		JLabel lblAreYouSure = new JLabel("are you sure you want to delete this file?");
		lblAreYouSure.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAreYouSure.setBounds(84, 11, 310, 22);
		panel.add(lblAreYouSure);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(deleteFile.class.getResource("/view/lottle2.jpg")));
		label.setBounds(0, 0, 484, 161);
		panel.add(label);
	}
	return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addOk(ActionListener l) {
		btnOk.addActionListener(l);
	}
	public void addSelectDelete(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public JComboBox getComboBox() {
		return this.comboBox;
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
		lblwarningMessage.setBounds(33, 129, 400, 32);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
}
