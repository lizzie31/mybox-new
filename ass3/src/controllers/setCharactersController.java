package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controllers.fileMenuCon.ButtoncancelListener;
import view.createNewGroupGUI;
import view.deleteGroupGUI;
import view.fileMenuGui;
import view.setCharacters;
import Model.Envelope;
import Model.User;
import Model.file;
import Model.interestGroups;

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
		CurrGui.addOkListener(new ButtonOkListener());
	}
	class ButtonOkListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Envelope en;
			String text=CurrGui.getContantFiled();
			String newname=CurrGui.getNameFiled();
			if(newname==null)
				CurrGui.setWarningMessageVisibleTrue("the file name is empty!");
			else{
				if(newname.contains("!")||newname.contains("@")||newname.contains("#")||newname.contains("$")||newname.contains("%"))
					CurrGui.setWarningMessageVisibleTrue("the file name has illigal characters!");
				else
					if(newname.contains("^")||newname.contains("&")||newname.contains("*")||newname.contains("~")||newname.contains("(")||newname.contains(")"))
						CurrGui.setWarningMessageVisibleTrue("the file name has illigal characters!");
					else
						if(newname.contains("[")||newname.contains("]")||newname.contains("\\"))
							CurrGui.setWarningMessageVisibleTrue("the file name has illigal characters!");
						else
						{
			
			
			String enter = " ";
			int lastIndex = 0;
			int count = 0;

			while(lastIndex != -1){
			    lastIndex = text.indexOf(enter,lastIndex);
			    if(lastIndex != -1){
			        count ++;
			        lastIndex ++;
			    }
			}
			if(count<=30)
			{
				en=new Envelope(ChoosenFile,"change description");
				ChoosenFile.setDescription(text);
				ChoosenFile.setnewfilename(newname);
				sendToServer(en);		
		
			}
			else
				CurrGui.setWarningMessageVisibleTrue("the description is too long - max 30 words!");
			}
		}
		}
	}
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		
		}
	}
	/**handleDBResult2(Object message) handles data that comes from the data base*/
	public void handleDBResult(Object message) {
		Envelope en;
		if(message instanceof file)
		{
			if(((file) message).getFileName().equals(ChoosenFile.getFileName()))
			{
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "updated successfuly!");
				en=new Envelope(user,"refresh data");
				sendToServer(en);
				//////////////////////////
			
			}
		}
		
		}
	public void refreshUserData(User u)
	{
		this.user=u;
		CurrGui.dispose();
		prevCon.getCurrGui().setVisible(true);
		
	}

}
