package view;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

public class createNewFileGUI extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel createpanel;
	private JButton btnCancel =null; 
	private JTextField textField2 = null;
	private createNewFileGUI currGUI;
	
	public createNewFileGUI() {
		setTitle("Create and add a new file");
		setCurrGUI(this);
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
		textField.setBounds(216, 38, 115, 20);
		textField.setColumns(10);
		createpanel.add(textField);
		
		JLabel lblFileName = new JLabel("File name:");
		lblFileName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFileName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileName.setBounds(66, 39, 93, 17);
		createpanel.add(lblFileName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 84, 115, 46);
		textField_1.setColumns(10);
		createpanel.add(textField_1);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(66, 84, 110, 14);
		createpanel.add(lblDescription);
		
		JButton btnnext = new JButton("Choose file...");
		btnnext.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFile of = new OpenFile();
				try {
					of.PickMe(currGUI);
				}catch (Exception e){e.printStackTrace();}
			}
		});
	
	
		btnnext.setBounds(53, 202, 123, 23);
		createpanel.add(btnnext);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(369, 400, 89, 23);
		createpanel.add(btnCancel);
		
	}
	private JPanel getCreatePanel(){
		if(createpanel==null)
		{
			createpanel=new JPanel();
			createpanel.setForeground(new Color(135, 206, 235));
			createpanel.setBackground(new Color(135, 206, 235));
			createpanel.setLayout(null);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(216, 147, 93, 20);
			createpanel.add(comboBox);
			comboBox.addItem(" ");
			for (int i=1;i<4;i++)
			{
				comboBox.addItem(i);
			}
			
			JLabel lblSetPermmision = new JLabel("Set permmision:");
			lblSetPermmision.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSetPermmision.setHorizontalAlignment(SwingConstants.LEFT);
			lblSetPermmision.setBounds(66, 149, 132, 14);
			createpanel.add(lblSetPermmision);
			
			textField2 = new JTextField();
			textField2.setHorizontalAlignment(SwingConstants.LEFT);
			textField2.setBounds(216, 191, 242, 46);
			createpanel.add(textField2);
			textField2.setColumns(10);
			
			JButton btnChooseTheFolder = new JButton("Choose the folder");
			btnChooseTheFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnChooseTheFolder.setBounds(53, 279, 132, 23);
			createpanel.add(btnChooseTheFolder);
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

	public JTextField getTextField2() {
		return textField2;
	}

	public void setTextField2(String textField2) {
		this.textField2.setText(textField2);
	}

	public createNewFileGUI getCurrGUI() {
		return currGUI;
	}

	public void setCurrGUI(createNewFileGUI currGUI) {
		this.currGUI = currGUI;
	}
}
