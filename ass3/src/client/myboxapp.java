package client;

import Model.MyBoxClientModel;
import view.*;
import controllers.*;
import Model.*;
/**
 * the main class that start the ccrm application
 * @author jacob
 *
 */
public class myboxapp {

	public static MyBoxClient clien;

	public static void main(String[] args) {

		MyBoxClientGUI clientView = new MyBoxClientGUI();
		MyBoxClientModel clientModel = new MyBoxClientModel();
		MyBoxClientController clientController = new MyBoxClientController(clientView,clientModel);

	}

}
