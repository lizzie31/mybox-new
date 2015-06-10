package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.createNewFileGUI;
import view.requestsGUI;


public class requestController {
	private requestsGUI request=null;
	private administratorMenuController prevController;
	
	public requestController (requestsGUI g , administratorMenuController lastCon){
		
		this.request=g;
		prevController=lastCon;
		request.addcancelRequest(new ButtoncancelListener());
	}
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		request.close();
		prevController.getusermainmenu2().setVisible(true);
		
	}
	
}
