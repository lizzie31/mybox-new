package server;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AbstractGuiServer extends JFrame{
protected JButton backButton=null;

	
	
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
}
