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
import Model.interestGroups;
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
 *
 */
public class logInCon extends AbstractTransfer
{
	/**loginG is log in gui window*/
	private logInGui loginG;
	/**loginM is log in mod*/
	private logInMod loginM;
	/**tempL is logInController*/
	private logInCon tempL;
	private User user;
	/**Menu2 is administrator menu gui window*/
	private administratorMenuGUI  Menu2;
	/**Menu is user main menu gui window*/
	private userMainMenuGUI Menu;
	/**frgt is forget password gui window*/
	private forgget_password frgt;
	/**username saves the user name entered in the login window*/
	private String username;
	/**filesInUserDB is an array list of all the files that the user added to his personal space*/
	private ArrayList<file> filesInUserDB=null;
	private Envelope en=null;
	/**allUsers is an array list of all the users in the DB*/
	private ArrayList<User> allUsers;
	
	
	
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
	
	/**sets the array list of all the users to a given array list*/
	public void setUser(ArrayList<User> u) {
		allUsers = u;
	}
	
	/**checks if a given user name already exist in the db
	 @param temp is a string that saves each time a name of a user from allUsers arrayList**/
	public boolean userExist(String userName){
		String temp;
		for (int i =0 ;i<allUsers.size();i++){
			temp=allUsers.get(i).getUserName();
			if(userName.compareTo(temp)==0)
				return true;
		}
		return false;
	}
	/**check if the user is connected or not
	  @param temp is a string that saves each time a name of a user from allUsers arrayList* */
	public boolean userConnect(String userName)
	{
		String temp;
		for (int i =0 ;i<allUsers.size();i++){
			temp=allUsers.get(i).getUserName();
			if (temp.compareTo(userName)==0){
				if (allUsers.get(i).getStatus()==1)
					return true;
				else 
					return false;
			}
		}
		return false;
	}
	/**check if the password of the userName is correct*/
	public boolean checkPassword(String  userName,String Pass)
	{
		for (int i =0 ;i<allUsers.size();i++){
			if (allUsers.get(i).getUserName().equals(userName)){
				if (allUsers.get(i).getUpassword().equals(Pass))
					return true;
				else 
					return false;		
				}
			}
		return false;
	}
	/**change the status of the user userName*/
	public int changeStatus(String userName){
		String temp;
		int res = -1;
		for (int i =0 ;i<allUsers.size();i++){
			temp=allUsers.get(i).getUserName();
			if (userName.compareTo(temp)==0){
				allUsers.get(i).setStatus(1-allUsers.get(i).getStatus());
				    res =  allUsers.get(i).getStatus();
			}
			}
		return res;
	}

	
	//============================================================================
	
	
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
		        sendToServer("all interest groups to admin");
		    	myboxapp.clien.setCurrObj(this );
		        
		 }
		 else{
		        UpdateDB(); //update the status to 1 that we know the user is login the system 
		        myboxapp.clien.setCurrUser(user);	 
		        loginG.dispose();
		        Menu= new userMainMenuGUI(user);
		        new userMainMenuController(Menu,this,user);
		 }
		 
		
	}
	public void handleDBResult2(Object message) {
		ArrayList<interestGroups> IG =(ArrayList<interestGroups>)message;
		  Menu2= new administratorMenuGUI(user,IG);
		  Menu= new userMainMenuGUI(user);
	        new administratorMenuController(Menu,this,user,Menu2);
		
	}


	public String getusername() {
		return username;
	}

	
	}