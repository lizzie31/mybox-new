package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.fileMenuCon.ButtoncancelListener;
import view.fileMenuGui;
import view.setCharacters;
import Model.User;
import Model.file;

public class setCharactersController extends AbstractTransfer{
	/**currGui is file menu window*/
	private setCharacters CurrGui;
	/**prevCon is the user main menu controller*/
	private fileMenuCon prevCon;
	private file ChoosenFile=null;
	/**user is a specific user*/
	private User user;
	
	public setCharactersController(setCharacters CurrGui,fileMenuCon prevCon,file ChoosenFile,User user)
	{
		this.CurrGui=CurrGui;
		this.prevCon=prevCon;
		this.user=user;
		this.ChoosenFile=ChoosenFile;
		CurrGui.addcancelListener(new ButtoncancelListener());
	}
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		
		}
	}

}
