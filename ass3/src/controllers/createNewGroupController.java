package controllers;

import view.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.myboxapp;


public class createNewGroupController extends AbstractTransfer {
	/**
	 * the new group that administrarotor field.
	 */
	private interestGroups newgroup;
	private createNewGroupGUI group;
	private administratorMenuController adm;
	/**
	 * contain the details about the new group to send the server.
	 */
	Envelope en;
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
		newgroup= new interestGroups(null);
		newgroup.setGroupName(group.getGroupname().getText());
		en=new Envelope(newgroup,"add new group to DB");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
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

	public createNewGroupGUI getGroup() {
		return group;
	}
	
	

}
