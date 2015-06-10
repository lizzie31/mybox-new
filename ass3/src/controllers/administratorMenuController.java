package controllers;


import java.awt.CardLayout;
import java.awt.Container;

import view.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.userMainMenuController.LogOutListener;

import Model.User;

public class administratorMenuController extends userMainMenuController {
	
	private administratorMenuGUI currgui2;
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
	
	 class LogOutListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				UpdateDB(); 
				currgui2.dispose();
				getPrevController().getLoginG().ClearText();
				getPrevController().getLoginG().setVisible(true);
			;
			}
	 }
	private class ButtonCreateGroupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonCreateGroup();
		}
		
	}
private void buttonCreateGroup() {
		CurrGui.close();
		
		createNewGroupGUI R= new createNewGroupGUI();
		new createNewGroupController(R,this);
		R.setVisible(true);
	}

	

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
	public administratorMenuGUI getAdminCon()
	{
		return this.currgui2;
	}
	public administratorMenuGUI getusermainmenu2() {

		return currgui2;

	}


}
