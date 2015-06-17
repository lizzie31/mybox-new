
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.file;
import view.*;

public class fileSearchController extends AbstractTransfer {
	/**searchG is a file search gui window*/
	private fileSearchGui searchG=null;
	/**prevController is user main menu controller*/
	private userMainMenuController prevController;
	/**fileDetails is a file that gives us information about specific file*/
	private file fileDetails;
	private String str;
	private Envelope en;
	
	/**constructor*/
	public fileSearchController (fileSearchGui g , userMainMenuController lastCon){
		
		this.searchG=g;
		prevController=lastCon;
		searchG.addcancel(new ButtoncancelListener());	
	}
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	/**Handling button cancel action performed*/
	private void buttoncancelPressed() {
		searchG.setVisible(false);
		if (prevController instanceof administratorMenuController)
			((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
			else prevController.getusermainmenu().setVisible(true);
	}
	/***********getters and setters************/
	public file getFileDetails() {
		return fileDetails;
	}
	
	public void setFileDetails(file fileDetails) {

		this.fileDetails = fileDetails;
	}


}