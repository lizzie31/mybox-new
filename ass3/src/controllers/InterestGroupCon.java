package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InterestGroupGui;
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
	
	/**constructor
	 * 
	 * @param user
	 * @param groupdetails
	 * @param prev 
	 */
	
	public InterestGroupCon(InterestGroupGui CurrGui,User user,interestGroups groupdetails, GroupsListController prev)
	{
		this.user=user;
		this.groupInformation=groupdetails;
		this.CurrGui=CurrGui;
		this.prevCon=prev;
		CurrGui.addcancel(new ButtoncancelListener());
		
	}
	
	//cancel action listener
	private class ButtoncancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CurrGui.close();
			prevCon.getGroupListGui().setVisible(true);
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
