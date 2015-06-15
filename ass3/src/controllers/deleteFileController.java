package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Envelope;
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
	
	public deleteFileController(deleteFile f, fileMenuCon lastCon,file f1)
	{
		this.file1=f1;
		this.currGui=f;
		this.lastCon=lastCon;
		currGui.addOk(new ButtonOkListener());
		currGui.addcancel(new ButtonCancelListener());
			
	}
	class ButtonCancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			currGui.dispose();
			lastCon.getCurrGui().setVisible(true);
		}
	}
	class ButtonOkListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			buttonaddPressed() ;
		}
	}
	
	private void buttonaddPressed() {
		//newgroup= new interestGroups(group.getGroupname().getText(), groupusers);
		en=new Envelope(file1,"delete file");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);
	
	
	}
}
