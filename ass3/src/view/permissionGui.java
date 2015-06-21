package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class permissionGui extends JFrame {

	private JPanel panel;
	private JButton btnCancel=null;
	private JComboBox comboBox;
	private JButton btnOk;
	/**@param wor is the warningGui*/
	private warningGui wor=null;
	private JLabel lblwarningMessage = null;
	private JButton btnChooseAdvancedGroups = null;
	

	public permissionGui() {
		initialize();
		this.setVisible(true);
	}

	private void initialize() {
		this.setBounds(100, 100, 250, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());

		comboBox = new JComboBox();
		comboBox.setBounds(271, 55, 75, 22);
		comboBox.addItem("");
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		panel.add(comboBox);
		
		JLabel lblSetNewPermission = new JLabel("set new permission for the file:");
		lblSetNewPermission.setBounds(42, 58, 189, 16);
		panel.add(lblSetNewPermission);
	
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(300, 167, 97, 25);
		panel.add(btnCancel);
		
		btnOk = new JButton("ok");
		btnOk.setBounds(42, 167, 97, 25);
		panel.add(btnOk);
		panel.add(getLblwarningMessage());
	}
	private JPanel getCreatePanel(){
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("set permission");
			
			btnChooseAdvancedGroups = new JButton("Choose advanced groups");
			btnChooseAdvancedGroups.setEnabled(false);
			btnChooseAdvancedGroups.setBounds(298, 120, 176, 23);
			panel.add(btnChooseAdvancedGroups);
			
		}
		return panel;
	}
	
	public void addSelectPermission(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void addOk(ActionListener l) {
		btnOk.addActionListener(l);
	}
	public void addChooseAdvancedGroups(ActionListener l) {
		btnChooseAdvancedGroups.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	
	/**getLblwarningMessage() returns a label with a warning message that user name of password is in correct*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setForeground(new Color(255, 0, 0));
			lblwarningMessage.setBounds(100, 205, 400, 32);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue() sets the warning message visible*/
	public void setWarningMessageVisibleTrue() {
		lblwarningMessage.setVisible(true);	
	}
	/** setWarningMessageVisibleTrue(String st) sets a warning message of the string st*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setBounds(249, 208, 200, 30);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
 	public void undisplayWarningMessage() {
		lblwarningMessage.setVisible(false);
		
	}
 	
 	public JButton getBtnChooseAdvancedGroups() {
		return btnChooseAdvancedGroups;
	}

	public void setBtnChooseAdvancedGroups(JButton btnChooseAdvancedGroups) {
		this.btnChooseAdvancedGroups = btnChooseAdvancedGroups;
	}
	
 
}
