package controllers;


import java.awt.CardLayout;
import java.awt.Container;

import view.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import client.myboxapp;
import controllers.deleteGroupController.SelectedGroupListener;
import controllers.userMainMenuController.LogOutListener;

import controllers.userMainMenuController.addsearchfilesListener;

import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import Model.file;
import Model.interestGroups;

public class administratorMenuController extends userMainMenuController {
	/**currgui 2 is administrator menu gui window
	 usersarr is array list of all the users */
	
	private administratorMenuGUI currgui2;
	/***currgroup is the current interest group*/
	private interestGroups currgroup;
	/**usersarr is array list of all the users*/
	private ArrayList<User> usersarr;
	/**allinterestgroups is array list of interest groups*/
	private ArrayList<interestGroups> allinterestgroups;
	/**allrequests is array list of group requests*/
	private ArrayList<GroupsRequests> allrequests;
	/**allfiles is array list of all the files**/
	private ArrayList<file> allfiles;
	private Envelope en=null;
	private interestGroups grouptoedit=null;
	/**the group that the admin choose to edit*/
	private String GroupName=null;
	/**fileArr is array list of files*/
	private ArrayList<file> fileArr;
	private User u;
	/***constractor***
	 * 
	 * @param menu
	 * @param lastCon
	 * @param user
	 * @param menu2
	 */
	public administratorMenuController (userMainMenuGUI menu,logInCon lastCon,User user,administratorMenuGUI menu2){
		
	super(menu,lastCon,user);
	this.u=user;
    this.currgui2=menu2;
	
	currgui2.addcreatenewgroup(new ButtonCreateGroupListener());
	currgui2.addcreatenewfile2(new ButtoncreatenewfileListener());
	currgui2.addDeletegroup(new ButtondeleteGroupListener());
	currgui2.addlogout(new LogOutListener());


	currgui2.addcreatenewfolder(new ButtoncreatenewfolderListener());
	currgui2.addrequests(new ButtonrequestsListener());
	currgui2.addSelectGroup(new SelectedGroupListener());
	currgui2.addeditgroup(new ButtoneditGroupListener());
	currgui2.addsearchfiles(new addsearchfilesListener());
	}
	/********************action listeners********************/
	private class ButtoneditGroupListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoneditGroupprresed();
		}
		
	}
	private void buttoneditGroupprresed(){
		
		if(GroupName==null) currgui2.setWarningMessageVisibleTrue("please select a group");
		else{
		currgroup=new interestGroups(GroupName);
		Envelope en=new Envelope(currgroup,"Show fils in group");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
		}
	

	

	}
	/**button listener of search file*/
	protected class addsearchfilesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonsearchfilesPressed();
		}	
	}
	protected void buttonsearchfilesPressed() {	
		String str = currgui2.getTextField();
		if(str.equals(""))
			currgui2.setWarningMessageVisibleTrue("Please type the filename to search!");	
		else
		{
			currgui2.undisplayWarningMessage();
			Envelope en =new Envelope (str,"search files 2");
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
		}
			
	}
	/**ButtondeleteGroupListener is a class that implements action listener and opens delete group window*/
	private class ButtondeleteGroupListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttondeleteGroup();
		}
		
	}
	private void buttondeleteGroup() {
		CurrGui.close();
		sendToServer("ShowAllGroups");
		myboxapp.clien.setCurrObj(this);
	}
	public class SelectedGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			GroupName=(String)currgui2.getComboBox().getSelectedItem();

		}
	}
	/**ButtoncreatenewfolderListener is a class that implements action listener and opens create new folder window*/
	private class ButtoncreatenewfolderListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonCreatefolder();
		}
		
	}
	private void buttonCreatefolder() {

		createNewFolderGUI R= new createNewFolderGUI(u);
		new createNewFolderController(R,this,u);

		R.setVisible(true);

	}
	/**LogOutListener is a class that implements action listener and logs out the user*/
	 class LogOutListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				UpdateDB(); 
				currgui2.dispose();
				getPrevController().getLoginG().ClearText();
				getPrevController().getLoginG().setVisible(true);
			;
			}
	 }
	 /**ButtonCreateGroupListener is a class that implements action listener and opens create new group window*/
	private class ButtonCreateGroupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonCreateGroup();
		}
		
	}
private void buttonCreateGroup() {
		CurrGui.close();
		sendToServer("ShowAllUsers");
		myboxapp.clien.setCurrObj(this);
		
	
	}
/**handleDBResultFile handles results from the DB*/
public void handleDBResultFile(Object message) {
	if(message==null)
		currgui2.setWarningMessageVisibleTrue("there are no files with this name. try again.");
	if(message instanceof ArrayList<?>)
	{
		ArrayList a= (ArrayList<?>) message;
		if(a.get(0) instanceof file)
	    fileArr= (ArrayList<file>)message;
		currgui2.undisplayWarningMessage();
		currgui2.close();
		fileSearchGui SG=new fileSearchGui (fileArr);
		new fileSearchController(SG,this,userDetails,fileArr);
		SG.setVisible(true);
	}
}

/**handleDBResult2(Object message) handles data that comes from the data base*/
public void handleDBResult2(Object message) {
	
	if(message instanceof ArrayList<?>)
	{
		if(((ArrayList<?>) message).get(0) instanceof User)
		{
		 usersarr= (ArrayList<User>)message;
		 createNewGroupGUI R= new createNewGroupGUI(usersarr);
		 new createNewGroupController(R,this,usersarr);
		}
		if(((ArrayList<?>) message).get(0) instanceof interestGroups)
		{
		 allinterestgroups= (ArrayList<interestGroups>)message;
		 deleteGroupGUI D= new deleteGroupGUI(allinterestgroups);
		new deleteGroupController(D,this);
		}
		if(((ArrayList<?>) message).get(0) instanceof GroupsRequests)
		{
		 allrequests= (ArrayList<GroupsRequests>)message;
		
		 requestsGUI D= new requestsGUI(allrequests);
		new requestController(D,this,allrequests);

		}
	}
	if(message instanceof interestGroups)
	{
	
		grouptoedit=(interestGroups) message;
		sendToServer("show all files");
		myboxapp.clien.setCurrObj(this);
		
		
	}
}
	public void getfiles(Object message)
	{
		allfiles=(ArrayList<file>) message;
		EditGroupGUI EG=new EditGroupGUI(grouptoedit,allfiles);	
		new EditGroupCon(EG,this,grouptoedit);
	}
	
	
	
/**ButtoncreatenewfileListener is a class that implements action listener and opens create new file window*/
private class ButtoncreatenewfileListener implements ActionListener {
@Override
	public void actionPerformed(ActionEvent arg0) {
		buttoncreatenewfilePressed();
	}
}
private void buttoncreatenewfilePressed() {
	CurrGui.close();
	createNewFileGUI CNFG=new createNewFileGUI ();
	new createNewFileController(CNFG,this,userDetails);
	CNFG.setVisible(true);

}
	/**ButtonrequestsListener is a class that implements action listener and opens the requests window*/
	private class ButtonrequestsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonrequestsPressed();
		}
		
	}
	
	private void buttonrequestsPressed() {

		CurrGui.close();
		sendToServer("ShowAllrequets");
		myboxapp.clien.setCurrObj(this);
		}

	/**getusermainmenu2() returns the administrator menu gui window*/

	public administratorMenuGUI getusermainmenu2() {

		return currgui2;

	}


}
