package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.User;
import Model.file;

public class UpdateGui extends JFrame{
	private JPanel panel=null;

	private JButton btnCancel=null;
	/**@param values is an array that saves all file names*/
	private String[] values = null;
	/**@param user is the current user*/
	private User user;
	
	private file ChoosenFile;
	private JButton btnRead=null;
	private JButton btnSetContent=null;
	private JButton btnChooser=null;
	private  JFileChooser fileChooser = new JFileChooser();
	private JButton btnUpdate=null;





	public UpdateGui(User u,file choosenFile) {
		this.ChoosenFile=choosenFile;
		this.user=u;
		initialize();
		this.setVisible(true);
		

	}



	/**

	 * Initialize the contents of the frame.

	 */

	private void initialize() {
		this.setBounds(400,200,300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setContentPane(getCreatePanel());
	}

	private JPanel getCreatePanel(){
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(SystemColor.inactiveCaption);
			panel.setLayout(null);
			
			btnCancel= new JButton("cancel");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnCancel.setBounds(147, 122, 89, 23);
			panel.add(btnCancel);
			
			btnUpdate = new JButton("update");
			btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnUpdate.setBounds(246, 122, 89, 23);
			panel.add(btnUpdate);
			
			btnChooser = new JButton("choose the file");
			btnChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnChooser.setBounds(174, 47, 134, 39);
			panel.add(btnChooser);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(UpdateGui.class.getResource("/view/Multicolor Grass Books.jpg")));
			label.setBounds(0, 0, 484, 261);
			panel.add(label);

		}

		return panel;

	}


/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();

	}

	public void addbtnChooser(ActionListener l) {
		 btnChooser.addActionListener(l);	
	}
	
	public void addbtnUpdate(ActionListener l) {
		 btnUpdate.addActionListener(l);	
	}
	
	public void addbtnCancel(ActionListener l) {
		 btnCancel.addActionListener(l);	
	}
	

	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}



	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	
 	/**showsuceedmessege() shows a message that the group was added sucssesfuly to the DB*/
 	public void showsuceedmessege()
 	{
 		Component frame=null;
 	   JOptionPane.showMessageDialog(frame, "the file was updated sucssefuly!!");
 	   this.close();
 	}

}


