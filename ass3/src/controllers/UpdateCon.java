package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import client.myboxapp;
import controllers.fileMenuCon.ButtoncancelListener;
import controllers.fileMenuCon.btnUpdateListener;
import view.UpdateGui;
import Model.Envelope;
import Model.User;
import Model.file;

public class UpdateCon extends AbstractTransfer{
	
	private User user;
	/**fileDetails saves the details of a specific file*/
	private file fileDetails;
	/**CurrGui is the update gui window*/
	private UpdateGui CurrGui;
	/**thisCon is update controller*/
    private UpdateCon thisCon=this;
    /**prevCon is the file menu controller**/
	private fileMenuCon prevCon;
	/**prevCon1 is interest groups controller**/
	private InterestGroupCon prevCon1;
	private byte[] content=null;
	private int flag;
	
	/**constructors*/
	public UpdateCon(User u,file f,UpdateGui currGui, fileMenuCon prevCon){
		this.flag=0;
		this.user=u;
		this.fileDetails=f;
		this.CurrGui=currGui;
		this.prevCon=prevCon;
		CurrGui.addbtnChooser(new btnChooserListener());
		CurrGui.addbtnUpdate(new btnUpdateListener());
		CurrGui.addbtnCancel(new btnCancelListener());
	}
	
	public UpdateCon(User u,file f,UpdateGui currGui, InterestGroupCon prevCon){
		this.flag=1;
		this.user=u;
		this.fileDetails=f;
		this.CurrGui=currGui;
		this.prevCon1=prevCon;
		CurrGui.addbtnChooser(new btnChooserListener());
		CurrGui.addbtnUpdate(new btnUpdateListener());
		CurrGui.addbtnCancel(new btnCancelListener());
	}
	
	/*********************action listeners*******************/
	/**button listener of choose
	 * @param returnVal*/
	class btnChooserListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			   int returnVal = CurrGui.getFileChooser().showOpenDialog(null);

	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File file = CurrGui.getFileChooser().getSelectedFile();
	                try {
						content=Files.readAllBytes(file.toPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	                
		}
		}
	/**button listener of cancel*/
	class btnCancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(flag==0)
			{
				CurrGui.dispose();
				prevCon.getCurrGui().setVisible(true);
			}
			else
			{
				CurrGui.dispose();
				prevCon1.getCurrGui().setVisible(true);
			}
		}
	}
	/**button listener of update*/
	class btnUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Envelope en=new Envelope(fileDetails,"check if someone updating");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getThisCon());
		    
        }

	}
	
	
	/***********************************************getters and setters**********************************************************/
	public UpdateGui getCurrGui() {
		return CurrGui;
	}
	public void setCurrGui(UpdateGui currGui) {
		CurrGui = currGui;
	}
	public UpdateCon getThisCon() {
		return thisCon;
	}
	public void setThisCon(UpdateCon thisCon) {
		this.thisCon = thisCon;
	}

	public void showsuceedmessege() {
 		Component frame=null;
  	    JOptionPane.showMessageDialog(frame, "the file was updated sucssefuly!!");
  	  if(flag==0)
		{
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		}
		else
		{
			CurrGui.dispose();
			prevCon1.getCurrGui().setVisible(true);
		}
		
	}
	
	public void isUpdatedByAnotherUser(file file)
	{
		fileDetails=file;
		if(fileDetails.getUpFlag()==1)
		{
			CurrGui.setWarningMessageVisibleTrue("someone else updating this file, please try again later.");
		}
		else
		{
			buttonUpdatePressed();
		}
	}
	
	
	public void buttonUpdatePressed() {
		fileDetails.setFileContent(content);
		Envelope en=new Envelope(fileDetails,"update file");
	    sendToServer(en);
	    myboxapp.clien.setCurrObj(getThisCon());
	}
	
	
}