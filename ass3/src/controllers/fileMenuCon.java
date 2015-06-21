package controllers;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.myboxapp;
import view.UpdateGui;
import view.createNewGroupGUI;
import view.deleteFile;
import view.fileMenuGui;
import view.permissionGui;
import view.setCharacters;
import view.userMainMenuGUI;
import Model.Envelope;
import Model.User;
import Model.file;



public class fileMenuCon extends AbstractTransfer{
	/**user is a specific user*/
	private User user;
	/**currGui is file menu window*/
	private fileMenuGui CurrGui;
	/**prevCon is the user main menu controller*/
	private userMainMenuController prevCon;
	private file ChoosenFile=null;
	public void setChoosenFile(file choosenFile) {
		ChoosenFile = choosenFile;
	}

	/**menu is user main menu window**/
	private userMainMenuGUI menu;
	/**thisCon is the file menu controller*/
	private fileMenuCon thisCon=this;
	/**allFiles is an arrayList of all the files in the DB*/
	private ArrayList<file> allFiles=null;
	
	/**constructor
	 * 
	 * @param menu
	 * @param lastCon
	 * @param user
	 * @param file
	 */
	public fileMenuCon(fileMenuGui menu,userMainMenuController  lastCon,User user, file file) {
		this.user=user;
		this.CurrGui=menu;
		this.prevCon=lastCon;
		this.ChoosenFile=file;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addread(new readListener());
		CurrGui.addupdate(new btnUpdateListener());
		CurrGui.adddelete(new btnDeleteListener());
		CurrGui.addpermission(new btnPermissionListener());
		CurrGui.addsetCharacters(new btnSetCharctersListener());
	}
	class btnSetCharctersListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttoncharactersPressed();
		}
	}
	private void buttoncharactersPressed() {
		if(ChoosenFile.getFileOwner().equals(user.getUserName()))
		{	setCharacters p= new setCharacters();
			new setCharactersController(p,getThisCon(),ChoosenFile,user);
		}
		else{
		if(ChoosenFile.getFilepermission()==3)
			CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
		if(ChoosenFile.getFilepermission()==2)
		{
			int flag=0;
			for(int i=0;i<ChoosenFile.getGroupsForUpdate().size();i++)
			{
				for(int j=0;j<user.getInterestGroupInDB().size();j++)
					if(ChoosenFile.getGroupsForUpdate().get(i).getGroupName().equals(user.getInterestGroupInDB().get(j).getGroupName()))
					{
						flag=1;
						CurrGui.close();
						setCharacters p= new setCharacters();
						new setCharactersController(p,getThisCon(),ChoosenFile,user);
					}
				
			}
			 if(flag==0) CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
		}
		if(ChoosenFile.getFilepermission()==1)
		{
			if(ChoosenFile.getFileOwner().equals(user.getUserName()))
			{
				UpdateGui UG=new UpdateGui(user,ChoosenFile);
			    new UpdateCon(user,ChoosenFile,UG,getThisCon());
			}
			else CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
		}
		}
	}

	class btnPermissionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		if(ChoosenFile.getFileOwner().equals(user.getUserName()))
		{
			CurrGui.dispose();
			permissionGui p= new permissionGui();
			new permissionController(p,getThisCon(),user,ChoosenFile);
		}
		else	
			CurrGui.setWarningMessageVisibleTrue("you don't have permission!");
	
		}
	}
	class btnDeleteListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				CurrGui.dispose();
				deleteFile d= new deleteFile();
				new deleteFileController(d,getThisCon(),getChoosenFile());	
		}
	}
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getusermainmenu().setVisible(true);
		}
	}
	
	class btnUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttonUpdatePressed();
		}

		private void buttonUpdatePressed() {
			if(ChoosenFile.getFileOwner().equals(user.getUserName()))
			{
				UpdateGui UG=new UpdateGui(user,ChoosenFile);
			    new UpdateCon(user,ChoosenFile,UG,getThisCon());
			}
			else{
			if(ChoosenFile.getFilepermission()==3)
				CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
			if(ChoosenFile.getFilepermission()==2)
			{
				int flag=0;
				for(int i=0;i<ChoosenFile.getGroupsForUpdate().size();i++)
				{
					for(int j=0;j<user.getInterestGroupInDB().size();j++)
						if(ChoosenFile.getGroupsForUpdate().get(i).getGroupName().equals(user.getInterestGroupInDB().get(j).getGroupName()))
						{
							flag=1;
							CurrGui.close();
							UpdateGui UG=new UpdateGui(user,ChoosenFile);
							new UpdateCon(user,ChoosenFile,UG,getThisCon());
						}
					
				}
				 if(flag==0) CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
			}
			if(ChoosenFile.getFilepermission()==1)
			{
				if(ChoosenFile.getFileOwner().equals(user.getUserName()))
				{
					UpdateGui UG=new UpdateGui(user,ChoosenFile);
				    new UpdateCon(user,ChoosenFile,UG,getThisCon());
				}
				else CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
			}
			}
		}
	}
	
	class readListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int flag=0;
			if(ChoosenFile.getFileOwner().equals(user.getUserName()))
				flag=1;
			if(ChoosenFile.getFilepermission()==2)
			{
				for(int i=0;i<ChoosenFile.getGroupsForRead().size();i++)
				{
					for(int j=0;j<user.getInterestGroupInDB().size();j++)
						if(ChoosenFile.getGroupsForRead().get(i).getGroupName().equals(user.getInterestGroupInDB().get(j).getGroupName()))
						{
							flag=1;
							CurrGui.close();
							prevCon.getusermainmenu().setVisible(true);
						}
					
				}
			}
			if(ChoosenFile.getFilepermission()==3)
				flag=1;
			if(flag==1)
			{
			
			boolean check = new File(ChoosenFile.getDirection()).exists();
			if(check)
			{
				Desktop desktop=null;
				desktop=Desktop.getDesktop();
				try {
					desktop.open(new File(ChoosenFile.getDirection()));
				} catch (IOException e1) {
	                e1.printStackTrace();
				}
			}
			else
			{
			    Envelope en=new Envelope(ChoosenFile.getDirection(),"open file");
			    sendToServer(en);
			    myboxapp.clien.setCurrObj(getThisCon());
			}
			}
			else CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to read this file.");
		}
	
	}
			
			
	
     	public void handleDBResultFile(byte[] bs1) throws IOException
	 
		{
			byte[] bs=bs1;
			File f=new File(ChoosenFile.getDirection());
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
     	public void handleDBResultFile2(Object message) {
    		if(message.equals("updated successfully"))
    		{
    			Component frame = null;
    			JOptionPane.showMessageDialog(frame, "updated successfully");
    			CurrGui.setVisible(true);
    			//CurrGui.dispose();
    			//prevCon.getCurrGui().setVisible(true);
    		}
    		
    		else 
    		{
    			JOptionPane.showMessageDialog(CurrGui, "error");
    			CurrGui.dispose();
    		//	prevCon.getCurrGui().setVisible(true);
    		}
    		
    	}

		public fileMenuCon getThisCon() {
			return this.thisCon;
		}
		public file getChoosenFile()
		{
			return this.ChoosenFile;
		}

		public fileMenuGui getCurrGui() {
			return CurrGui;
		}

		public void setCurrGui(fileMenuGui currGui) {
			CurrGui = currGui;
		}
		
		
}
			






