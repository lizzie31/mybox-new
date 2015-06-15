package controllers;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.file;
import view.*;

public class createNewFileController extends AbstractTransfer{
	/**createfile is create new file window*/
	private createNewFileGUI createfile=null;
	/**prevController is user main menu controller*/
	private userMainMenuController prevController;
	/**f is a file*/
	private file f = null;
	private File f1;
	private String ss;
	private JFileChooser fileChooser;
	private User user;
	
	/**Constructor*/
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon){
		
		this.createfile=g;
		prevController=lastCon;
		createfile.addcancel(new ButtoncancelListener());
		createfile.addOpen(new ButtonOpenListener());
		createfile.addFinish(new ButtonFinishListener());
	}
	/**ButtoncancelListener is a class that implements action listener and goes back to the user main menu window*/
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}	
	}
	private void buttoncancelPressed() {
		createfile.close();
		if (prevController instanceof administratorMenuController)
		((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else prevController.getusermainmenu().setVisible(true);
	}
	
	
	private class ButtonOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ButtonOpenPressed();
		}
	}
	
	private void ButtonOpenPressed() {
		
		JFileChooser fileChooser=new JFileChooser(new File("c:\\"));
		int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            f1=fileChooser.getCurrentDirectory();
			ss=f1.getPath()+"\\"+fileChooser.getSelectedFile().getName();
			createfile.setFile(f1);
		//currGUI.setTextField2(file.getPath());
        } 
	}
	
	
	
	private class ButtonFinishListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ButtonFinishPressed();
		}
	}
	
	private void ButtonFinishPressed() {
		
		if(createfile.getFileNameField().getText().equals(""))
			JOptionPane.showMessageDialog(createfile, "Please type the file name!", "Empty field",0,null);
		else if(createfile.getDescriptionField().getText().equals(""))
			JOptionPane.showMessageDialog(createfile, "Please write the description for this file!", "Empty field",0,null);
		else if(ss.equals(null))
		{
			JOptionPane.showMessageDialog(createfile, "Choose the file to upload", "Error!",0,null);
		}
		else
		{
			if(createfile.getSelectedComboBox()==0)
			{
				createfile.setSelectedComboBox(3);
			}
	
			File oldFile= new File(ss);
			byte [] bArr= new byte [(int)oldFile.length()];
			
			file upFile = new file(createfile.getFileNameField().getText(),ss, createfile.getSelectedComboBox(), user.getUserName());
			upFile.initArray(bArr.length);
			
			Envelope ev = new Envelope(upFile,"Save file in server");
			sendToServer(ev);
			myboxapp.clien.setCurrObj(this);
			
			/*FileInputStream fis= new FileInputStream(oldFile);
			BufferedInputStream bis= new BufferedInputStream(fis);
			
			//file.initArray(bArr.length);
			
			fis.read(bArr);
			fis.close();
			
			String str = oldFile.getName();
			String path1 = "D:\\mybox";
			// where to download the file 
			FileOutputStream fos= new FileOutputStream(new File (path1+"\\"+str));
			fos.write(bArr);
			fos.close();*/
			
			}
		

	}
	
	public void handleDBResultFile(Object message) {
		if(message=="file saved successfully")
		{
			
			JOptionPane.showMessageDialog(createfile, "File added succsesfully!", "Congratulations!",0,null);
			createfile.close();
			if (prevController instanceof administratorMenuController)
			((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
			else prevController.getusermainmenu().setVisible(true);
		
		}
		if(message=="file not saved")
		{
			JOptionPane.showMessageDialog(createfile, "Change file name, and try again!", "Failed!",0,null);
		}
	}
	
	public file getF() {
		return f;
	}
	public void setF(file f) {
		this.f = f;
	}
	
	
}