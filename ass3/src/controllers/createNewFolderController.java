package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import client.myboxapp;
import controllers.userMainMenuController.TreeSelection;
import Model.Envelope;
import Model.User;
import Model.directories;
import Model.file;
import view.*;

public class createNewFolderController extends AbstractTransfer{
	

	/**createfolder is create new folder window*/
	private createNewFolderGUI createfolder=null;
	/**prevController is user menu controller*/
	private userMainMenuController prevController=null;
	private User user=null;
	private directories parent=null;
	private String str=null;
	private createNewFolderController CurrCon=this;
	
	/**Constructor
	 * 
	 * @param g
	 * @param lastCon
	 * @param u
	 */
	public createNewFolderController (createNewFolderGUI g ,userMainMenuController lastCon,User u){
		
		this.createfolder=g;
		this.user=u;
		this.prevController=lastCon;
		createfolder.addbtnCancel(new ButtonCancelListener());
		createfolder.addOk(new ButtonOKListener());
		createfolder.addtreeSelectionListener(new TreeSelection());
	}
			/********action listeners*******/
	/**ButtoncancelListener is a class that implements action listener and opens user main menu window*/
	private class ButtonCancelListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		createfolder.close();
		if (prevController instanceof administratorMenuController)
		((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else prevController.getusermainmenu().setVisible(true);
	}
	
	/**ButtonOkListener implements action listener and handles the ok button pressing*/
	private class ButtonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonOKPressed();
			
		}
	}
	private void buttonOKPressed() {
		String foldername=createfolder.getTextField();
		if(foldername.equals(""))//if the folder name is empty
			createfolder.setWarningMessageVisibleTrue("please enter the directory name!!");
		else if(str==null)
			createfolder.setWarningMessageVisibleTrue("please select the location!!");
		else{
		directories dir=new directories(foldername);
		dir.setParent(parent);
		parent.getfiles().add(dir);
		dir.setUsername(user.getUsreName());
		Envelope en=new Envelope(dir,"add directory");
		sendToServer(en);
		en=new Envelope(user,"refresh data");
		sendToServer(en);
		myboxapp.clien.setCurrObj(getCurrCon());
		
		}
		
	}
		
		/**button listener of the tree
		 * @param file is a specific file*/
		public class TreeSelection implements TreeSelectionListener{
			public void valueChanged(TreeSelectionEvent e) {
			     /**Returns the last path element of the selection.
			    This method is useful only when the selection model allows a single selection.*/
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) createfolder.gettree().getLastSelectedPathComponent();
				Object nodeInfo = node.getUserObject();
				str = (String) nodeInfo;;
			    Object rootInfo = createfolder.getRoot().getUserObject();
			    String rootname = (String) rootInfo;
			    if(rootname.equals(str))
				{
			      parent=user.getuserItems();
				  parent.setRootFlag(true);
			    }
			   else
			     parent=findInTree(user.getuserItems(),str) ;
				  
				      
			}
			}
		
	
	private directories findInTree(directories dire,String Str)
	{
		if( dire.getDirectoryName().equals(Str))
			return dire; 
	        directories  temp;
	        if (dire.getfiles().isEmpty()==false)
	        {
	        for (int i = 0; i < dire.getfiles().size(); i++) {  
	        	if(dire.getfiles().get(i) instanceof directories)
	        	{
	            temp = findInTree((directories)(dire.getfiles().get(i)),Str);
	            if(temp!=null)
	                return temp;
	        	}
	        }
	        }
	        return null;
	 }

		/************getters and setters*************/
	public createNewFolderController getCurrCon() {
		return CurrCon;
	}


	public void setCurrCon(createNewFolderController currCon) {
		CurrCon = currCon;
	}
	
	
	public void RefreshUserData(User u)
	{
		this.user=u;
		JOptionPane.showMessageDialog(null,"the folder added succesfully!");
		createfolder.close();
		userMainMenuGUI menu=new userMainMenuGUI(user);
		new userMainMenuController(menu,user); 
	}
	
	}
	




