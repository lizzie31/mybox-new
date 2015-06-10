package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

public class createNewFolderController {
	
	private createNewFolderGUI createfolder=null;
	private userMainMenuController prevController;
	
	public createNewFolderController (createNewFolderGUI g , userMainMenuController lastCon){
		
		this.createfolder=g;
		prevController=lastCon;
		createfolder.addcancel(new ButtoncancelListener());
	}
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

