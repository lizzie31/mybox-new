package controllers;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controllers.JoinGroupCon.SelectedGroupListener;
import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.file;
import view.*;

public class createNewFileController extends AbstractTransfer{
	/**createfile is create new file window*/
	private createNewFileGUI createfile=null;
	public createNewFileGUI getCreatefile() {
		return createfile;
	}

	public void setCreatefile(createNewFileGUI createfile) {
		this.createfile = createfile;
	}

	/**prevController is user main menu controller*/
	private userMainMenuController prevController;
	/**f is a file*/
	private file f = null;
	private File f1 = null;
	private boolean flag = false;
	private String ss;
	private JFileChooser fileChooser;
	//private User user;
	protected User userDetails;
	private int selectedComboBox;
	
	/**Constructor*/
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon){
		
		this.createfile=g;
		prevController=lastCon;
		createfile.addcancel(new ButtoncancelListener());
		createfile.addOpen(new ButtonOpenListener());
		createfile.addFinish(new ButtonFinishListener());
		createfile.selectPermission(new SelectedPermissionListener());
		createfile.addChooseAdvancedGroups(new addChooseAdvancedGroupsListener());
		
	}
	/**ButtoncancelListener is a class that implements action listener and goes back to the user main menu window*/
	
	private class addChooseAdvancedGroupsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addChooseAdvancedGroupsPressed();
		}	
	}
	private void addChooseAdvancedGroupsPressed() {
		//CurrGui.close();
		userDetails = prevController.getUserDetails();
		chooseAdvancedRegularGUI CA=new chooseAdvancedRegularGUI (userDetails);
		new chooseAdvancedController(CA,this,userDetails);
		CA.setVisible(true);
	}
	
	
	
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
			this.ss=f1.getPath()+"\\"+fileChooser.getSelectedFile().getName();
			createfile.setFile(f1);
			setFlag(true);
        } 
	}
	
	
	
	private class ButtonFinishListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ButtonFinishPressed();
		}
	}
	
	private void ButtonFinishPressed() {
		
		if(createfile.getFileNameField().getText().equals(""))
		{
			JOptionPane.showMessageDialog(createfile, "Please type the file name!", "Empty field",0,null);
		}
		else if(createfile.getDescriptionField().getText().equals(""))
			JOptionPane.showMessageDialog(createfile, "Please write the description for this file!", "Empty field",0,null);
		else if(!isFlag())
			JOptionPane.showMessageDialog(createfile, "Choose the file to upload", "Error!",0,null);
	
		else if(isFlag())
		{
			
			File oldFile= new File(ss);
			byte[] bArr=null;;
			try {
				bArr = Files.readAllBytes(oldFile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String[] type = ss.split("\\.",2);
			String name=createfile.getFileNameField().getText();
			String temp ="D:/mybox/"+ name+ "." + type[1];
			file upFile = new file(name,temp, selectedComboBox,myboxapp.clien.getCurrUser().getUserName());
			upFile.setFileContent(bArr);
			
			Envelope ev = new Envelope(upFile,"Save file in server");
			sendToServer(ev);
			myboxapp.clien.setCurrObj(this);
			
			}
		

	}
	
	public class SelectedPermissionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			int i = (int) createfile.getComboBox().getSelectedItem();
			if (i == 0)
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(3);
			}
			if(i==2)
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(true);
				setSelectedComboBox(i);
			}
			
			else
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(i);
			}
		
			
		}
	}
	
	public void handleDBResultFile(Object message) {
		if(message.equals("file saved successfully"))
		{
			
			JOptionPane.showMessageDialog(createfile, "File added succsesfully!", "Congratulations!", 1);
			createfile.close();
			if (prevController instanceof administratorMenuController)
			((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
			else prevController.getusermainmenu().setVisible(true);
		setFlag(false);
		}
		if(message.equals("file already exist"))
		{
			JOptionPane.showMessageDialog(createfile, "Change file name, and try again!", "Failed!",0,null);
		}
	}

	public int getSelectedComboBox() {
		return selectedComboBox;
	}

	public void setSelectedComboBox(int selectedComboBox) {
		this.selectedComboBox = selectedComboBox;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}