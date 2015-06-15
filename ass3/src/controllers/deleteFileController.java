package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.deleteFile;
import view.deleteGroupGUI;

public class deleteFileController {
	/**currGui is delete file window*/
	private deleteFile currGui;
	/**lastCon is file menu controller*/
	private fileMenuCon lastCon;
	
	public deleteFileController(deleteFile f, fileMenuCon lastCon)
	{
		this.currGui=f;
		this.lastCon=lastCon;
		//currGui.addOk(new ButtonOkListener());
		currGui.addcancel(new ButtonCancelListener());
			
	}
	class ButtonCancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			currGui.dispose();
			lastCon.getCurrGui().setVisible(true);
		}
	}
}
