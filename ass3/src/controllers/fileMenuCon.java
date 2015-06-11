package controllers;

import java.awt.Desktop;
import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import view.fileMenuGui;
import view.userMainMenuGUI;
import Model.Envelope;
import Model.User;
import Model.file;



public class fileMenuCon extends AbstractTransfer{

	private User user;
	private fileMenuGui CurrGui;
	private userMainMenuController prevCon;
	private file ChoosenFile=null;
	private userMainMenuGUI menu;
	
	public fileMenuCon(fileMenuGui menu,userMainMenuController  lastCon,User user, file file) {
		this.user=user;
		this.CurrGui=menu;
		this.prevCon=lastCon;
		this.ChoosenFile=file;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addread(new readListener());
	}
	
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getusermainmenu().setVisible(true);
		}
	}
	
	class readListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			    Envelope en=new Envelope(ChoosenFile.getDirection(),"open file");
			    sendToServer(en);
			}
			
			
			
		}

}



