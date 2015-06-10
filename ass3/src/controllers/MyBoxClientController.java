package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.MyBoxClientModel;
import controllers.logInCon;
import Model.logInMod;
import Model.MyBoxClientModel;
import view.logInGui;
import view.MyBoxClientGUI;
import client.MyBoxClient;
import client.myboxapp;
//import client.CCRMClient;
/**
 * This Class is a CCRM Client Controller which create ccrmClient instance and check the port and host to server
 * @author jacob
 *
 */
public class MyBoxClientController 
{
	
	private MyBoxClientGUI clientView;
	private MyBoxClientModel clientModel;
	/**
	 * constructor  
	 * @param clientView is gui that show the host and port
	 * @param clientModel is entity of client that include host and port
	 */
	public MyBoxClientController(MyBoxClientGUI clientView,MyBoxClientModel clientModel)
	{
		this.clientView = clientView;
		this.clientModel = clientModel;
		clientView.addOKActionListener(new OKListener());
		clientView.addCancelActionListener(new CancelListener());
	}

	/**
	 * check if the Input type correctly
	 * @return boolean
	 */
	public boolean checkInput() 
	{
		try
		{
			if(clientView.getHost().equals("") || clientView.getPort()==0)
			{
				clientView.displayWarnningMessage("please enter some fields!!");
				clientView.clearFields();
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			clientView.displayWarnningMessage("Error: Use of elegal charchters");
			return false;
		}
	}

	/**
	 * Inner class that handles when Button OK Pressed, implements ActiontListener
	 * @author jacob
	 *
	 */
	class OKListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			if(checkInput() == false)
				return;
			try 
			{
				String host = new String (clientView.getHost());
				int port = clientView.getPort();
				clientModel.setHost(host);
				clientModel.setPort(port);
				
				myboxapp.clien = new MyBoxClient(host,port); //singleton
				
				if(myboxapp.clien.isConnected())
				{
					clientView.dispose(); //remove the current window to display login window
				
					//create a new login controller
					logInGui logView = new logInGui();
					logInMod logModel = new logInMod();
					logInCon logController = new logInCon(logView,logModel);
				}
				else
				{
					clientView.displayWarnningMessage("Faild to connect. check IP and port!");
					clientView.clearFields();
				}
				
			} catch (NumberFormatException e) 
			{
				clientView.displayWarnningMessage("Faild to connect. check IP and port!");
				clientView.clearFields();
				//e.printStackTrace();
			} 
			catch (IOException e) 
			{
				clientView.displayWarnningMessage("Connection problem. check IP and Port.");
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 *  Inner class that handles when Button cancel Pressed, implements ActiontListener
	 * @author jacob
	 *
	 */
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			clientView.dispose();
			System.exit(1);
		}
	}
}
