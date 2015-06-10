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
import Model.User;
import Model.file;
import Model.interestGroups;
import controllers.*;



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
    //this.clientUI = clientUI;
    Ar = new ArrayList<>();
    openConnection();
  //  sendToServer("hello");
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
	    if(E.getTask().equals("show all interest groups"))
	    	((userMainMenuController)(currController)).handleDBAllGroupsResult((ArrayList<interestGroups>)E.getObject());
	    if(E.getTask().equals("show user interest groups"))
	    	((userMainMenuController)(currController)).handleDBResult((User)E.getObject());    
	    if(E.getTask().equals("search files"))
	    	((userMainMenuController)(currController)).handleDBResultFile((ArrayList<file>)E.getObject());
	         
	        
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
		
	}//if
	/*if(message instanceof ArrayList<?>)
	{
		if(((ArrayList<?>) message).get(1) instanceof file)
				{
			((logInCon)(currController)).setTheFiles(message);
				}
	}
	*/
	
  }
	
	/*if(message instanceof String ){ //warning massage
		
	if(((String)message).equals("Not found User") ){
		((LoginController)currController).getLoginG().setWarningMessageVisibleTrue();
	}
	if(((String)message).equals("area exist") ){
		((AreaController)currController).getAreaView().setWarningMes("This area is already exist");
	  }
	
	if(((String)message).equals("area added") ){
		((AreaController)currController).getAreaView().setWarningMes("The area added successfully");
	  }
	
	//set warning message if the category is already exist 
	if(((String)message).equals("Category exist") ){
		((CategoryController)currController).getAreaView().setWarningMes("This Category is already exist");
	  }
	//set  message if the category is added successfully
	if(((String)message).equals("Category added") ){
		((CategoryController)currController).getAreaView().setWarningMes("The category added successfully");
	  }
	if(((String)message).equals("no such a customer") ){
		((CustomerHistoryController)currController).warmassege();
	  }
	if(((String)message).equals("OK") ){
		((FinalAccOrRejController)currController).hendleDBadd();
	}
	
	} //end instence of String
	
	if(message instanceof Envelope) { //get the ArrayList area to show the area on Combox
		
		if(((Envelope)message).getTask().equals("take all unactive campign")){
			((StartCampignController)currController).handleFormDBfirst((String)((Envelope)message).getObject());
		}
		if(((Envelope)message).getTask().equals("Active the campign plzz")){
			((StartCampignController)currController).handleFormDBsecond(((Envelope)message).getMess());
		}

		if(((Envelope)message).getTask().equals("Take Campain")){
				((GetCustomerListConntroler)currController).handlemessformDBCampain((String)((Envelope)message).getObject());
		}
		if(((Envelope)message).getTask().equals("Take a Customer List")){
			((GetCustomerListConntroler)currController).handlemessformDBCustomer((CustomerListModel)(((Envelope)message).getObject()));
		}
		if(((Envelope)message).getTask().equals("take all prem")){
		  ((FinalAccOrRejController)currController).hendleDBcombo((String)((Envelope)message).getObject());
		}
		if(((Envelope)message).getTask().equals("get area") || ((Envelope)message).getTask().equals("Serch costumer")
				|| ((Envelope)message).getTask().equals("Serch employee"))
		{
			if(((Envelope)message).getTask().equals("Serch employee")){
				((HRcreateNewEmployeeController)currController).handleDBResult(message);
			}
			else
				((CostumerRelEmpCont)currController).handleDBResult(message);
		}
		if(((Envelope)message).getTask().equals("get area for employee"))
			((HRcreateNewEmployeeController)currController).handleDBResult(message);	
		if(((Envelope)message).getTask().equals("serch campaign") || ((Envelope)message).getTask().equals("serch Sales report")){
			((SalesReportController)currController).handleDBResult(message);
		}
		if(((Envelope)message).getTask().equals("Potential Customer")){
			((InsertPostentialController)currController).handleDBResult(message);
		}
		if(((Envelope)message).getTask().equals("Customer report")){
			((CustomerReportController)currController).handleDBResult(message);
		}
		if(((Envelope)message).getTask().equals("get Category for type")){
			((MMaddTypeController)currController).handleDBResult(message);
		}
		if(((Envelope)message).getTask().equals("addType")){
			//System.out.println("in client: handleMessageFromServer addtype");
			((MMaddTypeController)currController).handleDBResult(message);
		}
		// human resources
		if(((Envelope)message).getMess().equals("employee found")){
			//System.out.println("in client: handleMessageFromServer emp found");
			Employee emp;
			emp = (Employee)(((Envelope)message).getObject());
			//System.out.println(emp.getUsreName());
			((HRupdateEmployeeDetalesController)currController).handleDBResult(emp);
		}
		if (((Envelope)message).getMess().equals("employee not found")){
			//System.out.println("in client: handleMessageFromServer emp not found");
			((HRupdateEmployeeDetalesController)currController).handleDBResult("employee not found");
		}
		if(((Envelope)message).getMess().equals("Update succeeded")){
			((HRupdateEmployeeDetalesController)currController).handleDBResult("Update succeeded");
		}
		
		//marketing add item
		if(((Envelope)message).getMess().equals("item exist in DB"))
			((NewItemController)currController).handleDBResult("item exist in DB");
		//marketing add item
		if(((Envelope)message).getMess().equals("item added successfully"))
			((NewItemController)currController).handleDBResult("item added successfully");
		
		//marketing add Campaign Pattern
		if(((Envelope)message).getTask().equals("get items"))
			((CampaignPaternController)currController).handleDBResult(message);
		//marketing add Campaign Pattern
		if(((Envelope)message).getTask().equals("get areas"))
			((CampaignPaternController)currController).handleDBResult(message);
		//marketing add Campaign Pattern
		if(((Envelope)message).getTask().equals("get all permissions"))
			((CampaignPaternController)currController).handleDBResult(message);
		//marketing add Campaign Pattern
		if(((Envelope)message).getTask().equals("set CampaignPatern in DB"))
			((CampaignPaternController)currController).handleDBResult(message);
		//marketing add Campaign Pattern
		if(((Envelope)message).getTask().equals("get all categoryz"))
			((ItemToCatalogController)currController).handleDBResult(message);
		
		//marketing add Item To Catalog
		if(((Envelope)message).getTask().equals("get all types in gategory"))
			((ItemToCatalogController)currController).handleDBResult(message);
		//marketing add Item To Catalog
		if(((Envelope)message).getTask().equals("get items2"))
			((ItemToCatalogController)currController).handleDBResult(message);
		//marketing add Item To Catalog
		if(((Envelope)message).getTask().equals("add Item to catalog"))
			((ItemToCatalogController)currController).handleDBResult(message);
	}
	if (message instanceof CustomerHistoryModel)
	{
		((CustomerHistoryController)currController).handleDBResult((CustomerHistoryModel)message);
	}
	notify();   
  }
  
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
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
