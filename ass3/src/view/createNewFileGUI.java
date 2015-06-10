package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.SwingConstants;

import controllers.administratorMenuController;
import controllers.createNewFolderController;

import java.awt.Font;
import java.io.File;
import java.util.Scanner;

public class createNewFileGUI extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel createpanel;
	private JButton btnCancel =null; 
	private JTextField textField2 = null;
	private createNewFileGUI currGUI;
	
	static private final String newline = "\n";
	private JButton btnOpen;
	private JButton btnChooseTheFolder;
	private JTextArea textArea;
    JFileChooser fileChooser = new JFileChooser();
	
	public createNewFileGUI() {
		setTitle("Create and add a new file");
		setCurrGUI(this);
		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = 
		    new JScrollPane(textArea,
		                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setEditable(false);
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

	}
	
	private class buttonOpenOrSavePressed implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnOpen) {
	            int returnVal = fileChooser.showOpenDialog(null);

	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File file = fileChooser.getSelectedFile();
	                //This is where a real application would open the file.
	                
	                
	    			
	    			currGUI.setTextField2(file.getPath());
	    			textArea.append("Opening: " + file.getName() + "." + newline);
	                //currGUI.textArea.setText("Opening: " + file.getName() + "." + newline);
	            } 
	            else 
	            {
	            	textArea.append("Open command cancelled by user." + newline);
	            }
	            textArea.setCaretPosition(textArea.getDocument().getLength());

	        //Handle save button action.
	        } else if (e.getSource() == btnChooseTheFolder) {
	            int returnVal = fileChooser.showSaveDialog(null);
	            
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fileChooser.getSelectedFile();
	                //This is where a real application would save the file.
	                textArea.append("Saving: " + file.getName() + "." + newline);
	            } else {
	            	textArea.append("Save command cancelled by user." + newline);
	            }
	            textArea.setCaretPosition(textArea.getDocument().getLength());
	        }
			
			/*OpenFile of = new OpenFile();
			try {
				of.PickMe(currGUI);
			}catch (Exception e){e.printStackTrace();}*/
			
		}
		
	}
		
		
	private JPanel getCreatePanel(){
		if(createpanel==null)
		{
			createpanel=new JPanel();
			createpanel.setForeground(new Color(135, 206, 235));
			createpanel.setBackground(new Color(135, 206, 235));
			createpanel.setLayout(null);
			createpanel.add(textArea);
			//JScrollPane logScrollPane = new JScrollPane(log);
			//createpanel.add(logScrollPane, BorderLayout.CENTER);
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
			
			btnOpen = new JButton("Choose file...");
			btnOpen.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnOpen.setBounds(53, 202, 123, 23);
			btnOpen.addActionListener(new buttonOpenOrSavePressed());
			createpanel.add(btnOpen);
		    
			btnChooseTheFolder = new JButton("Choose the folder");
			btnChooseTheFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnChooseTheFolder.setBounds(53, 290, 132, 23);
			btnChooseTheFolder.addActionListener(new buttonOpenOrSavePressed());
			createpanel.add(btnChooseTheFolder);
			
			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(264, 376, 132, 32);
			createpanel.add(btnCancel);
			
			textArea = new JTextArea();
			textArea.setBounds(394, 265, -147, 46);
			createpanel.add(textArea);
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
