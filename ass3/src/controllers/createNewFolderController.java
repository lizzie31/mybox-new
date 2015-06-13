package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

public class createNewFolderController {
	/**createfolder is create new folder window*/
	private createNewFolderGUI createfolder=null;
	/**prevController is user menu controller*/
	private userMainMenuController prevController;
	
	/**constractor*/
	public createNewFolderController (createNewFolderGUI g , userMainMenuController lastCon){
		
		this.createfolder=g;
		prevController=lastCon;
		createfolder.addcancel(new ButtoncancelListener());
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
}

