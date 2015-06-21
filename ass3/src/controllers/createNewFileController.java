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
import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.directories;
import Model.file;
import Model.interestGroups;
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
	private file advancedFile = null;
	private boolean flag = false;
	private boolean locFlag = false;
	private String ss;
	private JFileChooser fileChooser;
	//private User user;
	protected User userDetails;
	private int selectedComboBox;

	
	/**Constructor
	 * 
	 * @param g
	 * @param lastCon
	 */
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon){

	private directories parent=null;
	//private ArrayList<interestGroups> allGroups = new ArrayList<>();
	/**Constructor*/
	public createNewFileController (createNewFileGUI g , userMainMenuController lastCon,User us){

		
		this.createfile=g;
		this.userDetails=us;
		prevController=lastCon;
		createfile.addcancel(new ButtoncancelListener());
		createfile.addOpen(new ButtonOpenListener());
		createfile.addFinish(new ButtonFinishListener());
		createfile.selectPermission(new SelectedPermissionListener());
		createfile.addChooseAdvancedGroups(new addChooseAdvancedGroupsListener());
		createfile.addChooseLocation(new ChooseLocationListener());
		
	}
	/**ButtoncancelListener is a class that implements action listener and goes back to the user main menu window*/
	
	private class addChooseAdvancedGroupsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addChooseAdvancedGroupsPressed();
		}	
	}
	
	
	private void addChooseAdvancedGroupsPressed() {
		//CurrGui.close();
		chooseAdvancedRegularGUI CA=new chooseAdvancedRegularGUI (userDetails);
		new chooseAdvancedController(CA,this,userDetails);
		CA.setVisible(true);
	}
	
	
	private class ChooseLocationListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnChooseLocationPressed();
		}	
	}
	
	private void btnChooseLocationPressed()
	{
		createfile.close();
		NewFileLocationGui filegui=new NewFileLocationGui(this,userDetails);
		new NewFileLocationCon(filegui,this,userDetails);
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
		
		else if(createfile.getDescriptionField().getText().length() > 40)
			JOptionPane.showMessageDialog(createfile, "The description must be less then 40 characters!", "Not available field",0,null);
		else if(!isFlag())
			JOptionPane.showMessageDialog(createfile, "Choose the file to upload", "Error!",0,null);
		else if(!isLocFlag())
			JOptionPane.showMessageDialog(createfile, "Choose the file location to save!", "Error!",0,null);
	
		else if(isFlag() && isLocFlag())
		{
			
			File oldFile= new File(ss);
			
				
			byte[] bArr=null;;
			try {
				bArr = Files.readAllBytes(oldFile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//int i = (int) createfile.getComboBox().getSelectedItem();
			if (selectedComboBox == 0)
			{
				setSelectedComboBox(3);
			}
			
			String[] type = ss.split("\\.",2);
			String name=createfile.getFileNameField().getText();
			String temp ="D:/mybox/"+ name+ "." + type[1];
			file upFile = new file(name,temp, selectedComboBox,myboxapp.clien.getCurrUser().getUserName(),null);
			if(advancedFile!=null)
			{
				upFile.setGroupsForRead(advancedFile.getGroupsForRead());
				upFile.setGroupsForUpdate(advancedFile.getGroupsForUpdate());
			}
			
			if(advancedFile == null && selectedComboBox == 2)
				upFile.setGroupsForRead(userDetails.getInterestGroupInDB());
			
			if(advancedFile == null && selectedComboBox == 3)
			{
				
				upFile.setGroupsForRead(userDetails.getInterestGroupInDB());
			}
			
			upFile.setFileContent(bArr);
			upFile.setParent(parent);
			
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
				createfile.getComboBox().setSelectedItem(3);
				setSelectedComboBox(3);
			}
			
			if(i == 1)
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(1);
			}
			if(i == 2)
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(true);
				setSelectedComboBox(2);
			}
			
			if(i == 3)
			{
				createfile.getBtnChooseAdvancedGroups().setEnabled(false);
				setSelectedComboBox(3);
			}
		
			
		}
	}
	
	public void handleDBResultFile(Object message) {
		if(message.equals("file saved successfully"))
		{
			Envelope en=new Envelope(userDetails,"refresh data");
			sendToServer(en);
			myboxapp.clien.setCurrObj(this);
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

	public file getAdvancedFile() {
		return advancedFile;
	}

	public void setAdvancedFile(file advancedFile) {
		this.advancedFile = advancedFile;
	}

	public directories getParent() {
		return parent;
	}

	public void setParent(directories parent) {
		this.parent = parent;
	}

	public void RefreshUserData(User userrefresh) {
		JOptionPane.showMessageDialog(createfile, "File added succsesfully!", "Congratulations!", 1);
		userDetails=userrefresh;
		createfile.close();
		if (prevController instanceof administratorMenuController)
		((administratorMenuController) prevController).getusermainmenu2().setVisible(true);
		else 	
		{
		userMainMenuGUI menu=new userMainMenuGUI(userDetails);
		new userMainMenuController(menu,userDetails);
		}
		
	}

	public boolean isLocFlag() {
		return locFlag;
	}

	public void setLocFlag(boolean locFlag) {
		this.locFlag = locFlag;
	}
	
	
	
}
