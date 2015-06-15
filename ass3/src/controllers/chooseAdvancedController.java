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
	/**
	 * the users that the admin choose.
	 */
	private ArrayList<interestGroups> groupusers=new ArrayList<>();

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
		chooseAdvanced.addcancel(new ButtonCancelListener());
		chooseAdvanced.addAdd(new ButtonaddlListener());
	     chooseAdvanced.addchecklist(new checkboxListener());
	}




	private class checkboxListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
      	  for(int i=0;i<chooseAdvanced.getUserslist().size();i++)
      		  if (source ==chooseAdvanced.getUserslist().get(i)) 
      		  {
      			 for(int j=0;j<allgroups.size();j++)
      			 {
      				 if(chooseAdvanced.getUserslist().get(i).getText().equals(""+allgroups.get(j).getGroupName()))
      					 groupusers.add(allgroups.get(j));
      					 
      					 
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
