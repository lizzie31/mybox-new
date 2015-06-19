package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controllers.JoinGroupCon.SelectedGroupListener;
import Model.Envelope;
import Model.GroupsRequests;
import Model.User;
import Model.file;
import view.fileMenuGui;
import view.permissionGui;

public class permissionController extends AbstractTransfer {

	/**currGui is permission window*/
	private permissionGui CurrGui;
	/**prevCon is the file menu controller*/
	private fileMenuCon prevCon;
	/**selectedPermission is the permission the user selected*/
	private String selectedPermission;
	private int permission;
	private User user;
	private file f;
	
	/**constructor
	 * @param u
	 * @param per
	 * @param f
	 */
	public permissionController(permissionGui per, fileMenuCon f,User u, file file)
	{
		this.f=file;
		this.user=u;
		this.CurrGui=per;
		this.prevCon=f;
		CurrGui.addcancel(new ButtoncancelListener());
		CurrGui.addSelectPermission(new SelectedPermissionListener());
		CurrGui.addOk(new ButtonOk());
	}
	/**button listener of cancel*/
	private class ButtoncancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncancelPressed();
		}
		
	}
	private void buttoncancelPressed() {
		CurrGui.dispose();
		prevCon.getCurrGui().setVisible(true);
		
	}
	
	/**button listener of selecting a permission*/
	public class SelectedPermissionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			selectedPermission=(String) CurrGui.getComboBox().getSelectedItem();
			if(selectedPermission.equals("1"))
				permission=1;
			else
			{
				if(selectedPermission.equals("2"))
					permission=2;
				else
				{
					if(selectedPermission.equals("3"))
						permission=3;
					else
						permission=0;
				}
			}
			
		}
	}
	/**button listener of send*/
	public class ButtonOk implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Envelope en;
			if(permission==0)
				CurrGui.setWarningMessageVisibleTrue("please select a permission!");
			else
			{
					//GroupsRequests request=new GroupsRequests(GroupName,user.getUserName(),"join");
				if(permission==1)
					en=new Envelope(f.getFileName(),"change permission 1");
				else
				{
					if(permission==2)
						en=new Envelope(f.getFileName(),"change permission 2");
					else
						en=new Envelope(f.getFileName(),"change permission 3");
				}
					
				sendToServer(en);
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "updated successfuly!");
			
			}
		}
	}
}
