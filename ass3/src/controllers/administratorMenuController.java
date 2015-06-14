package controllers;


import java.awt.CardLayout;
import java.awt.Container;

import view.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import client.myboxapp;
import controllers.userMainMenuController.LogOutListener;
import Model.User;
import Model.file;

public class administratorMenuController extends userMainMenuController {
	/**currgui 2 is administrator menu gui window
	 usersarr is array list of all the users */
	
	private administratorMenuGUI currgui2;
	private ArrayList<User> usersarr;
	/***constractor***/
	public administratorMenuController (userMainMenuGUI menu,logInCon lastCon,User user,administratorMenuGUI menu2){
		
	super(menu,lastCon,user);
    this.currgui2=menu2;
	currgui2.addrequests(new ButtonrequestsListener());
	currgui2.addcreatenewgroup(new ButtonCreateGroupListener());
	currgui2.addcreatenewfile(new ButtoncreatenewfileListener());
	currgui2.addDeletegroup(new ButtondeleteGroupListener());
	currgui2.addlogout(new LogOutListener());

	currgui2.addcreatenewfolder(new ButtoncreatenewfolderListener());
	}
	/**ButtondeleteGroupListener is a class that implements action listener and opens delete group window*/
	private class ButtondeleteGroupListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttondeleteGroup();
		}
		
	}
	private void buttondeleteGroup() {
		currgui2.close();
		
		deleteGroupGUI R= new deleteGroupGUI();
		new deleteGroupController(R,this);
		//R.setVisible(true);
	}
	/**ButtoncreatenewfolderListener is a class that implements action listener and opens create new folder window*/
	private class ButtoncreatenewfolderListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonCreatefolder();
		}
		
	}
private void buttonCreatefolder() {
		CurrGui.close();
		
		createNewFolderGUI R= new createNewFolderGUI();
		new createNewFolderController(R,this);
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

/**handleDBResult2(Object message) handles data that comes from the data base*/



public void handleDBResult2(Object message) {
	
	if(message instanceof ArrayList<?>)
	{
		if(((ArrayList<?>) message).get(0) instanceof User)
			usersarr= (ArrayList<User>)message;
	}
	CurrGui.close();
	createNewGroupGUI R= new createNewGroupGUI(usersarr);
	new createNewGroupController(R,this);
	
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
	new createNewFileController(CNFG,this);
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
		requestsGUI R= new requestsGUI();
		new requestController(R,this);
	}
	/**getAdminCon() returns the administrator manu gui window*/
	public administratorMenuGUI getAdminCon()
	{
		return this.currgui2;
	}
	/**getusermainmenu2() returns the administrator manu gui window*/
	public administratorMenuGUI getusermainmenu2() {

		return currgui2;

	}


}
