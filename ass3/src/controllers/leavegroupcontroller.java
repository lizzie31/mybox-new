package controllers;
	import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.myboxapp;
import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import view.*;

public class leavegroupcontroller extends AbstractTransfer{
		/**leaveGroupGui is leave group gui window*/
		private leavegroupGUI leaveGroupGui=null;
		/**prevController is the user main menu controller*/
		private userMainMenuController prevController;
		/**GroupName is the name of the group the user requested to leave*/
		private String GroupName=null;
		private leavegroupcontroller thiscon=this;
		private User user;
		
		/**constructor
		 * 
		 * @param g
		 * @param lastCon
		 * @param userDetails
		 */
		public leavegroupcontroller (leavegroupGUI g , userMainMenuController lastCon, User userDetails){
			
			this.leaveGroupGui=g;
			this.prevController=lastCon;
			this.user=userDetails;
			leaveGroupGui.addcancel(new ButtoncancelListener());
			leaveGroupGui.addSelectGroup(new SelectedGroupListener());
			leaveGroupGui.addSendToSystem(new ButtonSend());
			
		}
		/*********************action listeners*******************/
		/**button listener of cancel*/
		private class ButtoncancelListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttoncancelPressed();
			}
			
		}
		
		private void buttoncancelPressed() {
			leaveGroupGui.close();
			prevController.getusermainmenu().setVisible(true);
		}
		
		/**button listener of select group*/
		public class SelectedGroupListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				GroupName=(String)leaveGroupGui.getComboBox().getSelectedItem();
				System.out.println(""+GroupName);
				
			}
		}
		/**button listener of send*/
		public class ButtonSend implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				if(GroupName==null)
				{
					leaveGroupGui.setWarningMessageVisibleTrue("please select a group!");
				}
				else
				{
				GroupsRequests request=new GroupsRequests(GroupName,user.getUserName(),"leave");
				Envelope en=new Envelope(request,"send request to system administrator");
				sendToServer(en);
				myboxapp.clien.setCurrObj(getThiscon());
				}
			}
		}

		public leavegroupcontroller getThiscon() {
			return thiscon;
		}
		public void setThiscon(leavegroupcontroller thiscon) {
			this.thiscon = thiscon;
		}
		public leavegroupGUI getLeaveGroupGui() {
			return leaveGroupGui;
		}
		
		
		

}
