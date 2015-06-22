
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.file;
import Model.Envelope;
import view.*;

public class fileSearchController extends AbstractTransfer {
	/**searchG is a file search gui window*/
	private fileSearchGui searchG=null;
	/**prevController is user main menu controller*/
	private userMainMenuController prevController;
	/**fileDetails is a file that gives us information about specific file*/
	private file fileDetails;
	private String str;
	private Envelope en;
	/***searchresults is array list of files*/
	private ArrayList<file> searchresults;
	/**SelectedFile is the name of the file the user selected**/
	private String SelectedFile=null;
	private file file=null;
	private User user=null;
	
	/**constructor
	 * @param filesarr */
	public fileSearchController (fileSearchGui g , userMainMenuController lastCon,User u, ArrayList<file> filesarr){
		
		this.searchG=g;
		prevController=lastCon;
		this.searchresults=filesarr;
		this.user=u;
		searchG.addcancel(new ButtoncancelListener());
		searchG.addBtnDescription(new btnDescriptionListener());
		searchG.addBtnAddToMyFiles(new btnAddToMyFilesListenet());
		searchG.addListActionListener(new listSelecTionListen());
	}
	/*********************action listiners************************/
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	/**Handling button cancel action performed*/
	private void buttoncancelPressed() {
		if (prevController instanceof administratorMenuController)
			((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else
		{
		Envelope en=new Envelope(user,"refresh data");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
		}
	}
	
	private class btnDescriptionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnDescriptionPressed();
		}
		
	}
	private void btnDescriptionPressed() {
		new DescriptionGui(file,this);	
	}
	
	
	private class btnAddToMyFilesListenet implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnAddToMyFilesPressed();
		}
		
	}
	
	private void btnAddToMyFilesPressed() {
		
		AddToClientFilesGui ATC=new AddToClientFilesGui(user);
		new AddToClientFilesCon(ATC,this,user,file,2);
	
	}
	
	private class listSelecTionListen implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent arg0) {
			SelectedFile=(String) (searchG.getList_1().getSelectedValue());
			for(int i=0;i<searchresults.size();i++)
			{
				if(SelectedFile.equals(searchresults.get(i).getFileName()))
				{
					file=searchresults.get(i);
				}
			}
			
	   }
	}
	/***********getters and setters************/
	public file getFileDetails() {
		return fileDetails;
	}
	
	public void setFileDetails(file fileDetails) {

		this.fileDetails = fileDetails;
	}

	public fileSearchGui getSearchG() {
		return searchG;
	}

	public void setSearchG(fileSearchGui searchG) {
		this.searchG = searchG;
	}
	
	/*********************refresh data***************************/
	public void RefreshUserData(User userrefresh) {
		user=userrefresh;
		searchG.close();
		userMainMenuGUI menu=new userMainMenuGUI(user);
		new userMainMenuController(menu,user);
		
	}

}