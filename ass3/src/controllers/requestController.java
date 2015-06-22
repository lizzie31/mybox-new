package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.myboxapp;
import Model.Envelope;
import Model.GroupsRequests;
import view.createNewFileGUI;
import view.requestsGUI;


public class requestController extends AbstractTransfer {
	/**requestgui is requests gui window*/
	private requestsGUI requestgui;
	/**prevController is administrator menu controller*/
	private administratorMenuController prevController;
	/**saves the selected request*/
	private String selectedrequest;
	/**request1 is group requests gui*/
	private GroupsRequests request1;
	/**allrequests is an array list of all the group requests**/
	private ArrayList<GroupsRequests> allrequests;
	
	/**constructor*/
	public requestController (requestsGUI g , administratorMenuController lastCon,ArrayList<GroupsRequests> allrequests){
		
		this.requestgui=g;
		this.allrequests=allrequests;
		prevController=lastCon;
		requestgui.addcancelRequest(new ButtoncancelListener());
		requestgui.addapproveRequest(new ButtonaprroveListener());
		requestgui.addListActionListener(new listSelecTionListen());
		requestgui.adddenyRequest(new ButtonadenyListen());
	}
	/*********************action listeners*******************/
	/**button listener of approve request*/
	
	private class ButtonadenyListen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonDenyPressed();
		}
		
	}
	private void buttonDenyPressed() {
		for(int i=0;i<allrequests.size();i++)
		{
		if(selectedrequest.contains(allrequests.get(i).getGroupName()) && selectedrequest.contains(allrequests.get(i).getRequestType()) && selectedrequest.contains(allrequests.get(i).getUserName()))
				request1=allrequests.get(i);
		}
		Envelope en=new Envelope(request1,"DenyRequest");
		sendToServer(en);
		requestgui.close();
		
	}
	private class ButtonaprroveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonapprovePressed();
		}
		
	}
	private void buttonapprovePressed() {
		for(int i=0;i<allrequests.size();i++)
		{
		if(selectedrequest.contains(allrequests.get(i).getGroupName()) && selectedrequest.contains(allrequests.get(i).getRequestType()) && selectedrequest.contains(allrequests.get(i).getUserName()))
				request1=allrequests.get(i);
		}
		Envelope en=new Envelope(request1,"answer request");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this );
		
		
	}
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		requestgui.close();
		prevController.getusermainmenu2().setVisible(true);
		
	}
	//list selection listener
	/**listSelecTionListen is a class that implements ListSelectionListener*/
	private class listSelecTionListen implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent arg0) {
			selectedrequest=(String)( requestgui.getList().getSelectedValue());
					
			
	   }
	}
	/*******getters and setters**********/
	public requestsGUI getRequestgui() {
		return requestgui;
	}
	

	
}
