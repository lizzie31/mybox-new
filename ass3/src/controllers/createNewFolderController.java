package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Envelope;
import Model.User;
import Model.directories;
import view.*;

public class createNewFolderController extends AbstractTransfer{
	/**createfolder is create new folder window*/
	private createNewFolderGUI createfolder=null;
	/**prevController is user menu controller*/
	private userMainMenuController prevController;
	private User user=null;
	
	/**Constructor
	 * 
	 * @param g
	 * @param lastCon
	 * @param u
	 */
	public createNewFolderController (createNewFolderGUI g , userMainMenuController lastCon,User u){
		
		this.createfolder=g;
		this.user=u;
		prevController=lastCon;
		createfolder.addcancel(new ButtoncancelListener());
		createfolder.addOk(new ButtonOKListener());
	}
	/**ButtoncancelListener is a class that implements action listener and opens user main menu window*/
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		createfolder.close();
		if (prevController instanceof administratorMenuController)
		((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else prevController.getusermainmenu().setVisible(true);
	}
	
	private class ButtonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonOKPressed();
			
		}
		
	}
	
	private void buttonOKPressed() {
		String foldername=createfolder.getTextField();
		directories dir=new directories(foldername);
		//user.getuserDirectories().add(dir);
		Envelope en=new Envelope(user,"add directory");
		sendToServer(en);
		createfolder.close();
		prevController.getusermainmenu().dispose();
		
	}
}

