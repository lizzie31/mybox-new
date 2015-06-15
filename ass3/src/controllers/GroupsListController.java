package controllers;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.interestGroups;
import view.*;

public class GroupsListController extends AbstractTransfer{
	
	private groupListGUI grouplist=null;
	private userMainMenuController prevController;
	private User user;
	
	public GroupsListController (groupListGUI g , userMainMenuController lastCon, User userDetails){
		
		this.grouplist=g;
		this.prevController=lastCon;
		this.user=userDetails;
	    grouplist.addcancel(new ButtoncancelListener());
	    grouplist.addListActionListener(new listListener());
	}
	
	public GroupsListController(leavegroupGUI lG,
			userMainMenuController lastCon, User userDetails) {
		// TODO Auto-generated constructor stub
	}

	public class listListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			Envelope  en=null;
			  for(int i=0;i<user.getInterestGroupInDB().size();i++)
			  {
				  if(user.getInterestGroupInDB().get(i).getGroupName().equals(grouplist.GetList().getSelectedValue()))
					  en=new Envelope(user.getInterestGroupInDB().get(i),"show interest group to user");
			 }
			  sendToServer(en);
			  myboxapp.clien.setCurrObj(getCurrCon());
			
			
		}

		
	}
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttongrouplistPressed();
		}
		
	}
	private void buttongrouplistPressed() {
		grouplist.close();
		prevController.getusermainmenu().setVisible(true);
	}
	
	private Object getCurrCon() {
		
		return this;
	}

	public groupListGUI getGroupListGui() {
		return grouplist;
	}
	
	public void handleDBresult(interestGroups IGR) {
		grouplist.close();
		InterestGroupGui IGG=new InterestGroupGui(IGR);
		new InterestGroupCon(IGG,user,IGR,this);
		
	}

}