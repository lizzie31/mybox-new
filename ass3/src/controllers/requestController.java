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
	private requestsGUI requestgui=null;
	private administratorMenuController prevController;
	private String selectedrequest;
	private GroupsRequests request1;
	private ArrayList<GroupsRequests> allrequests;
	
	public requestController (requestsGUI g , administratorMenuController lastCon,ArrayList<GroupsRequests> allrequests){
		
		this.requestgui=g;
		this.allrequests=allrequests;
		prevController=lastCon;
		requestgui.addcancelRequest(new ButtoncancelListener());
		requestgui.addapproveRequest(new ButtonaprroveListener());
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
	private class listSelecTionListen implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent arg0) {
			selectedrequest=(String) (requestgui.getList().getSelectedValue());
			
	   }
	}
	public requestsGUI getRequestgui() {
		return requestgui;
	}
	

	
}
