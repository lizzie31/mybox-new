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

public class createNewFolderGUI extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JPanel panel;
	JButton btnCancel=null;


	public createNewFolderGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());
	
		
		textField = new JTextField();
		textField.setBounds(227, 79, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("folder name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(99, 82, 93, 14);
		panel.add(lblNewLabel);
		
		JButton btnOk = new JButton("ok");
		btnOk.setBounds(261, 151, 89, 23);
		panel.add(btnOk);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(136, 151, 89, 23);
		panel.add(btnCancel);
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
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
