





package controllers;
import view.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.myboxapp;
import Model.Envelope;
import Model.GroupsRequests;
import Model.interestGroups;
import controllers.JoinGroupCon.SelectedGroupListener;



public class deleteGroupController extends AbstractTransfer {
	/**group is delete group window*/
	private deleteGroupGUI group;
	/**adm is administrator menu controller*/
	private administratorMenuController adm;
	/**the group that the admin choose to delete*/
	private String GroupName=null;
	/**the group that the admin choose to delete*/
	private interestGroups groupToDel;
	private deleteGroupController thiscon;
	
	/**constructor
	 * 
	 * @param group
	 * @param lastCon
	 */
	public deleteGroupController(deleteGroupGUI group, administratorMenuController lastCon)
	{
		this.group=group;
		this.adm=lastCon;
		this.thiscon=this;
		group.addcancel(new ButtonCancelListener());
		group.addSelectGroup(new SelectedGroupListener());
		group.adddelete(new ButtonDeleteListener() );
	
	}

	public class SelectedGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			GroupName=(String)group.getComboBox().getSelectedItem();

		}
	}
	public class ButtonDeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			buttondeletePressed();
		}
		
		private void buttondeletePressed() {
			if(GroupName==null)
			{
				group.setWarningMessageVisibleTrue("please select a group!");
			}
			else
			{
			groupToDel=new interestGroups(GroupName);
			group.close();
			Envelope en=new Envelope(groupToDel,"Delete this Group");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThiscon() );
			}
		}
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

	public deleteGroupGUI getGroup() {
		return this.group;
	}

	public deleteGroupController getThiscon() {
		return thiscon;
	}

	public void setThiscon(deleteGroupController thiscon) {
		this.thiscon = thiscon;
	}
	
	

}
