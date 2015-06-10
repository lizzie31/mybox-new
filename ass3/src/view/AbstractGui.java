package view;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * this class is Abstract Gui  that extends JFrame
 *
 */
public abstract class AbstractGui extends JFrame
{
	protected JButton backButton=null;

	/**
	 * constructor
	 * add window Listener 
	 */
	public AbstractGui(){
		this.addWindowListener(new Exit());
	}
	/**
	 * constructor
	 *  
	 */
	public AbstractGui(int i){
		
	}
	public void displayInfoMessage(String message,String title,int messageType)
	{
		JOptionPane.showMessageDialog(this, message, title,messageType);
	}
	

	
	
	/**
	 * This method adds an action listener to back to menu button.
	 * @param listener
	 */
	public void addBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	/**
	 * Inner class that we press on Exit button - update the status of Employee to 0 (Disconnected) and exit from program
	 * @author jacob
	 *
	 */
	class Exit implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
		//	 Envelope en1 = new Envelope(CcrmApp.clien.getCurrUser(),"updateStatus0");
			//	try {
					//CcrmApp.clien.sendToServer(en1);
					System.exit(1);
			//	} catch (IOException e1) {
					// TODO Auto-generated catch block
			//		e1.printStackTrace();
				}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		}

	
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

