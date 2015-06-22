package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import client.myboxapp;
import controllers.JoinGroupCon.SelectedGroupListener;
import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import Model.directories;
import Model.file;
import view.chooseAdvancedRegularGUI;
import view.createNewFileGUI;
import view.createNewGroupGUI;
import view.fileMenuGui;
import view.permissionGui;

public class permissionController extends AbstractTransfer {

	/**currGui is permission window*/
	private permissionGui CurrGui;
	/**prevCon is the file menu controller*/

	private fileMenuCon prevController;
	/**selectedPermission is the permission the user selected*/
	
	private File f1 = null;
	private boolean flag = false;
	private directories parent=null;
	private int selectedComboBox = 0;

	private int permission;
	private User userDetails;
	private file fileChangePermission;
	private file advancedFile;

	
	/**constructor
	 * @param u
	 * @param per
	 * @param f
	 */
	public permissionController(permissionGui permGui, fileMenuCon fileMenuController,User userDetails, file file)
	{
		this.fileChangePermission=file;
		this.userDetails=userDetails;
		this.CurrGui=permGui;
		this.prevController=fileMenuController;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.selectPermission(new SelectedPermissionListener());
		CurrGui.addChooseAdvancedGroups(new addChooseAdvancedGroupsListener());
		CurrGui.addOk(new ButtonOk());
	}

	private class addChooseAdvancedGroupsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addChooseAdvancedGroupsPressed();
		}	
	}
	
	private void addChooseAdvancedGroupsPressed() {
		//CurrGui.close();
		chooseAdvancedRegularGUI CA=new chooseAdvancedRegularGUI (userDetails);
		new chooseAdvancedController(CA,this,userDetails);
		CA.setVisible(true);
	}
	

	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}	
	}
		
	private void buttoncancelPressed() {
		CurrGui.dispose();
		prevController.getCurrGui().setVisible(true);		
	}
	
	
	/**button listener of selecting a permission*/
	public class SelectedPermissionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			String i = (CurrGui.getComboBox().getSelectedItem()).toString();
			if (i.equals("0"))
			{
				CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
				//CurrGui.getComboBox().setSelectedItem(3);
				//setSelectedComboBox(3);
			}
			
			if(i.equals("1"))
			{
				CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(1);
			}
			if(i.equals("2"))
			{
				CurrGui.getBtnChooseAdvancedGroups().setEnabled(true);
				setSelectedComboBox(2);
			}
			
			if(i.equals("3"))
			{
				CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(3);
			}		
		}
	}
	/**button listener of send*/
	public class ButtonOk implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			ButtonOkPressed();
		}
	}
	
	private void ButtonOkPressed()
	{
		int i = getSelectedComboBox();
		
		if (i == 0)
		{
			CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
			JOptionPane.showMessageDialog(CurrGui, "Please choose the permission!", "Not choosed",0,null);
		}

		if(advancedFile!=null)
		{
			fileChangePermission.setGroupsForRead(advancedFile.getGroupsForRead());
			fileChangePermission.setGroupsForUpdate(advancedFile.getGroupsForUpdate());
		}
			
		if(advancedFile == null && selectedComboBox == 2)
			fileChangePermission.setGroupsForRead(userDetails.getInterestGroupInDB());
			
		/*if(advancedFile == null && selectedComboBox == 3)
		{
				
			fileChangePermission.setGroupsForRead(userDetails.getInterestGroupInDB());
		}*/

		String task = "Change permission " + Integer.toString(selectedComboBox);
		Envelope ev = new Envelope(fileChangePermission,task);
		sendToServer(ev);
		myboxapp.clien.setCurrObj(this);
			
			
	}

	public void handleDBResultFile(Object message) {
		if(message.equals("permission updated successfully"))
		{
		
			JOptionPane.showMessageDialog(CurrGui, "Permission updated successfully!");
			CurrGui.dispose();
			prevController.getCurrGui().setVisible(true);
		}
		
		else 
		{
			JOptionPane.showMessageDialog(CurrGui, "error");
			CurrGui.dispose();
			prevController.getCurrGui().setVisible(true);
		}
		
	}


	public permissionGui getPermissionGUI() {
		return CurrGui;
	}

	public void setCreatefile(permissionGui permissionGUI) {
		this.CurrGui = permissionGUI;
	}
	public file getAdvancedFile() {
		return advancedFile;
	}

	public void setAdvancedFile(file advancedFile) {
		this.advancedFile = advancedFile;
	}

	public int getSelectedComboBox() {
		return selectedComboBox;
	}

	public void setSelectedComboBox(int selectedComboBox) {
		this.selectedComboBox = selectedComboBox;
	}

}


