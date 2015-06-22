package controllers;

import view.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.myboxapp;


public class chooseAdvancedController<JCheckBox> extends AbstractTransfer {
	/**
	 * the new group that administrarotor field.
	 */
	private interestGroups newgroup;
	private chooseAdvancedRegularGUI chooseAdvanced;
	private createNewFileController createFileCon;
	private permissionController perController;
	/**
	 * all the users in DB.
	 */
	//private ArrayList<interestGroups> allgroups;
	private ArrayList<interestGroups> groupsR;
	private ArrayList<interestGroups> groupsU;
	private boolean changePermission = false;
	/**
	 * the users that the admin choose.
	 */
	//private ArrayList<interestGroups> groupusers=new ArrayList<>();
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
		setChangePermission(false);
		this.groupsR=userDetails.getInterestGroupInDB();
		this.groupsU=userDetails.getInterestGroupInDB();
		chooseAdvanced.addcancel(new ButtonCancelListener());
		chooseAdvanced.addAdd(new ButtonaddlListener());
	     chooseAdvanced.addchecklist(new checkboxListener());
	     chooseAdvanced.addchecklistUpdate(new checkboxUpdateListener());
	}

	public chooseAdvancedController(chooseAdvancedRegularGUI cA, permissionController permissionCon, User userDetails) {
		this.chooseAdvanced=cA;
		this.perController=permissionCon;
		this.groupsR=userDetails.getInterestGroupInDB();
		this.groupsU=userDetails.getInterestGroupInDB();
		setChangePermission(true);
		chooseAdvanced.addcancel(new ButtonCancelListener());
		chooseAdvanced.addAdd(new ButtonaddlListener());
	    chooseAdvanced.addchecklist(new checkboxListener());
	    chooseAdvanced.addchecklistUpdate(new checkboxUpdateListener());
	}
/*********************action listeners*************************/
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
     				 {
     					if(!chooseAdvanced.getGroupsReadList().get(i).isSelected())
    					 {
         					if(chooseAdvanced.getGroupsUpdateList().get(i).isSelected())
         					{
         						
         						groupsUpdate.remove(groupsU.get(j));
         						JOptionPane.showMessageDialog(chooseAdvanced, "The group \"" + groupsU.get(j).getGroupName() +"\"" + " removed from update list!" , "Error!",0,null);
            					chooseAdvanced.getGroupsUpdateList().get(j).setSelected(false);
         					}
         					groupsRead.remove(groupsR.get(j));
    					 }
    					 else
    						 groupsRead.add(groupsR.get(j));
     				 }
      					 
      			 }
      		  }		
		}
    }
	
	
	private class checkboxUpdateListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) {
    		Object source = e.getSource();
    		//if(!chooseAdvanced.getGroupsReadList().contains(source))
    			
      	  for(int i=0;i<chooseAdvanced.getGroupsUpdateList().size();i++)
      	  {
      		  if (source == chooseAdvanced.getGroupsUpdateList().get(i)) 
      		  {
      			 for(int j=0;j<groupsU.size();j++)
      			 {
      				 if(chooseAdvanced.getGroupsUpdateList().get(i).getText().equals(""+groupsU.get(j).getGroupName()))
      				 {
      					 if(!chooseAdvanced.getGroupsReadList().get(i).isSelected())
      					 {
      						//groupsUpdate.remove(groupsU.get(j));
      						JOptionPane.showMessageDialog(chooseAdvanced, "The group \"" + groupsR.get(i).getGroupName() +"\"" + " unabled to read the file!" , "Error!",0,null);
          					chooseAdvanced.getGroupsUpdateList().get(j).setSelected(false);
      					 }
      					 if(!(chooseAdvanced.getGroupsUpdateList().get(j).isSelected()))
      						groupsUpdate.remove(groupsU.get(j));
      					 else
      						 groupsUpdate.add(groupsU.get(j));    
      					
      					//chooseAdvanced.getGroupsReadList().get(i).setSelected(true);
      				 }
      			 }
      		  }	
      	  }
		}
    }
    	  
	/**ButtoncancelListener is a class that implements action listener and adds a new group*/
	private class ButtonaddlListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonaddPressed();
		}
		
	}
	
	private void buttonaddPressed() {
		if(isChangePermission())
		{
			chooseAdvanced.close();
			perController.setAdvancedFile(new file(groupsRead,groupsUpdate));
			perController.getPermissionGUI().setVisible(true);
		}
		else{
			chooseAdvanced.close();
			createFileCon.setAdvancedFile(new file(groupsRead,groupsUpdate));
			createFileCon.getCreatefile().setVisible(true);
		}	
	
	}
	/**ButtonCancelListener is a class that implements action listener and goes back to administrator menu window*/
	private class ButtonCancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
	}
	
	private void buttoncancelPressed() {
		chooseAdvanced.close();		
		if(isChangePermission())
			perController.getPermissionGUI().setVisible(true);

		else
			createFileCon.getCreatefile().setVisible(true);
	}
	
	/**************getters and setters*****************/
	public chooseAdvancedRegularGUI getChooseAdvanced() {
		return chooseAdvanced;
	}
	public void setChooseAdvanced(chooseAdvancedRegularGUI chooseAdvanced) {
		this.chooseAdvanced = chooseAdvanced;
	}


	public boolean isChangePermission() {
		return changePermission;
	}
	public void setChangePermission(boolean changePermission) {
		this.changePermission = changePermission;
	}
}
