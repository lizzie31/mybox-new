package controllers;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import view.*;

public class EnterOrLeaveGroupController {
	

		
		private EnterOrLeaveGroupGUI ELgroup=null;
		private userMainMenuController prevController;
		
		public EnterOrLeaveGroupController (EnterOrLeaveGroupGUI g , userMainMenuController lastCon){
			
			this.ELgroup=g;
			prevController=lastCon;
			ELgroup.addcancel(new ButtoncancelListener());
		}
		private class ButtoncancelListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttoncancelPressed();
			}
			
		}
		private void buttoncancelPressed() {
			ELgroup.close();
			prevController.getusermainmenu().setVisible(true);
		}

}
