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
	/**ChoosenFile is the file the user pressed*/
	private file ChoosenFile=null;
	/**user is a specific user*/
	private User user;

	/**constructor
	 * @param CurrGui
	 * @param pervCon
	 * @param ChoosenFile
	 * @param user
	 * */
	public setCharactersController(setCharacters CurrGui,fileMenuCon prevCon,file ChoosenFile,User user)
	{
		this.CurrGui=CurrGui;
		this.prevCon=prevCon;
		this.user=user;
		this.ChoosenFile=ChoosenFile;
		CurrGui.addcancelListener(new ButtoncancelListener());
		CurrGui.addOkListener(new ButtonOkListener());
	}
					/********action listeners*******/
	/**ButtonOkListener implements action listener and handles the ok button pressing*/
	class ButtonOkListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Envelope en;
			String text=CurrGui.getContantFiled();// text saves the contant filed the user entered
			String newname=CurrGui.getNameFiled();// newname is the name filed the user entered
		
			if(newname.equals(" ")||newname.equals("")||newname.isEmpty())
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
			
			/*check if the description is longer than 30 words*/
			String enter = " ";
			int lastIndex = 0;
			int count = 0;//counts the number of spaces in the description

			while(lastIndex != -1){
			    lastIndex = text.indexOf(enter,lastIndex);
			    if(lastIndex != -1){
			        count ++;
			        lastIndex ++;
			    }
			}
			if(count<=30)//update the file characters
			{
				en=new Envelope(ChoosenFile,"change description");
				ChoosenFile.setDescription(text);
				ChoosenFile.setnewfilename(newname);
				sendToServer(en);		
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "updated successfuly!");
				CurrGui.dispose();
				prevCon.getCurrGui().setVisible(true);
		
			}
			else// the description is longer than 30 words. error message
				CurrGui.setWarningMessageVisibleTrue("the description is too long - max 30 words!");
			}
		}
		}
	}
	/**ButtoncancelListener implements action listener and handles cancel listener*/
	class ButtoncancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CurrGui.dispose();
			prevCon.getCurrGui().setVisible(true);
		
		}
	}

}
