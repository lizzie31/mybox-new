package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

public class GroupsListController {
	
	private groupListGUI gruoplist=null;
	private userMainMenuController prevController;
	
	public GroupsListController ( groupListGUI g , userMainMenuController lastCon){
		
		this.gruoplist=g;
		prevController=lastCon;
	    gruoplist.addcancel(new ButtoncancelListener());
	}
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttongrouplistPressed();
		}
		
	}
	private void buttongrouplistPressed() {
		gruoplist.close();
		prevController.getusermainmenu().setVisible(true);
	}
}