package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class setCharacters extends JFrame {

	
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel = null;
	private JButton btnCancel;
	private JButton btnOk;

	public setCharacters() {
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
	
		textField = new JTextField();
		textField.setBounds(161, 46, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 96, 204, 77);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(40, 217, 97, 25);
		panel.add(btnCancel);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(309, 217, 97, 25);
		panel.add(btnOk);
		
		JLabel lblName = new JLabel("name:");
		lblName.setBounds(43, 49, 56, 16);
		panel.add(lblName);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setBounds(40, 96, 86, 16);
		panel.add(lblDescription);
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("set characters");
		}
		return panel;
	}
	public void addcancelListener(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
