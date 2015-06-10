package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;

import Model.Envelope;
import Model.User;
import Model.file;
import Model.logInMod;
import view.administratorMenuGUI;
import view.forgget_password;
import view.logInGui;
import view.userMainMenuGUI;
import client.CliMessage;
import client.IObserve;
import client.myboxapp;
/**
 * This Class is a Login Controller, which extends AbstractTransfer
 * @author jacob
 *
 */
public class logInCon extends AbstractTransfer
{
	
	private logInGui loginG;
	private logInMod loginM;
	private logInCon tempL;
	private User user;
	private administratorMenuGUI  Menu2;
	private userMainMenuGUI Menu;
	private forgget_password frgt;
	private String username;
	private ArrayList<file> filesInUserDB=null;
	private Envelope en=null;
	
	
	
	//============================================================================
	//attribute of tests
	//public  lo=null;
	//public ArrayList<Employee> empp = null; 
	//public ArrayList<PermissionModel> perm = null;
	
	
	public logInCon(){
	
	}

	
	/**
	 * 
	 * @param lC is gui instance
	 * @param lM Login Model instance - that include user name and password
	 */

	public logInCon(logInGui lC,logInMod lM ) {
		//super(lC,lM);
		loginG = lC;
		loginM = lM;
		tempL = this;
		loginG.addLoginActionListener(new LoginListener());
		loginG.addfrgtPassActionListener(new frgtPassListener());
		
	}
	
	/**
	 * Inner class that handles when Button forget password Pressed, implements ActiontListener
	 *
	 */	
	class frgtPassListener implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			 loginG.dispose();
			 frgt= new forgget_password();
			 new forgetPassCon(frgt,getTempL());
			
		}
	
	}
/**
 * Inner class that handles when Button login Pressed, implements ActiontListener
 *
 */
	class LoginListener implements ActionListener {
		
		
           public void actionPerformed(ActionEvent ev){
        	  
        		  String pass = loginG.getTextPassword();
        		  username = loginG.getTextUserName();
        		  
        		  if(pass.equals("")|| username.equals("") ) {
        			  loginG.setWarningMessageVisibleTrue("please enter missing fields!");
        			  return;
        		  }
        		  else
        		  {
 
        			  loginG.undisplayWarningMessage();
        			  try{ //set the user name and password and sent to server
        				 loginM.setPassword(pass);
        				 loginM.setUserName(username);
        				 en=new Envelope(loginM,"login");
        				 sendToServer(en);
        				 myboxapp.clien.setCurrObj(getTempL());
        				 
        				 
        			  }
        			  catch(Exception e){
        				  
        			  }
			
		      }
           }
	
	
	/*
	 * (non-Javadoc)
	 * @see controllers.AbstractTransfer#handleDBResult(java.lang.Object)
	 */

		 
  	 }
	
	 
	 public void UpdateDB(){
		 //update the status of user to 1 - it's mean that is login to system
		   en=new Envelope(user,"log in status");
		   sendToServer(en);
		   user.setStatus(1);
	  }
	
	/**
	 * get the login Gui 
	 * @return LoginGui
	 */
	public logInGui getLoginG() {
		return loginG;
	}
	/**
	 * set login gui
	 * @param loginG
	 */
	public void setLoginG(logInGui loginG) {
		this.loginG = loginG;
	}

	/**
	 * get login model(entity)
	 * @return LoginMod
	 */
	public logInMod getLoginM() {
		return loginM;
	}

	/**
	 * set log in model(entity)
	 * @param loginM
	 */
	public void setLoginM(logInMod loginM) {
		this.loginM = loginM;
	}
	/**
	 * get instance  of current controller
	 * @return LoginCon
	 */
	public logInCon getTempL() {
		return tempL;
	}

	public void handleDBResult(Object message) {
		 user = (User)message;
		 if(user.getStatus() == 1)
				loginG.setWarningMessageVisibleTrue("This user name is already Login to system");
		 else if(user.getUserName().equals("nofar")){
			   UpdateDB(); //update the status to 1 that we know the user is login the system 
		        myboxapp.clien.setCurrUser(user);	 
		        loginG.dispose();
		        Menu2= new administratorMenuGUI(user);
		        Menu= new userMainMenuGUI(user);
		        new administratorMenuController(Menu,this,user,Menu2);
		 }
		 else{
		        UpdateDB(); //update the status to 1 that we know the user is login the system 
		        myboxapp.clien.setCurrUser(user);	 
		        loginG.dispose();
		        Menu= new userMainMenuGUI(user);
		        new userMainMenuController(Menu,this,user);
		 }
		 
		
	}


	public String getusername() {
		return username;
	}

	public void setTheFiles(Object message) {
		user.setFilesInDB((ArrayList<file>)message);
		
	}
	}