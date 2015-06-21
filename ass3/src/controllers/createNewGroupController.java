package controllers;

import view.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.myboxapp;


public class createNewGroupController<JCheckBox> extends AbstractTransfer {
	/**
	 * the new group that administrarotor field.
	 */
	private interestGroups newgroup;
	private createNewGroupGUI group;
	private administratorMenuController adm;
	/**
	 * all the users in DB.
	 */
	private ArrayList<User> allusers;
	/**
	 * the users that the admin choose.
	 */
	private ArrayList<User> groupusers=new ArrayList<>();

	/**
	 * contain the details about the new group to send the server.
	 */
	Envelope en;
	
	/**constructor*/
	public createNewGroupController(createNewGroupGUI group, administratorMenuController lastCon ,ArrayList<User> allusers )
	{
		this.group=group;
		this.adm=lastCon;
		this.allusers=allusers;
		group.addcancel(new ButtonCancelListener());
		group.addAdd(new ButtonaddlListener());
	     group.addchecklist(new checkboxListener());

	}
	

	
	
    private class checkboxListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
      	  for(int i=0;i<group.getUserslist().size();i++)
      		  if (source ==group.getUserslist().get(i)) 
      		  {
      			 for(int j=0;j<allusers.size();j++)
      			 {
      				 if(group.getUserslist().get(i).getText().equals(""+allusers.get(j).getUserName()))
      					 groupusers.add(allusers.get(j));
      					 
      					 
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
		if(groupusers.size()==0) group.setWarningMessageVisibleTrue("please choose group members!");
		else if( group.getGroupname().getText().equals(""))
		{
			group.setWarningMessageVisibleTrue("please write a group name!");
		}
		else {
		newgroup= new interestGroups(group.getGroupname().getText(), groupusers);
		en=new Envelope(newgroup,"add new group to DB");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
		}
		
	     
	
	}
	/**ButtonCancelListener is a class that implements action listener and goes back to administrator menu window*/
	private class ButtonCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	private void buttoncancelPressed() {
		group.close();
		adm.getusermainmenu2().setVisible(true);
	
	}

	public createNewGroupGUI getGroup() {
		return group;
	}
	
	

}
