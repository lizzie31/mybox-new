package controllers;

import view.*;
import Model.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class createNewGroupController {
	/**
	 * the new group that administrarotor field.
	 */
	private interestGroups newgroup;
	private createNewGroupGUI group;
	private administratorMenuController adm;
	public createNewGroupController(createNewGroupGUI group, administratorMenuController lastCon)
	{
		this.group=group;
		this.adm=lastCon;
		group.addcancel(new ButtonCancelListener());
		group.addAdd(new ButtonaddlListener());
	}
	private class ButtonaddlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonaddPressed();
		}
		
	}
	
	private void buttonaddPressed() {
		
		newgroup.setGroupName(group.getGroupname().getText());
		
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
