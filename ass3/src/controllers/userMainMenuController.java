package controllers;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;






import java.awt.EventQueue;

import view.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.directories;
import Model.file;
import Model.interestGroups;
import Model.logInMod;

public class userMainMenuController extends AbstractTransfer{

	private static final long serialVersionUID = 1L;
	/**CurrGui is user main menu gui */
	protected userMainMenuGUI CurrGui=null;
	/**prevController is log in controller*/
	protected logInCon prevController;
	/**userDetails saves the details of specific user*/
	protected User userDetails;
	/**fileMenu is file menu gui */
	protected fileMenuGui fileMenu;
	/**fileCon is file menu controller*/
    protected fileMenuCon fileCon;
	
	private Envelope en;
	/**f is a specific file*/
	private file f;
	/**filesarr is an array list of files*/
	private ArrayList<file> filesarr;

/**constructor
 * 
 */
	public userMainMenuController(userMainMenuGUI menu, logInCon lastCon,User user) {
		this.CurrGui= menu;
		prevController=lastCon;
		userDetails=user;
	
		CurrGui.addcreatenewfolder(new ButtoncreatenewfolderListener());
		CurrGui.addjoingruop(new ButtonAddAGroupListener());
		CurrGui.addshowgruops(new ButtonshowgrouprListener());
		CurrGui.addLogOut(new LogOutListener());
		CurrGui.addtreeSelectionListener(new TreeSelection());
		CurrGui.addsearchfiles(new addsearchfilesListener());
		CurrGui.addleavegruop(new ButtonleaveListene());
		CurrGui.addcreatenewfile(new ButtoncreatenewfileListener());
		CurrGui.addbtnRestoreFiles(new ButtonrestorefileListener());
	}
	
	public userMainMenuController(userMainMenuGUI menu, User user) {
		this.CurrGui= menu;
		userDetails=user;
		CurrGui.addcreatenewfile(new ButtoncreatenewfileListener());
		CurrGui.addcreatenewfolder(new ButtoncreatenewfolderListener());
		CurrGui.addjoingruop(new ButtonAddAGroupListener());
		CurrGui.addshowgruops(new ButtonshowgrouprListener());
		CurrGui.addLogOut(new LogOutListener());
		CurrGui.addtreeSelectionListener(new TreeSelection());
		CurrGui.addsearchfiles(new addsearchfilesListener());
		CurrGui.addleavegruop(new ButtonleaveListene());
		CurrGui.addbtnRestoreFiles(new ButtonrestorefileListener());
	
}


