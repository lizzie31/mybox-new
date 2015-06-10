package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class createNewFileGUI extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel createpanel;
	private JButton btnCancel =null; 


	public createNewFileGUI() {
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
		textField.setBounds(240, 78, 115, 20);
		textField.setColumns(10);
		createpanel.add(textField);
		
		JLabel lblFileName = new JLabel("file name:");
		lblFileName.setBounds(137, 81, 76, 14);
		createpanel.add(lblFileName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(240, 124, 115, 46);
		textField_1.setColumns(10);
		createpanel.add(textField_1);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setBounds(137, 127, 93, 14);
		createpanel.add(lblDescription);
		
		JButton btnnext = new JButton("next");
	
	
		btnnext.setBounds(365, 199, 89, 23);
		createpanel.add(btnnext);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(219, 199, 89, 23);
		createpanel.add(btnCancel);
		
	}
	private JPanel getCreatePanel(){
		if(createpanel==null)
		{
			createpanel=new JPanel();
			createpanel.setLayout(null);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(65, 200, 93, 20);
			createpanel.add(comboBox);
			comboBox.addItem(" ");
			for (int i=1;i<4;i++)
			{
				comboBox.addItem(i);
			}
			
			JLabel lblSetPermmision = new JLabel("set permmision");
			lblSetPermmision.setBounds(65, 175, 93, 14);
			createpanel.add(lblSetPermmision);
		}
			return createpanel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
