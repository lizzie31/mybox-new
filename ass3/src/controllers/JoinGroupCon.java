package controllers;
	import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import view.*;

public class JoinGroupCon extends AbstractTransfer{
	

		
		private joinGroupGui JoinGroupGui=null;
		private userMainMenuController prevController;
		private String GroupName=null;
		private User user;
		
		public JoinGroupCon (joinGroupGui g , userMainMenuController lastCon, User userDetails){
			
			this.JoinGroupGui=g;
			this.prevController=lastCon;
			this.user=userDetails;
			JoinGroupGui.addcancel(new ButtoncancelListener());
			JoinGroupGui.addSelectGroup(new SelectedGroupListener());
			JoinGroupGui.addSendToSystem(new ButtonSend());
			
		}
		private class ButtoncancelListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttoncancelPressed();
			}
			
		}
		
		private void buttoncancelPressed() {
			JoinGroupGui.close();
			prevController.getusermainmenu().setVisible(true);
		}
		
		
		public class SelectedGroupListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				GroupName=(String)JoinGroupGui.getComboBox().getSelectedItem();
				System.out.println(""+GroupName);
				
			}
		}
		
		public class ButtonSend implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				if(GroupName==null)
				{
					JoinGroupGui.setWarningMessageVisibleTrue("please select a group!");
				}
				else
				{
				GroupsRequests request=new GroupsRequests(GroupName,user.getUserName(),"join");
				Envelope en=new Envelope(request,"send request to system administrator");
				sendToServer(en);
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "your request was send to the system administrator!");
				}
			}
		}
		
		

}
