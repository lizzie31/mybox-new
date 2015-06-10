
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
	
	private fileSearchGui searchG=null;
	private userMainMenuController prevController;
	private file fileDetails;
	private String str;
	private Envelope en;
	
	public fileSearchController (fileSearchGui g , userMainMenuController lastCon){
		
		this.searchG=g;
		prevController=lastCon;
		searchG.addcancel(new ButtoncancelListener());	
	}
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	public file getFileDetails() {
		return fileDetails;
	}
	
	public void setFileDetails(file fileDetails) {

		this.fileDetails = fileDetails;
	}

	private void buttoncancelPressed() {
		searchG.setVisible(false);
		if (prevController instanceof administratorMenuController)
			((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
			else prevController.getusermainmenu().setVisible(true);
	}
}