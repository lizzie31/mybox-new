package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

import controllers.fileMenuCon.ButtoncancelListener;
import controllers.fileMenuCon.btnUpdateListener;
import view.UpdateGui;
import Model.Envelope;
import Model.User;
import Model.file;

public class UpdateCon extends AbstractTransfer{
	
	private User user;
	private file fileDetails;
	private UpdateGui CurrGui;

	private fileMenuCon prevCon;
	private byte[] content=null;
	
	public UpdateCon(User u,file f,UpdateGui currGui, fileMenuCon prevCon){
		this.user=u;
		this.fileDetails=f;
		this.CurrGui=currGui;
		this.prevCon=prevCon;
		CurrGui.addbtnChooser(new btnChooserListener());
		CurrGui.addbtnUpdate(new btnUpdateListener());
		CurrGui.addbtnCancel(new btnCancelListener());
	}
	
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
	
	class btnCancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				CurrGui.dispose();
				prevCon.getCurrGui().setVisible(true);
		}
	}
	class btnUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			fileDetails.setFileContent(content);
			Envelope en=new Envelope(fileDetails,"update file");
		    sendToServer(en);
        }

	}
	
	
	/***********************************************getters and setters**********************************************************/
	public UpdateGui getCurrGui() {
		return CurrGui;
	}
	public void setCurrGui(UpdateGui currGui) {
		CurrGui = currGui;
	}
}