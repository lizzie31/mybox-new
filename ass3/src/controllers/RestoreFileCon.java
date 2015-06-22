package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.InterestGroupGui;
import view.RestoreFilesGui;
import view.groupListGUI;
import view.leavegroupGUI;
import view.userMainMenuGUI;
import Model.Envelope;
import Model.User;
import Model.file;
import Model.interestGroups;
import client.myboxapp;
import controllers.GroupsListCon.listListener;

public class RestoreFileCon extends AbstractTransfer{
	/**grouplist is group list gui window*/
	private RestoreFilesGui CurrGui=null;
	/**prev controller is user main menu controller*/
	private userMainMenuController prevController;	
	private User user;
	private RestoreFileCon thisCon=this;
	private ArrayList<file> RestoreFiles=null;
	private file fileToRestore;
	
	/**constructor
	 * 
	 * @param g
	 * @param lastCon
	 * @param userDetails
	 */
	public RestoreFileCon(RestoreFilesGui g , userMainMenuController lastCon, User userDetails,ArrayList<file> RestoreFile){
		
		this.CurrGui=g;
		this.prevController=lastCon;
		this.user=userDetails;
		this.RestoreFiles=RestoreFile;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addListActionListener(new listListener());
		CurrGui.addbtnrestore(new restoreListener());
		
	}
	/****************************************action Listeners**************************************************************/

	/**list listener is a class that implements list selection listener and handles a press on the list*/
	public class listListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			  for(int i=0;i<RestoreFiles.size();i++)
			  {
				  if(RestoreFiles.get(i).getFileName().equals(CurrGui.getList_1().getSelectedValue()))
				  {
					  fileToRestore=RestoreFiles.get(i);
				  }
			 }
			
		}

		
	}
	
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
	}
		
	/**button listener of cancel*/
	private class restoreListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnRestorePressed();
		}
		
	}
	
	public void btnRestorePressed()
	{
	    Envelope en=new Envelope(fileToRestore,"restore file");
	    sendToServer(en);
		en=new Envelope(user,"refresh data");
		sendToServer(en);
		myboxapp.clien.setCurrObj(getThisCon());
	}
	
	private void buttoncancelPressed() {
		CurrGui.close();
		prevController.getusermainmenu().setVisible(true);
	}
	/***********getters and setters************/
	private Object getCurrCon() {
		
		return this;
	}

	public RestoreFilesGui getGroupListGui() {
		return CurrGui;
	}

	public RestoreFileCon getThisCon() {
		return thisCon;
	}
	public void setThisCon(RestoreFileCon thisCon) {
		this.thisCon = thisCon;
	}
	

	/**refresh user data*/
	public void RefreshUserData(User userrefresh) {
		user=userrefresh;
		CurrGui.close();
		userMainMenuGUI menu=new userMainMenuGUI(user);
		new userMainMenuController(menu,user);
	}
	

}
