package controllers;
import java.io.IOException;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import server.AbstractGuiServer;
import view.AbstractGui;
import client.CliMessage;
import client.IObserve;
import client.myboxapp;
import java.io.Serializable;
/**
 * this class is abstract transfer 
 *
 */
public abstract class AbstractTransfer implements Serializable
{

		
		/** The model. */
		protected Object theModel;
		
		/** The view. */
		protected AbstractGui theView;
		
		

		/**
		 * Instantiates a new abstract controller.
		 *
		 * @param theView the the view
		 * @param theModel the the model
		 * 
		 */
		public AbstractTransfer(){}
		
		public AbstractTransfer( AbstractGui theView , Object theModel) 
		{
			this.theModel = theModel;
			this.theView = theView;
		}
		
		/**
		 * Send to server with String
		 *
		 * @param request the request
		 */
		protected void sendToServer(String request)
		{
			try 
			{
				myboxapp.clien.sendToServer(request);
			} 
			catch (IOException e)
			{
				theView.displayInfoMessage("Error: Server comunication problem","Commuinication Error",0);
				e.printStackTrace();
			}
		}
	/**
	 * sent to server with Object	
	 * @param request
	 */
		public void sendToServer(Object request)
		{
			try 
			{
				myboxapp.clien.sendToServer(request);
			} 
			catch (IOException e)
			{
				theView.displayInfoMessage("Error: Server comunication problem","Commuinication Error",0);
				e.printStackTrace();
			}
		}
	/**
	 * this function is handle the DB result 	
	 * @param message
	 */
		// public void handleDBResult(Object message) {
			 
			 //
		// }
		 
		 public void UpdateDB(){
			 //
		 }
		
		
			
		
	}
