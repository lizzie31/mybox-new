package controllers;

import view.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class createNewGroupController {
	private createNewGroupGUI group;
	private administratorMenuController adm;
	public createNewGroupController(createNewGroupGUI group, administratorMenuController lastCon)
	{
		this.group=group;
		this.adm=lastCon;
		group.addcancel(new ButtonCancelListener());
		group.addok(new ButtonOklListener());
	}
	private class ButtonOklListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonOkPressed();
		}
		
	}
	
	private void buttonOkPressed() {
		group.close();
		adm.getAdminCon().setVisible(true);
	
	}
	private class ButtonCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	private void buttoncancelPressed() {
		group.close();
		adm.getAdminCon().setVisible(true);
	
	}

}
