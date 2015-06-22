// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import server.*;
import view.*;
import ocsf.client.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import Model.file;
import Model.interestGroups;
import controllers.*;

//////////

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class MyBoxClient extends ObservableClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  ArrayList<String> Ar;
  private Object currController;
  private  User currUser = null;
 // private LoginGui

  
  //Constructors ****************************************************
  
/**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public MyBoxClient(String host, int port) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    Ar = new ArrayList<>();
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
@SuppressWarnings("unchecked")
public synchronized void handleMessageFromServer(Object message)  
  {
	 
	if(message instanceof Envelope ) //user name and password is found
	{
		 Envelope E =(Envelope) message;
		 
	    if(E.getTask().equals("log in handle"))
		   ((logInCon)(currController)).handleDBResult((User)E.getObject());
	    if(E.getTask().equals("show interest group"))
	    	((GroupListController)(currController)).handleDBresult((interestGroups)E.getObject());
	    if(E.getTask().equals("show all interest groups"))
	    	((userMainMenuController)(currController)).handleDBAllGroupsResult((ArrayList<interestGroups>)E.getObject());
	    if(E.getTask().equals("open file"))
	    {
	    	byte[] file=(byte[])E.getObject();
	    	try {
				((fileMenuCon)(currController)).handleDBResultFile((file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(E.getTask().equals("updating flag result"))    
	    	((UpdateCon)(currController)).isUpdatedByAnotherUser((file)E.getObject());
	    if(E.getTask().equals("restore files"))
	    {
	    	ArrayList<file> filesToRestore=(ArrayList<file>)E.getObject();
	    	if(filesToRestore.isEmpty())
	    	{
	    		((userMainMenuController)(currController)).getusermainmenu().setWarningMessageVisibleTrue("there is no files to restore.");
	    	}
	    	else
	    	{
	    		((userMainMenuController)(currController)).HandleRestoreFilesResult(filesToRestore);
	    	}
	    }
	    
	    if(E.getTask().equals("refresh data"))
	    {
	    	User userrefresh=(User)E.getObject();
	    	if(currController instanceof createNewFolderController)
	    	{
	    		((createNewFolderController)(currController)).RefreshUserData(userrefresh);
	    	}
	    	if(currController instanceof RestoreFileCon)
	    	{
	    		((RestoreFileCon)(currController)).RefreshUserData(userrefresh);
	    	}
	    	if(currController instanceof fileSearchController)
	    	{
	    		((fileSearchController)(currController)).RefreshUserData(userrefresh);
	    	}
	    	if(currController instanceof createNewFileController)
	    	{
	    		((createNewFileController)(currController)).RefreshUserData(userrefresh);
	    	}  	
	    	if(currController instanceof fileMenuCon)
	    	{
	    		((fileMenuCon)(currController)).RefreshUserData(userrefresh);
	    	}
	    	if(currController instanceof GroupListController)
	    	{
	    		((GroupListController)(currController)).RefreshUserData(userrefresh);
	    	}
	    }
	    if(E.getTask().endsWith("search file"))    
	    {
	    	if(E.getObject()==null)
	    		((userMainMenuController)(currController)).handleDBResultFile(null);
	    	else
	    	((userMainMenuController)(currController)).handleDBResultFile((ArrayList<file>)E.getObject());
	    }
	    if(E.getTask().endsWith("search file 2"))    
	    {
	    	if(E.getObject()==null)
	    		((administratorMenuController)(currController)).handleDBResultFile(null);
	    	else
	    	((administratorMenuController)(currController)).handleDBResultFile((ArrayList<file>)E.getObject());
	    }
	    if(E.getTask().equals("all users"))    
	    	((administratorMenuController)(currController)).handleDBResult2((ArrayList<User>)E.getObject()); 
	    if(E.getTask().equals("all groups"))    

	    	((administratorMenuController)(currController)).handleDBResult2((ArrayList<interestGroups>)E.getObject()); 

	

	    if(E.getTask().equals("all requests"))    
	    	((administratorMenuController)(currController)).handleDBResult2((ArrayList<GroupsRequests>)E.getObject()); 

	    if(E.getTask().equals("all groups to admin"))    
	    	((logInCon)(currController)).handleDBResult2((ArrayList<interestGroups>)E.getObject());
	    if(E.getTask().equals("files in group"))    
	    	((administratorMenuController)(currController)).handleDBResult2((interestGroups)E.getObject());
	    if(E.getTask().equals("all files"))    
	    	((administratorMenuController)(currController)).getfiles((ArrayList<file>)E.getObject());

	}
	if(message instanceof String ) //user name and password not found
	{
		String str=message.toString();
		if(str.equals("Not found User"))
			((logInCon)currController).getLoginG().setWarningMessageVisibleTrue();
		if(str.equals("mail doesn't exists"))
		{
			((forgetPassCon)currController).getforPassGui().setWarningMessageVisibleTrue("mail doesn't exists!!");
		}
		if(str.equals("we found the mail!"))
		{
			((forgetPassCon)currController).getforPassGui().setWarningMessageVisibleTrue("check your email to see your password");
		}
		if(str.equals("this name is allready exist"))
		{
			((createNewGroupController)currController).getGroup().setWarningMessageVisibleTrue("There is already interest group with this name please try again");
		}
		if(str.equals("the group was added sucssesfuly"))
		{
			((createNewGroupController)currController).getGroup().showsuceedmessege();
		}
		if(str.equals("the group delete secsefuly"))
		{
			((deleteGroupController)currController).getGroup().showsuceedmessege();
		}

		if(str.equals("updated"))
		{
			 ((UpdateCon)(currController)).showsuceedmessege();
		}
	    if(str.equals("this file exists in your files"))
	    {
	    	((AddToClientFilesCon)(currController)).getCurrGui().setWarningMessageVisibleTrue("this file already exists in this folder");
	    }
	    if(str.equals("file added succesfully"))
	    {
	    	((AddToClientFilesCon)(currController)).HandleDBresult();
	    }
		

		if(str.equals("the user was added secssfuly to this group"))
		{
			((requestController)currController).getRequestgui().showsuceedmessege("the user was added secssfuly to this group");
		}
		if(str.equals("the user was deleted secssfuly from this group"))
		{
			((requestController)currController).getRequestgui().showsuceedmessege("the user was deleted secssfuly from this group");
		}
		if(str.equals("request allready exist"))
		{
			((JoinGroupCon)currController).getJoinGroupGui().setWarningMessageVisibleTrue("you all ready send this request, please be patient");
		}
		if(str.equals("request was send"))
		{
			((JoinGroupCon)currController).getJoinGroupGui().secssid_massege();
		}
		
		if(str.equals("no requests"))
		{
			((administratorMenuController)currController).getusermainmenu2().setWarningMessageVisibleTrue("you dont have requests");

		}
		if(str.equals("the file delete secsefuly"))
		{
			((EditGroupCon)currController).getEgroup().showsuceedmessege("the file deleted from this group");
		}
		if(str.equals("the file added to the group"))
		{
			((EditGroupCon)currController).getEgroup().showsuceedmessege("the file added to this group :)");
		}
		
		
		//permission updated successfully
		if(str.equals("file saved successfully"))
	    	((createNewFileController)(currController)).handleDBResultFile("file saved successfully");
		if(str.equals("file already exist"))
	    	((createNewFileController)(currController)).handleDBResultFile("file already exist");
		
		if(str.equals("permission updated successfully"))
			((permissionController)(currController)).handleDBResultFile("permission updated successfully");
		
		if(str.equals("updated successfully"))
			((fileMenuCon)(currController)).handleDBResultFile2("updated successfully");
		
	}
  }
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  
  public Object getCurrObj() {
	return currController;
}
  
   public User getCurrUser() {
		return currUser;
	}


	public  void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


public void setCurrObj(Object currObj) {
	this.currController = currObj;
}
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
