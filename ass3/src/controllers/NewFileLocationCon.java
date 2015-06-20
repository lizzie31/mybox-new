package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import view.NewFileLocationGui;
import view.createNewFolderGUI;
import view.userMainMenuGUI;
import Model.Envelope;
import Model.User;
import Model.directories;
import client.myboxapp;
import controllers.createNewFolderController.TreeSelection;

public class NewFileLocationCon {

	
	/**createfile is create new folder window*/
	private NewFileLocationGui CurrGui=null;
	/**prevController is user menu controller*/
	private createNewFileController prevController=null;
	private User user=null;
	private directories parent=null;
	private String str=null;
	private NewFileLocationCon CurrCon=this;
	
	/**Constructor
	 * 
	 * @param g
	 * @param lastCon
	 * @param u
	 */
	public NewFileLocationCon(NewFileLocationGui g ,createNewFileController lastCon,User u){
		
		this.CurrGui=g;
		this.user=u;
		this.prevController=lastCon;
		CurrGui.addbtnCancel(new ButtonCancelListener());
		CurrGui.addOk(new ButtonOKListener());
		CurrGui.addtreeSelectionListener(new TreeSelection());
	}

	/**ButtoncancelListener is a class that implements action listener and opens user main menu window*/
	private class ButtonCancelListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		CurrGui.close();
		prevController.getCreatefile().setVisible(true);
		
	}
	
	private class ButtonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonOKPressed();
			
		}
	}
	private void buttonOKPressed() {
		 if(str==null)
			 CurrGui.setWarningMessageVisibleTrue("please select the location!!");
		else{
		  CurrGui.close();
		  prevController.setParent(parent);
		  prevController.getCreatefile().setVisible(true);
		}
		
	}
		
		/**button listener of the tree
		 * @param file is a specific file*/
		public class TreeSelection implements TreeSelectionListener{
			public void valueChanged(TreeSelectionEvent e) {
			     /**Returns the last path element of the selection.
			    This method is useful only when the selection model allows a single selection.*/
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) CurrGui.gettree().getLastSelectedPathComponent();
				Object nodeInfo = node.getUserObject();
				str = (String) nodeInfo;;
			    Object rootInfo = CurrGui.getRoot().getUserObject();
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
	        if (dire.getfiles().size()>0)
	        for (int i = 0; i < dire.getfiles().size(); i++) {  
	        	if(dire.getfiles().get(i) instanceof directories)
	        	{
	            temp = findInTree((directories)(dire.getfiles().get(i)),Str);
	            if(temp!=null)
	                return temp;
	        	}
	        }
	        return null;
	 }


	public NewFileLocationCon getCurrCon() {
		return CurrCon;
	}


	public void setCurrCon(NewFileLocationCon currCon) {
		CurrCon = currCon;
	}
	
	
	public void RefreshUserData(User u)
	{
		this.user=u;
		JOptionPane.showMessageDialog(null,"the folder added succesfully!");
        CurrGui.close();
		userMainMenuGUI menu=new userMainMenuGUI(user);
		new userMainMenuController(menu,user); 
	}
	
	}

