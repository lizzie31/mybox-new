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
	//private userMainMenuController prevCon;
	private fileMenuCon prevCon;
	/**selectedPermission is the permission the user selected*/
	private String selectedPermission;
	private int selectedComboBox;
	private int permission;
	private User user;
	private file f;
	private file advancedFile;

	public permissionGui getPermissionGUI() {
		return CurrGui;
	}

	public void setCreatefile(permissionGui permissionGUI) {
		this.CurrGui = permissionGUI;
	}
	/**constructor
	 * @param u
	 * @param per
	 * @param f
	 */
	public permissionController(permissionGui per, fileMenuCon f,User u, file file)
	{
		this.f=file;
		this.user=u;
		this.CurrGui=per;
		this.prevCon=f;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addSelectPermission(new SelectedPermissionListener());
		CurrGui.addChooseAdvancedGroups(new addChooseAdvancedGroupsListener());
		CurrGui.addOk(new ButtonOk());
	}
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	
	private void buttoncancelPressed() {
		CurrGui.dispose();
		prevCon.getCurrGui().setVisible(true);
		
	}
	
	private class addChooseAdvancedGroupsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addChooseAdvancedGroupsPressed();
		}	
	}
	
	
	private void addChooseAdvancedGroupsPressed() {
		//CurrGui.close();
		chooseAdvancedRegularGUI CA=new chooseAdvancedRegularGUI (user);
		new chooseAdvancedController(CA,this,user);
		CA.setVisible(true);
	}
	
	/**button listener of selecting a permission*/
	public class SelectedPermissionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			selectedPermission=(String) CurrGui.getComboBox().getSelectedItem();
			if(selectedPermission.equals("1"))
			{
				permission=1;
				CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
			}
			else
			{
				if(selectedPermission.equals("2"))
				{
					permission=2;
					CurrGui.getBtnChooseAdvancedGroups().setEnabled(true);
				}
				else
				{
					if(selectedPermission.equals("3"))
						permission=3;
					else
						permission=0;
					CurrGui.getBtnChooseAdvancedGroups().setEnabled(false);
				}
			}
			
		}
	}
	/**button listener of send*/
	public class ButtonOk implements ActionListener
	{
		private int selectedComboBox;

		public void actionPerformed(ActionEvent e) {
		

			Envelope en = new Envelope(f,"");

			//Envelope en;
			if(permission==0)
				CurrGui.setWarningMessageVisibleTrue("please select a permission!");
			else
			{
					
				if(permission==1)
					en=new Envelope(f,"change permission 1");
				else
				{
					if(permission==2)
						en=new Envelope(f,"change permission 2");
					else
						en=new Envelope(f,"change permission 3");
				}
				f.setFilePermission(permission);	
				//sendToServer(en);
				
			
			if(advancedFile!=null)
			{
				f.setGroupsForRead(advancedFile.getGroupsForRead());
				f.setGroupsForUpdate(advancedFile.getGroupsForUpdate());
				en.setTask("change permission 2");
				f.setFilePermission(2);
				CurrGui.undisplayWarningMessage();
			}
			
			if(advancedFile == null && permission == 2)
			{
				f.setGroupsForRead(user.getInterestGroupInDB());
				CurrGui.undisplayWarningMessage();
				f.setFilePermission(2);
			}
			
			if(advancedFile == null && permission == 3)
			{
				en.setTask("change permission 3");
				//f.setGroupsForRead(user.getInterestGroupInDB());
				CurrGui.undisplayWarningMessage();
				f.setFilePermission(3);
			}
			if(permission==1)
			{
				en.setTask("change permission 1");
				CurrGui.undisplayWarningMessage();
				f.setFilePermission(1);
			}
			
			if(permission==0)
			{
				CurrGui.setWarningMessageVisibleTrue("please select a permission!");
			}
			
			//Component frame1 = null;
			//JOptionPane.showMessageDialog(frame1, "Permissions updated successfully!");
			//CurrGui.dispose();
			//prevCon.getCurrGui().setVisible(true);
			//f.setFilePermission(permission);	
			
		}
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
	}
	
	public void handleDBResultFile(Object message) {
		if(message.equals("updated successfully"))
		{
		
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "updated successfully2");
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		}
		
		else 
		{
			JOptionPane.showMessageDialog(CurrGui, "error");
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		}
		
	}

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

