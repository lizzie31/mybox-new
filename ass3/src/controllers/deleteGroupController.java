package controllers;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class deleteGroupController {
	/**group is delete group window*/
	private deleteGroupGUI group;
	/**adm is administrator menu controller*/
	private administratorMenuController adm;
	
	/**constractor*/
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
