package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.myboxapp;
import Model.Envelope;
import Model.file;
import Model.interestGroups;
import view.EditGroupGUI;
import view.deleteGroupGUI;
import controllers.deleteGroupController.ButtonDeleteListener;
import controllers.deleteGroupController.SelectedGroupListener;

public class EditGroupCon extends AbstractTransfer {
	
	/**group is delete group window*/
	private EditGroupGUI egroup;
	private EditGroupCon thiscon;
	private interestGroups grouptoedit;
	private administratorMenuController adm;
	private EditGroupCon currcon=null;
	private String filetodelete;
	private file filetoadd;
	private Envelope en;
	private String permmision;
	/** changepermission contain the current permmision of file in group-read/update*/
	private String changepermission;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public EditGroupCon(EditGroupGUI eG, administratorMenuController lastCon , interestGroups grouptoedit)
	{
		this.egroup=eG;
		this.grouptoedit=grouptoedit;
		this.thiscon=this;
		this.adm=lastCon;
		egroup.addAddfile(new ButtonaddfileListener());
		egroup.addcancel(new ButtonCancelListener());
		egroup.addchangeP(new ButtonchangePListener() );
		egroup.adddeletefile(new ButtondeleteListener());
		egroup.addchecklist(new checkboxListener());
		egroup.addSelecfile(new SelectedfiletoDELListener());
		egroup.addSelecfile2(new SelectedfiletoADDListener());
	
	
	}
	private class ButtonchangePListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		en=new Envelope(grouptoedit,changepermission);
		sendToServer(en);
		JOptionPane.showMessageDialog(null, "the permission has changed");
			
		}
		
	}
	
	 private class checkboxListener implements ActionListener{
	    	public void actionPerformed(ActionEvent e) {
	    		Object source = e.getSource();
	      	
	      		  if (source ==egroup.getChckbxForRead()) permmision="read";
	      		  else permmision="update";
	      		  
	    	}
	 }
	    	
	public class SelectedfiletoDELListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			filetodelete=(String)(egroup.getComboBox().getSelectedItem());
			grouptoedit.setFiletoChangP(filetodelete);
			egroup.undisplayWarningMessage();
			int flag=0;
			filetodelete=(String)(egroup.getComboBox().getSelectedItem());
			for(int i=0;i<grouptoedit.getFilesForUpdate().size();i++){
				if((grouptoedit.getFilesForUpdate().get(i).getFileName()).equals(filetodelete)) 
				flag=1;
			}
			if(flag==1) changepermission="this file is for UPDATE";
			else changepermission="this file is for READ";
			egroup.setWarningMessageVisibleTrue2(changepermission);
		
			

		}
	}

	public class SelectedfiletoADDListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			filetoadd=new file((String)(egroup.getComboBox_1().getSelectedItem()));

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
		egroup.close();
		adm.getusermainmenu2().setVisible(true);
	
	}
	private class ButtondeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(filetodelete==null) egroup.setWarningMessageVisibleTrue("please select a file!");
			else{
			en=new Envelope(filetodelete,"delete file from interest group");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThiscon() );
			}
		}
	}
	private class ButtonaddfileListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(filetoadd==null) egroup.setWarningMessageVisibleTrue("please select a file!");
			
			else if( permmision==null) egroup.setWarningMessageVisibleTrue("please select a permmision for the file!");
			
			else{
		     if(permmision.equals("read"))
			{
				grouptoedit.getFilesForRead().add(filetoadd);
				en=new Envelope(grouptoedit,"add file for read");
			}
			
			else{
				grouptoedit.getFilesForUpdate().add(filetoadd);
				grouptoedit.getFilesForRead().add(filetoadd);
				en=new Envelope(grouptoedit,"add file for update");
			}		
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThiscon() );
		}
		}
		}
		

	/**
	 * @wbp.parser.entryPoint
	 */
	public EditGroupCon getThiscon() {
		return thiscon;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public EditGroupGUI getEgroup() {
		return egroup;
	}

	public String getChangepermission() {
		return changepermission;
	}

	public EditGroupCon getCurrcon() {
		return currcon;
	}
	
	
		

}
