package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Model.file;
import Model.interestGroups;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class deleteFile extends JFrame{
	/**allFiles is array list of all the files in the data base*/
	private ArrayList<file> allFiles;
	private JPanel panel=null;
	/**Comment is a comment to help the user understand how delete file works*/
	private JLabel Comment;
	private JLabel lblDeletePermenantly;
	/**comboBox is a comboBox of all the file names*/
	private JComboBox comboBox ;
	/**button ok*/
	private JButton btnOk;
	private JButton btnCancel;

	/**constructor**/
	public deleteFile(ArrayList<file>f) {
		this.allFiles=f;
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
		 btnCancel.setBounds(27, 199, 97, 25);
		panel.add( btnCancel);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(275, 199, 97, 25);
		panel.add(btnOk);
		
		comboBox = new JComboBox();
		comboBox.setBounds(184, 58, 132, 22);
		comboBox.addItem("");
		
		for(int i=0;i<allFiles.size();i++)
			comboBox.addItem(allFiles.get(i).getFileName());
		
		panel.add(comboBox);
		
		lblDeletePermenantly = new JLabel("delete permenantly:");
		lblDeletePermenantly.setBounds(12, 61, 141, 16);
		panel.add(lblDeletePermenantly);
		
		Comment = new JLabel("(if you choose no the file will be deleted only from your personal space)");
		Comment.setForeground(Color.MAGENTA);
		Comment.setBounds(12, 90, 420, 16);
		panel.add(Comment);
	}
	private JPanel getCreatePanel(){
	if(panel==null)
	{
		panel=new JPanel();
		panel.setLayout(null);
		this.setTitle("delete file");
	}
	return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addOk(ActionListener l) {
		btnOk.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
