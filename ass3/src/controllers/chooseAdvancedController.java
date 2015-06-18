package controllers;

import view.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.ArrayList;

import client.myboxapp;


public class chooseAdvancedController<JCheckBox> extends AbstractTransfer {
	/**
	 * the new group that administrarotor field.
	 */
	private interestGroups newgroup;
	private chooseAdvancedRegularGUI chooseAdvanced;
	private createNewFileController createFileCon;
	/**
	 * all the users in DB.
	 */
	private ArrayList<interestGroups> allgroups;
	private ArrayList<interestGroups> groupsR;
	private ArrayList<interestGroups> groupsU;
	/**
	 * the users that the admin choose.
	 */
	private ArrayList<interestGroups> groupusers=new ArrayList<>();
	private ArrayList<interestGroups> groupsRead=new ArrayList<>();
	private ArrayList<interestGroups> groupsUpdate=new ArrayList<>();
	/**
	 * contain the details about the new group to send the server.
	 */
	Envelope en;
	
	/**constructor*/
	
    public chooseAdvancedController(chooseAdvancedRegularGUI cA,createNewFileController createFileCon, User userDetails) 
    {
    	this.chooseAdvanced=cA;
		this.createFileCon=createFileCon;
		this.allgroups=userDetails.getInterestGroupInDB();
		this.groupsR=userDetails.getInterestGroupInDB();
		this.groupsU=userDetails.getInterestGroupInDB();
		chooseAdvanced.addcancel(new ButtonCancelListener());
		chooseAdvanced.addAdd(new ButtonaddlListener());
	     chooseAdvanced.addchecklist(new checkboxListener());
	     chooseAdvanced.addchecklistUpdate(new checkboxUpdateListener());
	}




	private class checkboxListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
      	  for(int i=0;i<chooseAdvanced.getGroupsReadList().size();i++)
      		  if (source ==chooseAdvanced.getGroupsReadList().get(i)) 
      		  {
      			 for(int j=0;j<groupsR.size();j++)
      			 {
      				 if(chooseAdvanced.getGroupsReadList().get(i).getText().equals(""+groupsR.get(j).getGroupName()))
      					groupsRead.add(groupsR.get(j));
      					 
      					 
      			 }
      		  }		
		}
    }
	
	
	private class checkboxUpdateListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
      	  for(int i=0;i<chooseAdvanced.getGroupsUpdateList().size();i++)
      		  if (source == chooseAdvanced.getGroupsUpdateList().get(i)) 
      		  {
      			 for(int j=0;j<groupsU.size();j++)
      			 {
      				 if(chooseAdvanced.getGroupsUpdateList().get(i).getText().equals(""+groupsU.get(j).getGroupName()))
      					groupsUpdate.add(groupsU.get(j));     					 
      			 }
      		  }	
		}
    }
    	  
	/**ButtoncancelListener is a class that implements action listener and adds a new group*/
	private class ButtonaddlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonaddPressed();
		}
		
	}
	
	private void buttonaddPressed() {
		
		chooseAdvanced.close();
		createFileCon.setAdvancedFile(new file(groupsRead,groupsUpdate));
		createFileCon.getCreatefile().setVisible(true);
		/*newgroup= new interestGroups(group.getGroupname().getText(), groupusers);
		en=new Envelope(newgroup,"add new group to DB");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);*/
	
	
	}
	/**ButtonCancelListener is a class that implements action listener and goes back to administrator menu window*/
	private class ButtonCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	private void buttoncancelPressed() {
		chooseAdvanced.close();
		createFileCon.getCreatefile().setVisible(true);
	
	}

	public chooseAdvancedRegularGUI getGroup() {
		return chooseAdvanced;
	}
	
	

}
