package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.InterestGroupGui;
import Model.Envelope;
import Model.User;
import Model.interestGroups;

/** this class is the controller of the interest groups window*/
public class InterestGroupCon extends AbstractTransfer {
	/**user*/
	private User user;
	/**group information*/
	private interestGroups groupInformation;
	/**interest group gui CurrGui*/
	private InterestGroupGui CurrGui;
	/**prev controller*/
	private GroupsListController prevCon;
	/**the selected file name*/
	private String selectedfile;
	
	/**constructor
	 * 
	 * @param user
	 * @param groupdetails
	 * @param prev 
	 */
	
	public InterestGroupCon(InterestGroupGui CurrG,User user,interestGroups groupdetails, GroupsListController prev)
	{
		this.user=user;
		this.groupInformation=groupdetails;
		this.CurrGui=CurrG;
		this.prevCon=prev;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addListActionListener(new listSelecTionListen());
		CurrGui.addOpen(new ButtonOpenListener());
	}
	
	
	
	/**************************************************action listeners**********************************************************/
	//cancel action listener
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CurrGui.close();
			prevCon.getGroupListGui().setVisible(true);
		}
	}
	
	private class ButtonOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<groupInformation.getFilesForRead().size();i++)
				if(groupInformation.getFilesForRead().get(i).getFileName().equals(selectedfile))
				{
			            Envelope en=new Envelope(groupInformation.getFilesForRead().get(i).getDirection(),"open file");
			            sendToServer(en);
				}
		}
		
	}
	
	//list selection listener
	private class listSelecTionListen implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent arg0) {
			selectedfile=(String) (CurrGui.getList1().getSelectedValue());
			
	   }
	}


	/**************************************************getters and setters***********************************************************/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public interestGroups getGroupInformation() {
		return groupInformation;
	}

	public void setGroupInformation(interestGroups groupInformation) {
		this.groupInformation = groupInformation;
	}	
	

}
