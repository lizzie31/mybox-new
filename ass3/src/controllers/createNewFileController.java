package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.file;
import view.*;

public class createNewFileController {
	/**createfile is create new file window*/
	private createNewFileGUI createfile=null;
	/**prevController is user main menu controller*/
	private userMainMenuController prevController;
	/**f is a file*/
	private file f = null;
	
	/**Constructor*/
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon){
		
		this.createfile=g;
		prevController=lastCon;
		createfile.addcancel(new ButtoncancelListener());
	}
	/**ButtoncancelListener is a class that implements action listener and goes back to the user main menu window*/
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		createfile.close();
		if (prevController instanceof administratorMenuController)
		((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else prevController.getusermainmenu().setVisible(true);
	}
	public file getF() {
		return f;
	}
	public void setF(file f) {
		this.f = f;
	}
	
	
}