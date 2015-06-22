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

public class GroupListController extends AbstractTransfer{
	/**grouplist is group list gui window*/
	private groupListGUI grouplist=null;
	/**prev controller is user main menu controller*/
	private userMainMenuController prevController;	
	private User user;
	private GroupListController thisCon=this;
	
	/**constructor*/
	public GroupListController (groupListGUI g , userMainMenuController lastCon, User userDetails){
		
		this.grouplist=g;
		this.prevController=lastCon;
		this.user=userDetails;
	    grouplist.addcancel(new ButtoncancelListener());
	    grouplist.addListActionListener(new listListener());
	}
	/**************action listeners*******************/
	/**list listener is a class that implements list selection listener and handles a press on the list*/
	public class listListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			  for(int i=0;i<user.getInterestGroupInDB().size();i++)
			  {
				  if(user.getInterestGroupInDB().get(i).getGroupName().equals(grouplist.GetList().getSelectedValue()))
				  {
					  Envelope en=new Envelope(user.getInterestGroupInDB().get(i),"show interest group to user");
				     sendToServer(en);
				     myboxapp.clien.setCurrObj(getCurrCon());
				  }
			 }
			
		}

		
	}
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttongrouplistPressed();
		}
		
	}
	/**handleDBresult closes the current window and opens interest group gui window*/
	public void handleDBresult(interestGroups IGR) {
		grouplist.close();
		InterestGroupGui IGG=new InterestGroupGui(IGR);
		new InterestGroupCon(IGG,user,IGR,this);
		
	}
	private void buttongrouplistPressed() {
		
		Envelope en=new Envelope(user,"refresh data");
		sendToServer(en);
		myboxapp.clien.setCurrObj(getThisCon());
	}
	/***********getters and setters************/
	private Object getCurrCon() {
		
		return this;
	}

	public groupListGUI getGroupListGui() {
		return grouplist;
	}

	public GroupListController getThisCon() {
		return thisCon;
	}
	public void setThisCon(GroupListController thisCon) {
		this.thisCon = thisCon;
	}
	public void RefreshUserData(User userrefresh) {
		user=userrefresh;
		grouplist.close();
		userMainMenuGUI menu=new userMainMenuGUI(user);
		new userMainMenuController(menu,user);
	}
	
	

}
