package controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.myboxapp;
import view.AddToClientFilesGui;
import view.InterestGroupGui;
import view.UpdateGui;
import Model.Envelope;
import Model.User;
import Model.file;
import Model.interestGroups;

/** this class is the controller of the interest groups window*/
public class InterestGroupCon extends AbstractTransfer {
	/**user*/
	private User user;
	/**group information*/
	private interestGroups groupInformation;
	/**interest group gui CurrGui*/
	private InterestGroupGui CurrGui;
	/**prev controller*/
	private GroupsListCon prevCon;
	/**the selected file name*/
	private String selectedfile;
	private InterestGroupCon thisCon=this;
	private file choosenFile=null;
	
	/**constructor
	 * 
	 * @param user
	 * @param groupdetails
	 * @param prev 
	 */
	
	public InterestGroupCon(InterestGroupGui CurrG,User user,interestGroups groupdetails, GroupsListCon prev)
	{
		this.user=user;
		this.groupInformation=groupdetails;
		this.CurrGui=CurrG;
		this.prevCon=prev;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addListActionListener(new listSelecTionListen());
		CurrGui.addOpen(new ButtonOpenListener());
		CurrGui.addupdate(new ButtonupdateListener());
		CurrGui.addtomefiles(new myfilelistener());
	}
	
	
	
	
	/**handles the result that comes from the db for files*/
 	public void handleDBResultFile(byte[] bs1) throws IOException
	 
	{
		byte[] bs=bs1;
		File f=new File(choosenFile.getDirection());
		BufferedWriter writer=new BufferedWriter(new FileWriter(f));
		FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
		fos.write(bs);
		fos.flush();
		fos.close();
		Desktop desktop=null;
		desktop=Desktop.getDesktop();
		try {
			desktop.open(f);
		} catch (IOException e1) {
            e1.printStackTrace();
		}
		
	}
	
	
	/**************************************************action listeners**********************************************************/
	//cancel action listener
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CurrGui.close();
			prevCon.getGroupListGui().setVisible(true);
		}
	}
	
	private class myfilelistener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addmyfilesPressed();
			
		}
	}
	
	public void addmyfilesPressed()
	{
		for(int i=0;i<groupInformation.getFilesForRead().size();i++)
		{
			if(groupInformation.getFilesForRead().get(i).getFileName().equals(selectedfile))
			{
				setChoosenFile(groupInformation.getFilesForRead().get(i));
			}
		}
	  CurrGui.close();
	  AddToClientFilesGui ATM=new AddToClientFilesGui(user);
	  new AddToClientFilesCon(ATM,this,user,choosenFile,1);
	}
	
	
	private class ButtonupdateListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			    if(getGroupInformation().getFilesForUpdate().size()==0)
			    {
			    	CurrGui.setWarningMessageVisibleTrue("sorry,this group cant update this file!");
			    }
				for(int i=0;i<getGroupInformation().getFilesForUpdate().size();i++)
				{
					if(getGroupInformation().getFilesForUpdate().get(i).getFileName().equals(selectedfile))
					{
						CurrGui.close();
					    UpdateGui UG=new UpdateGui(user,getGroupInformation().getFilesForUpdate().get(i));
					    new UpdateCon(user,getGroupInformation().getFilesForUpdate().get(i),UG,getThisCon());
					}
					else
					{
						CurrGui.setWarningMessageVisibleTrue("sorry,this group cant update this file!");
					}
			    }
			
		}
	}
	
	private class ButtonOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<groupInformation.getFilesForRead().size();i++)
				if(groupInformation.getFilesForRead().get(i).getFileName().equals(selectedfile))
				{
					setChoosenFile(groupInformation.getFilesForRead().get(i));
					boolean check = new File(choosenFile.getDirection()).exists();
					if(check)
					{
						Desktop desktop=null;
						desktop=Desktop.getDesktop();
						try {
							desktop.open(new File(choosenFile.getDirection()));
						} catch (IOException e1) {
			                e1.printStackTrace();
						}
					}
					else
					{
					    Envelope en=new Envelope(choosenFile.getDirection(),"open file");
					    sendToServer(en);
					    myboxapp.clien.setCurrObj(getThisCon());
					}

				}
		}
		
	}
	
	//list selection listener
	private class listSelecTionListen implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent arg0) {
			selectedfile=(String) (CurrGui.getList1().getSelectedValue());
			
	   }
	}


	/**************************************************getters and setters***********************************************************/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public interestGroups getGroupInformation() {
		return groupInformation;
	}

	public void setGroupInformation(interestGroups groupInformation) {
		this.groupInformation = groupInformation;
	}

	public InterestGroupCon getThisCon() {
		return thisCon;
	}

	public void setThisCon(InterestGroupCon thisCon) {
		this.thisCon = thisCon;
	}





	public InterestGroupGui getCurrGui() {
		return CurrGui;
	}





	public void setCurrGui(InterestGroupGui currGui) {
		CurrGui = currGui;
	}





	public file getChoosenFile() {
		return choosenFile;
	}


	public void setChoosenFile(file choosenFile) {
		this.choosenFile = choosenFile;
	}	
	
	
	
	
	

}
