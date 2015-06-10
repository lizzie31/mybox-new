package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.AbstractListModel;

import controllers.administratorMenuController;
import Model.User;
import Model.file;

import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class fileSearchGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel=null;
	private JButton btnCancel=null;
	private String[] values = null;
	private ArrayList<file> file;
	private ArrayList<file> filesByName=null;
	private JList list_1;
	

	public fileSearchGui(ArrayList f) {
		setTitle("Searched files by name");
		setForeground(SystemColor.inactiveCaption);
		this.file=f;
		
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
		
		btnCancel = new JButton("Back to main menu");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(190, 398, 209, 34);
		panel.add(btnCancel);
		
		values=new String[file.size()];
		for(int i=0;i<file.size();i++){
			values[i]=file.get(i).getFileName();
		}
		
		JLabel lblYourInterestGroup = new JLabel("Files in database:");
		lblYourInterestGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourInterestGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourInterestGroup.setBounds(81, 28, 186, 23);
		panel.add(lblYourInterestGroup);
		
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
		
			list_1 = new JList();
			list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			list_1.setBounds(43, 79, 186, 273);
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
