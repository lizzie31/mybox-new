package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import client.myboxapp;
import controllers.NewFileLocationCon.TreeSelection;
import view.AbstractGui;
import view.AddToClientFilesGui;
import view.NewFileLocationGui;
import view.createNewFolderGUI;
import view.userMainMenuGUI;
import Model.Envelope;
import Model.User;
import Model.directories;
import Model.file;

public class AddToClientFilesCon extends AbstractTransfer{
	
	/**createfile is create new folder window*/
	private AddToClientFilesGui CurrGui=null;
	/**prevController is user menu controller*/
	private InterestGroupCon prevController=null;
	/**prevController2 is file search controller*/
	private fileSearchController prevController2=null;
	private User user=null;
	private directories parent=null;
	private String str=null;
	/***CurrCon is add to client files controller*/
	private AddToClientFilesCon CurrCon=this;
	private file file=null;
	private int flag;
	
	
	/**Constructor
	 * 
	 * @param g
	 * @param lastCon
	 * @param u
	 */
	public AddToClientFilesCon (AddToClientFilesGui  g ,InterestGroupCon lastCon,User u,file f,int flag){
		
		this.CurrGui=g;
		this.user=u;
		this.prevController=lastCon;
		
		this.file=f;
		this.flag=flag;
		CurrGui.addbtnCancel(new ButtonCancelListener());
		CurrGui.addOk(new ButtonOKListener());
		CurrGui.addtreeSelectionListener(new TreeSelection());
	}
	
	public AddToClientFilesCon (AddToClientFilesGui  g ,fileSearchController lastCon,User u,file f,int flag){
		
		this.CurrGui=g;
		this.user=u;
		this.prevController2=lastCon;
		this.file=f;
		this.flag=flag;
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
		if(flag==1)
		   prevController.getCurrGui().setVisible(true);
		if(flag==2)
		   prevController2.getSearchG().setVisible(true);	
	}
	/**ButtonOKListener is a class that implements action listener and opens user main menu window*/
	private class ButtonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttonOKPressed();
			
		}
	}
	private void buttonOKPressed() {
		if(str==null)
			CurrGui.setWarningMessageVisibleTrue("please select the location!!");
		else{
		CurrGui.undisplayWarningMessage();
		file.setParent(parent);
		parent.getfiles().add(file);
		file.setCurrAddingUser(user.getUsreName());
		Envelope en=new Envelope(file,"add file to user");
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
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) CurrGui.gettree().getLastSelectedPathComponent();
				Object nodeInfo = node.getUserObject();
				str = (String) nodeInfo;;
			    Object rootInfo =CurrGui.getRoot().getUserObject();
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

	/***************getters and setters***************/
	public AddToClientFilesCon getCurrCon() {
		return CurrCon;
	}


	public void setCurrCon(AddToClientFilesCon currCon) {
		CurrCon = currCon;
	}


	public AddToClientFilesGui getCurrGui() {
		return CurrGui;
	}


	public void setCurrGui(AddToClientFilesGui currGui) {
		CurrGui = currGui;
	}
	/***********HandleDBresult********/
	public void HandleDBresult()
	{
		
		JOptionPane.showMessageDialog(null,"the file added succesfully!");
		CurrGui.close();
		if(flag==1)
		   prevController.getCurrGui().setVisible(true);
		if(flag==2)
			prevController2.getSearchG().setVisible(true);	
	}
	
	
	
}



