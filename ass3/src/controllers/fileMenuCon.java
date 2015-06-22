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
	/**ChossenFile is a file that the user chose**/
	private file ChoosenFile=null;
	private boolean updateFlag;
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
		/********action listeners*******/
	/**btnSetCharctersListener implements action listener and handles the setcharacters button pressing*/
	class btnSetCharctersListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttoncharactersPressed();
		}
	}
	private void buttoncharactersPressed() {
		 if(ChoosenFile.getFileName().contains("deleted"))
		  {
		  	CurrGui.setWarningMessageVisibleTrue("this file was deleted by its owner,please delete it from your files.");
		  }
		 {
		if(ChoosenFile.getFileOwner().equals(user.getUserName()))//if the user is the file owner
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
	}
/**btnPermissionListener implements action listener and handles setPermission button pressed*/
	class btnPermissionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		 if(ChoosenFile.getFileName().contains("deleted"))
		 {
		 	CurrGui.setWarningMessageVisibleTrue("this file was deleted by its owner,please delete it from your files.");
	     }	
		 else{
			
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
	}
		/**btnDeleteListener implements action listener and handles delete file button pressed*/
	class btnDeleteListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				CurrGui.dispose();
					deleteFile d= new deleteFile(user,ChoosenFile);
				new deleteFileController(d,getThisCon(),getChoosenFile(),user);		
		}
	}
	
		/**ButtoncancelListener implements action listener and handles cancel button pressed*/
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Envelope en=new Envelope(user,"refresh data");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThisCon());
			
		}
	}
	/**btnUpdateListener implements action listener and handles setContant button pressed*/
	class btnUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			buttonUpdatePressed();

		}

		private void buttonUpdatePressed() {
			
			 if(ChoosenFile.getFileName().contains("deleted"))
			  {
			  	CurrGui.setWarningMessageVisibleTrue("this file was deleted by its owner,please delete it from your files.");
			  }
			 if(isUpdateFlag())
			 {
				 CurrGui.setWarningMessageVisibleTrue("this file was updated by another user, please try later");
			 }
			 else if (!isUpdateFlag())
			 {
				 if(ChoosenFile.getFileOwner().equals(user.getUserName()))
				 {
					 CurrGui.close();
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
							 CurrGui.close();
							 UpdateGui UG=new UpdateGui(user,ChoosenFile);
							 new UpdateCon(user,ChoosenFile,UG,getThisCon());
						 }
						 else CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to update this file.");
					 }
				 }
			 }
		}
	}
	/**readListener implements action listener and handles read file button pressed*/	
	class readListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 if(ChoosenFile.getFileName().contains("deleted"))
			  {
			  	CurrGui.setWarningMessageVisibleTrue("this file was deleted by its owner,please delete it from your files.");
			  }
			 else{
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
		
						}
					
				}
			}
			
			if(ChoosenFile.getFilepermission()==3)
				flag=1;
			if(flag==1)
			{
			    Envelope en=new Envelope(ChoosenFile.getDirection(),"open file");
			    sendToServer(en);
			    myboxapp.clien.setCurrObj(getThisCon());
			}
			else CurrGui.setWarningMessageVisibleTrue("sorry,you don't have permission to read this file.");
		}
		}
	
	}

/**handles db result*/	
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
    		}
    		
    		else 
    		{
    			JOptionPane.showMessageDialog(CurrGui, "error");
    			CurrGui.dispose();
    		}
    		
    	}

     	/********************getters and setters*******************/
    	public void setChoosenFile(file choosenFile) {
    		ChoosenFile = choosenFile;
    	}
    	
    	public boolean isUpdateFlag() {
    		return updateFlag;
    	}
    	public void setUpdateFlag(boolean updateFlag) {
    		this.updateFlag = updateFlag;
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
		/*******refresh data**********/
		public void RefreshUserData(User userrefresh) {
			user=userrefresh;
			CurrGui.close();
			userMainMenuGUI menu=new userMainMenuGUI(user);
			new userMainMenuController(menu,user);
			
		}
		
}
			