	/*************************************************action listeners**********************************************************/
	/**button listener of leave*/
	public class ButtonleaveListene implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonleavePressed();
		}	
	}
	
	protected void buttonleavePressed() {	
	
		
		CurrGui.close();
		this.setUserDetails(userDetails);
		leavegroupGUI LG=new leavegroupGUI(userDetails);
		new leavegroupcontroller(LG,this,userDetails);
		LG.setVisible(true);	
	
	}

	/**button listener of show groups*/
	protected class ButtonshowgrouprListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonshowgroupPressed();
		}	
	}
	
	protected void buttonshowgroupPressed() {	
		CurrGui.close();
		this.setUserDetails(userDetails);
		groupListGUI SG=new groupListGUI (userDetails);
		new GroupListController(SG,this,userDetails);
		SG.setVisible(true);	
	}
	/**button listener of add group*/
	private class ButtonAddAGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CurrGui.undisplayWarningMessage();
			Envelope en=new Envelope(userDetails,"show all interest groups");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getCon());
		}
	}
	/**button listener of the tree
	 * @param file is a specific file*/
	public class TreeSelection implements TreeSelectionListener{
		public void valueChanged(TreeSelectionEvent e) {
			file file=null;
			directories dir=null;
				    /**Returns the last path element of the selection.
				    This method is useful only when the selection model allows a single selection.*/
				   DefaultMutableTreeNode node = (DefaultMutableTreeNode) CurrGui.gettree().getLastSelectedPathComponent();
				    Object nodeInfo = node.getUserObject();
				    String str = (String) nodeInfo;;
			    for(int i=0;i<userDetails.getuserItems().getfiles().size();i++)
				{
			      if(userDetails.getuserItems().getfiles().get(i) instanceof directories)
			       {
			    	  dir=((directories)(userDetails.getuserItems().getfiles().get(i)));
			    	  findInTree(dir,str);
			       }
			      else
			       {
					   if(((file)(userDetails.getuserItems().getfiles().get(i))).getFileName().equals(str))
				    	{
						  file=(file)dir.getfiles().get(i);
				           CurrGui.close();			
				           fileMenu=new fileMenuGui(userDetails,str);
				           fileCon=new fileMenuCon(fileMenu,getCon(),userDetails,file);
					      
				    	}
				    }
		}
	}
			 
		   
//this function finds the preesed file in the tree
		private void findInTree(directories dir,String Str)
		{
		 String filename=Str;	
		 for(int i=0;i<dir.getfiles().size();i++)
			{
			 if(dir.getfiles().get(i) instanceof directories)
		    	{
				  findInTree((directories)dir.getfiles().get(i),filename);
		    	}
			 else if(((file)(dir.getfiles().get(i))).getFileName().equals(filename))
			 {
				 file file=(file)dir.getfiles().get(i);
			      CurrGui.close();			
			      fileMenu=new fileMenuGui(userDetails,filename);
			      fileCon=new fileMenuCon(fileMenu,getCon(),userDetails,file);
				 
			}
			    	
		}
		}

		   
		}
		
		
	/**button listener of search file*/
	protected class addsearchfilesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonsearchfilesPressed();
		}	
	}
	protected void buttonsearchfilesPressed() {	
		String str =CurrGui.getTextField();
		if(str.equals(""))
			CurrGui.setWarningMessageVisibleTrue("Please type the filename to search!");	
		else
		{
			CurrGui.undisplayWarningMessage();
			Envelope en =new Envelope (str,"search files");
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
		}
			
	}
	/**button listener of log out*/
	 class LogOutListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				UpdateDB(); //update the user to status 0 = logout
				CurrGui.undisplayWarningMessage();
				CurrGui.dispose();
				logInGui login=new logInGui();
				logInMod loginm=new logInMod();
				new logInCon(login,loginm);
			}
	 } 	
		/**button listener of create new file*/
	private class ButtoncreatenewfileListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfilePressed();
		}
	}

	private void buttoncreatenewfilePressed() {
		CurrGui.undisplayWarningMessage();
		CurrGui.close();
		userMainMenuController lastCon = this;
		createNewFileGUI CNFG=new createNewFileGUI ();
		new createNewFileController(CNFG,lastCon,userDetails);
		CNFG.setVisible(true);
	
		
	}
	/**button listener of create new folder*/
	private class ButtoncreatenewfolderListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfolderPressed();
		}
	}

	private void buttoncreatenewfolderPressed() {
		CurrGui.undisplayWarningMessage();
		CurrGui.close();
		createNewFolderGUI CNFOG=new createNewFolderGUI (userDetails);
		new createNewFolderController(CNFOG,this,userDetails);
	    CNFOG.setVisible(true);
	}
	
	private class ButtonrestorefileListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ButtonrestorefilefPressed();
		}
	}
	
	public void ButtonrestorefilefPressed()
	{
		Envelope en=new Envelope(userDetails,"files to restore");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
	}
	
	
	
	/**UpdateDB is setting the status of a user as 0 - logged out*/
	public void UpdateDB(){
		
		en=new Envelope(userDetails,"log out status");
		 sendToServer(en);
		 userDetails.setStatus(0);
		 
	}
	/**handleDBResultFile handles results from the DB*/
	public void handleDBResultFile(Object message) {
		if(message==null)
			CurrGui.setWarningMessageVisibleTrue("there are no files with this name. try again.");
		if(message instanceof ArrayList<?>)
		{
			ArrayList a= (ArrayList<?>) message;
			if(a.get(0) instanceof file)
		    filesarr= (ArrayList<file>)message;
			CurrGui.undisplayWarningMessage();
			CurrGui.close();
			fileSearchGui SG=new fileSearchGui (filesarr);
			new fileSearchController(SG,this,userDetails,filesarr);
			SG.setVisible(true);
		}
	}
	/**handleDBAllGroupsResult handles results from the DB*/
	public void handleDBAllGroupsResult(ArrayList<interestGroups> message) {
		ArrayList<interestGroups> groups=(ArrayList<interestGroups>)message;
		joinGroupGui ALG=new joinGroupGui (userDetails,groups);
		new JoinGroupCon(ALG,this,userDetails);
		ALG.setVisible(true);
	}
	
	/**********getters and setters*******/
	public userMainMenuGUI getusermainmenu() {
		return CurrGui;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	
public userMainMenuController getCon(){
		return this;
	}
	
public logInCon getPrevController() {
		return prevController;
	}

public void HandleRestoreFilesResult(ArrayList<file> filesToRestore) {
	
	CurrGui.close();
	RestoreFilesGui RF=new RestoreFilesGui(filesToRestore);
	new RestoreFileCon(RF,this,userDetails,filesToRestore);
	
}

}


