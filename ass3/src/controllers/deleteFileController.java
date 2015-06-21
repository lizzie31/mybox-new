package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controllers.JoinGroupCon.SelectedGroupListener;
import Model.Envelope;
import Model.User;
import Model.file;
import Model.interestGroups;
import client.myboxapp;
import view.deleteFile;
import view.deleteGroupGUI;

public class deleteFileController extends AbstractTransfer{
	/**currGui is delete file window*/
	private deleteFile currGui;
	/**lastCon is file menu controller*/
	private fileMenuCon lastCon;
	private file file1;
	private Envelope en=null;
	/**1 is no 2 is yes 0 is didn't choose*/
	private int decision=1;
	private User user;
	
	public deleteFileController(deleteFile f, fileMenuCon lastCon,file f1,User u)
	{
		this.user=u;
		this.file1=f1;
		this.currGui=f;
		this.lastCon=lastCon;
		currGui.addOk(new ButtonOkListener());
		currGui.addcancel(new ButtonCancelListener());
		currGui.addSelectDelete(new SelectedDeleteListener());
			
	}
	/**button listener of selecting a delete option*/
	public class SelectedDeleteListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			String temp=(String)currGui.getComboBox().getSelectedItem();
		
			if(temp.equals("no"))
				decision=1;
			else
			{
				if(temp.equals("yes"))
					decision=2;
				else
					decision=0;
			}	
		}
	}
	class ButtonCancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			currGui.dispose();
			lastCon.getCurrGui().setVisible(true);
		}
	}
	class ButtonOkListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttonyesPressed() ;
		}
	}

	
	private void buttonyesPressed() {
		if(file1.getFileOwner().equals(user.getUserName()))
		{
			if(decision==0)
				currGui.setWarningMessageVisibleTrue("please choose yes or no");
			if(decision==1)
			{
				file1.setuserNotOwnerDeleteFile(user.getUserName());
				en=new Envelope(file1,"delete file owner");
				sendToServer(en);
				myboxapp.clien.setCurrObj(this);
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "deleted successfuly!");
				currGui.dispose();
				lastCon.getCurrGui().setVisible(true);
			}
			if(decision==2)
			{
				file1.setuserNotOwnerDeleteFile(user.getUserName());
				en=new Envelope(file1,"delete file permanantly");
				sendToServer(en);
				myboxapp.clien.setCurrObj(this);
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "deleted successfuly!");
				currGui.dispose();
				lastCon.getCurrGui().setVisible(true);
			}
				
		}
	else{
			file1.setuserNotOwnerDeleteFile(user.getUserName());
			en=new Envelope(file1,"delete file not owner");
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "deleted successfuly!");
			currGui.dispose();
			lastCon.getCurrGui().setVisible(true);
		}

	}
	/*public void handleDBResult(Object message) {
		 if((file)message instanceof file)
		 {
			 Component frame = null;
				JOptionPane.showMessageDialog(frame, "deleted successfuly!");
				currGui.dispose();
				lastCon.getCurrGui().setVisible(true);
		 }
	}*/
}
