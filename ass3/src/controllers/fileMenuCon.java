package controllers;

import java.awt.Desktop;
import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import client.myboxapp;
import view.UpdateGui;
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
	private fileMenuCon thisCon=this;
	
	public fileMenuCon(fileMenuGui menu,userMainMenuController  lastCon,User user, file file) {
		this.user=user;
		this.CurrGui=menu;
		this.prevCon=lastCon;
		this.ChoosenFile=file;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addread(new readListener());
		CurrGui.addupdate(new btnUpdateListener());
	}
	
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getusermainmenu().setVisible(true);
		}
	}
	
	class btnUpdateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			UpdateGui UG=new UpdateGui(user,ChoosenFile);
			new UpdateCon(user,ChoosenFile,UG,getThisCon());
		}
	}
	
	class readListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			boolean check = new File(ChoosenFile.getDirection()).exists();
			if(check)
			{
				Desktop desktop=null;
				desktop=Desktop.getDesktop();
				try {
					desktop.open(new File(ChoosenFile.getDirection()));
				} catch (IOException e1) {
	                e1.printStackTrace();
				}
			}
			else
			{
			    Envelope en=new Envelope(ChoosenFile.getDirection(),"open file");
			    sendToServer(en);
			    myboxapp.clien.setCurrObj(getThisCon());
			}
			}
	}
			
			
	
     	public void handleDBResultFile(byte[] bs1) throws IOException
	 
		{
			byte[] bs=bs1;
			File f=new File(ChoosenFile.getDirection());
			BufferedWriter writer=new BufferedWriter(new FileWriter(f));
			FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
			fos.write(bs);
			fos.flush();
			fos.close();
			Desktop desktop=null;
			desktop=Desktop.getDesktop();
			try {
				desktop.open(new File(f.getAbsolutePath()));
			} catch (IOException e1) {
                e1.printStackTrace();
			}
			
		}
		
		
		

		public fileMenuCon getThisCon() {
			return thisCon;
		}
}
			






