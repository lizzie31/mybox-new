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
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class createNewFileGUI extends JFrame{

	private JPanel createpanel;
	private JButton btnCancel =null; 
	/**@param currGUI is the create new file gui*/
	private createNewFileGUI currGUI;
	
	/**@param btnOpen is a button in order to choose the folder*/
	private JButton btnOpen;
	private JTextArea textArea;
	private JButton btnFinish;
	private File file;
	private int selectedComboBox = 0;
	private JComboBox comboBox;
	private JFrame frame;
	private JButton btnChooseAdvancedGroups = null;
	private JButton btnChooseLocation =null;
	private JLabel lblSetPermmision;
	private JLabel lblFileName;
	private JLabel lblDescription;
	private JLabel label;
	/**@param fileNameField is the file name text field*/
	private JTextField fileNameField;
	/**@param descriptionField is the description text field*/
	private JTextField descriptionField;

	
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
		this.setBounds(400, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,450);
	    this.setContentPane(getCreatePanel());   	
	}

	private JPanel getCreatePanel(){
		if(createpanel==null)
		{
			createpanel=new JPanel();
			createpanel.setForeground(new Color(135, 206, 235));
			createpanel.setBackground(new Color(135, 206, 235));
			createpanel.setLayout(null);

			createpanel.add(getComboBoxButton());
			createpanel.add(getlblSetPermmision());
			createpanel.add(getfileNameField());
			createpanel.add(getlblFileName());
			createpanel.add(getdescriptionField());
			createpanel.add(getdescriptionLable());
			createpanel.add(getbtnOpen());
			createpanel.add(gebtnFinish());
			createpanel.add(getbtnCancel());
			createpanel.add(gettextArea());
			createpanel.add(getbtnChooseAdvancedGroups());
			createpanel.add(getbtnChooseLocation());
			createpanel.add(getlabel());
		}
			return createpanel;
	}

	/*************************************************buttons action listeners***********************************/
	public void selectPermission(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addChooseLocation(ActionListener l) {
		btnChooseLocation.addActionListener(l);
	}
	public void addFinish(ActionListener l) {
		btnFinish.addActionListener(l);
	}
	public void addOpen(ActionListener l) {
		btnOpen.addActionListener(l);
	}
	
	public void addChooseAdvancedGroups(ActionListener l) {
		btnChooseAdvancedGroups.addActionListener(l);
	}
	
	public void close() {
		this.setVisible(false);
		dispose();
	}

/**********************************************************getters and setters*****************************************/
	public JLabel getlabel(){
	
	
	label = new JLabel("");
	label.setIcon(new ImageIcon(createNewFileGUI.class.getResource("/view/Multicolor Grass Books.jpg")));
	label.setBounds(0, 0, 594, 414);
	return label;
}
	public JButton getbtnChooseLocation(){
	
	btnChooseLocation = new JButton("Choose location");
	btnChooseLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
	btnChooseLocation.setBounds(169, 263, 132, 32);
	return btnChooseLocation;}

	public JButton getbtnChooseAdvancedGroups(){
	
	btnChooseAdvancedGroups = new JButton("Choose advanced groups");
	btnChooseAdvancedGroups.setEnabled(false);
	btnChooseAdvancedGroups.setBounds(298, 162, 176, 23);
	return btnChooseAdvancedGroups;
}
	public JTextArea gettextArea(){

	textArea = new JTextArea();
	textArea.setBounds(394, 265, -147, 46);
	return textArea;
}
	public JButton getbtnCancel(){

	btnCancel = new JButton("Cancel");
	btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnCancel.setBounds(22, 333, 132, 32);
	return btnCancel;
}
	public JButton gebtnFinish(){
    
	btnFinish = new JButton("Finish");
	btnFinish.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnFinish.setBounds(342, 333, 132, 32);
	return btnFinish;
}
	public JButton getbtnOpen()
	{
		btnOpen = new JButton("Load your file");
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOpen.setBounds(169, 209, 132, 32);
		return btnOpen;
	}


	private JLabel getdescriptionLable()
	{
		
		lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDescription.setBounds(40, 84, 110, 14);
		return lblDescription;
	
	}
	public JTextField getdescriptionField()
	{
		descriptionField = new JTextField();
		descriptionField.setBounds(175, 84, 207, 46);
		descriptionField.setColumns(10);
		return descriptionField;
	}
	public JLabel getlblFileName()
	{
		lblFileName = new JLabel("File name:");
		lblFileName.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblFileName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileName.setBounds(40, 39, 93, 17);
		return lblFileName;
	}
	public JComboBox getComboBoxButton()
	{
		comboBox = new JComboBox();
		comboBox.setBounds(175, 165, 93, 20);
		createpanel.add(comboBox);
		comboBox.addItem(" ");
		for (int i=1;i<4;i++)
		{
			comboBox.addItem(i);
		}
		return comboBox;
		
	}
	public JTextField getfileNameField()
	{
		fileNameField = new JTextField();
		fileNameField.setBounds(175, 38, 207, 20);
		fileNameField.setColumns(10);
		return fileNameField;
	}
	public JLabel getlblSetPermmision()
	{

		lblSetPermmision = new JLabel("Set permmision:");
		lblSetPermmision.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblSetPermmision.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetPermmision.setBounds(22, 167, 132, 14);
		return lblSetPermmision;
		
	}
	public createNewFileGUI getCurrGUI() {
		return currGUI;
	}

	public void setCurrGUI(createNewFileGUI currGUI) {
		this.currGUI = currGUI;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public JComboBox getComboBox() {
		return this.comboBox;
		
	}
	
	public JButton getBtnChooseAdvancedGroups() {
		return btnChooseAdvancedGroups;
	}

	public void setBtnChooseAdvancedGroups(JButton btnChooseAdvancedGroups) {
		this.btnChooseAdvancedGroups = btnChooseAdvancedGroups;
	}
	
	public JTextField getFileNameField() {
		return fileNameField;
	}

	public void setFileNameField(JTextField fileNameField) {
		this.fileNameField = fileNameField;
	}

	public String getDescriptionField() {
		return descriptionField.getText();
	}

	public void setDescriptionField(JTextField descriptionField) {
		this.descriptionField = descriptionField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
}
