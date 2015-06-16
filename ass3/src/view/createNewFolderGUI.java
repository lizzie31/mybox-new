package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class createNewFolderGUI extends JFrame {

	
	/**@param folderNameField is the folder name text filed*/
	private JTextField folderNameField;
	private JPanel panel;
	private JButton btnCancel=null;
	private JLabel label=null;
	private JLabel lblNewLabel=null;
	private JButton btnOk=null;


	public createNewFolderGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450,200);
		this.setContentPane(getCreatePanel());
			
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(78, 87, 89, 23);
		panel.add(btnCancel);
		
			
		folderNameField = new JTextField();
		folderNameField.setBounds(181, 43, 86, 20);
		panel.add(folderNameField);
		folderNameField.setColumns(10);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(177, 87, 89, 23);
		panel.add(btnOk);
		
		lblNewLabel = new JLabel("folder name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(78, 44, 93, 14);
		panel.add(lblNewLabel);
		
		label = new JLabel();
		label.setIcon(new ImageIcon(createNewFolderGUI.class.getResource("/view/little.jpg")));
		label.setBounds(0, 0, 434, 161);
		panel.add(label);
	}
	private JPanel getCreatePanel(){
		
	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addOk(ActionListener l) {
		btnOk.addActionListener(l);
	}
	
	public String getTextField()
	{
		return folderNameField.getText();
	}
	
	
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	
	
}
