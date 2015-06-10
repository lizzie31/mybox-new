package controllers;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class deleteGroupController {
	private deleteGroupGUI group;
	private administratorMenuController adm;
	public deleteGroupController(deleteGroupGUI group, administratorMenuController lastCon)
	{
		this.group=group;
		this.adm=lastCon;
		group.addcancel(new ButtonCancelListener());
	
	}

	
	
	private class ButtonCancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	
	private void buttoncancelPressed() {
		group.close();
		adm.getAdminCon().setVisible(true);
	
	}

}
