package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private administratorMenuController lastcon;
	private String filetodelete;
	private file filetoadd;
	private Envelope en;
	private String permmision;
	
	public EditGroupCon(EditGroupGUI eG, interestGroups grouptoedit,administratorMenuController lastCon)
	{
		this.egroup=eG;
		this.grouptoedit=grouptoedit;
		this.thiscon=this;
		this.lastcon=lastcon;
		egroup.addAddfile(new ButtonaddfileListener());
		egroup.addcancel(new ButtonCancelListener());
		//egroup.addchangeP(l);
		egroup.adddeletefile(new ButtondeleteListener());
		egroup.addchecklist(new checkboxListener());
		//egroup.addchecklinstupdate(l);
		egroup.addSelecfile(new SelectedfiletoDELListener());
		egroup.addSelecfile2(new SelectedfiletoADDListener());
	
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

		}
	}

	public class SelectedfiletoADDListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			filetoadd=new file((String)(egroup.getComboBox_1().getSelectedItem()));

		}
	}

	private class ButtonCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	private void buttoncancelPressed() {
		egroup.close();
		lastcon.getAdminCon().setVisible(true);
	
	}
	private class ButtondeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			en=new Envelope(filetodelete,"delete file from interest group");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThiscon() );
		}
	}
	private class ButtonaddfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
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

	public EditGroupCon getThiscon() {
		return thiscon;
	}

	public EditGroupGUI getEgroup() {
		return egroup;
	}
	
	
		

}
