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
import Model.file;
import Model.interestGroups;

public class userMainMenuController extends AbstractTransfer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected userMainMenuGUI CurrGui=null;
	protected logInCon prevController;
	protected User userDetails;
	protected fileMenuGui fileMenu;
    protected fileMenuCon fileCon;
	private Envelope en;
	private file f;
	private ArrayList<file> filesarr;


	public userMainMenuController(userMainMenuGUI menu, logInCon lastCon,User user) {
		this.CurrGui= menu;
		prevController=lastCon;
		userDetails=user;
		CurrGui.addcreatenewfile(new ButtoncreatenewfileListener());
		CurrGui.addcreatenewfolder(new ButtoncreatenewfolderListener());
		CurrGui.addjoingruop(new ButtonAddAGroupListener());
		CurrGui.addshowgruops(new ButtonshowgrouprListener());
		CurrGui.addLogOut(new LogOutListener());
		CurrGui.addtreeSelectionListener(new TreeSelection());
		CurrGui.addsearchfiles(new addsearchfilesListener());
	}

	protected class ButtonshowgrouprListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonshowgroupPressed();
		}	
	}
	
	public class TreeSelection implements TreeSelectionListener{
		public void valueChanged(TreeSelectionEvent e) {
			file file=null;
				    /**Returns the last path element of the selection.
				    This method is useful only when the selection model allows a single selection.*/
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode) CurrGui.gettree().getLastSelectedPathComponent();
				    Object nodeInfo = node.getUserObject();
				    String str = (String) nodeInfo;;
			    for(int i=0;i<userDetails.getuserDirectories().size();i++)
				{
				   for(int j=0;j<userDetails.getuserDirectories().get(i).getfiles().size();j++)
					if(userDetails.getuserDirectories().get(i).getfiles().get(j).getFileName().equals(str))
					{
						file=userDetails.getuserDirectories().get(i).getfiles().get(j);
				        CurrGui.close();			
				        fileMenu=new fileMenuGui(userDetails,str);
				        fileCon=new fileMenuCon(fileMenu,getCon(),userDetails,file);
					}
				}
			 
		   }
		   
		}
		
	

	protected void buttonshowgroupPressed() {	
		CurrGui.close();
		this.setUserDetails(userDetails);
		groupListGUI SG=new groupListGUI (userDetails);
		new GroupsListController(SG,this);
		SG.setVisible(true);	
	}
	
	protected class addsearchfilesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonsearchfilesPressed();
		}	
	}
	protected void buttonsearchfilesPressed() {	
		String str = CurrGui.getTextField();
		if(str.equals(""))
		{
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "Please type the filename to search!");
		}
			
		else
		{
			Envelope en =new Envelope (CurrGui.getTextField(),"search files");
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
		}
			
	}

	 class LogOutListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				UpdateDB(); //update the user to status 0 = logout
				CurrGui.dispose();
				prevController.getLoginG().ClearText();
				prevController.getLoginG().setVisible(true);
			}
	 } 	
	 
	private class ButtoncreatenewfileListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfilePressed();
		}
	}

	private void buttoncreatenewfilePressed() {
		CurrGui.close();
		userMainMenuController lastCon = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createNewFileGUI CNFG=new createNewFileGUI ();
					new createNewFileController(CNFG,lastCon);
					CNFG.setVisible(true);
				} catch (Exception e){e.printStackTrace();}
			}
		});
	}

	private class ButtoncreatenewfolderListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfolderPressed();
		}
	}

	private void buttoncreatenewfolderPressed() {
		CurrGui.close();
		createNewFolderGUI CNFOG=new createNewFolderGUI ();
		new createNewFolderController(CNFOG,this);
	    CNFOG.setVisible(true);
	}

	public userMainMenuGUI getusermainmenu() {
		return CurrGui;
	}
	private class ButtonAddAGroupListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Envelope en=new Envelope(userDetails,"show all interest groups");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getCon());
		}
	}


	public void UpdateDB(){

		en=new Envelope(userDetails,"log out status");
		 sendToServer(en);
		 userDetails.setStatus(0);
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
	
	
	public void handleDBResultFile(Object message) {
		if(message instanceof ArrayList<?>)
		{
			ArrayList a= (ArrayList<?>) message;
			if(a.get(0) instanceof file)
				filesarr= (ArrayList<file>)message;
		}
		
		CurrGui.close();
		fileSearchGui SG=new fileSearchGui (filesarr);
		new fileSearchController(SG,this);
		SG.setVisible(true);
		}
	

public logInCon getPrevController() {
		return prevController;
	}

public void handleDBAllGroupsResult(ArrayList<interestGroups> message) {
	ArrayList<interestGroups> groups=(ArrayList<interestGroups>)message;
	joinGroupGui ALG=new joinGroupGui (userDetails,groups);
	new JoinGroupCon(ALG,this,userDetails);
	ALG.setVisible(true);
}
}

