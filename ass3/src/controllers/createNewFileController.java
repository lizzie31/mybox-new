package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.file;
import view.*;

public class createNewFileController {
	
	private createNewFileGUI createfile=null;
	private userMainMenuController prevController;
	private file f = null;
	
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon){
		
		this.createfile=g;
		prevController=lastCon;
		createfile.addcancel(new ButtoncancelListener());
	}
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