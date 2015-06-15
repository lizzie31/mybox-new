package view;

import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;




import java.awt.BorderLayout;




import javax.swing.JLabel;




import java.awt.Font;
import java.awt.event.ActionListener;




import javax.swing.JButton;
import javax.swing.AbstractListModel;




import Model.User;

import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class fileMenuGui extends JFrame {


	private JPanel panel=null;

	private JButton btnCancel=null;
	/**@param values is an array that saves all file names*/
	private String[] values = null;
	/**@param user is the current user*/
	private User user;
	
	private String FileName;
	private JButton btnCancel_1=null;
	private JButton btnRead=null;
	private JButton btnSetContent=null;




	public fileMenuGui(User u,String FileName) {
		this.FileName=FileName;
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

			btnRead = new JButton("open");
			btnRead.setBounds(26, 56, 137, 23);
			panel.add(btnRead);

			
			JButton btnUpdate = new JButton("set charecters");
			btnUpdate.setBounds(26, 108, 137, 23);
			panel.add(btnUpdate);

			JButton btnNewButton = new JButton("delete");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			btnNewButton.setBounds(184, 56, 137, 23);
			panel.add(btnNewButton);

			JLabel lblWhatIsThe = new JLabel("what is the action you want to do with the file:"+FileName);
			lblWhatIsThe.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblWhatIsThe.setBounds(28, 11, 411, 34);
			panel.add(lblWhatIsThe);

			JButton btnNewButton_1 = new JButton("set permission");
			btnNewButton_1.setBounds(184, 108, 137, 23);
			panel.add(btnNewButton_1);

			btnSetContent = new JButton("set content");
			btnSetContent.setBounds(337, 56, 137, 23);
			panel.add(btnSetContent);

			btnCancel_1 = new JButton("cancel");
			btnCancel_1.setBounds(337, 108, 137, 23);
			panel.add(btnCancel_1);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(fileMenuGui.class.getResource("/view/Computer-Background-Wallpapers-Ideas-Creative-Digital.jpg")));
			label.setBounds(0, 0, 484, 261);
			panel.add(label);

		}

		return panel;

	}

	public void addcancel(ActionListener l) {
		btnCancel_1.addActionListener(l);
	}
/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();

	}



	public void addread(ActionListener l) {
		btnRead.addActionListener(l);
	}
	
	public void addupdate(ActionListener l) {
		 btnSetContent.addActionListener(l);	
	}
	
	public JButton getbtnRead()
	{
		return this.btnRead;
	}
	

	public JButton getBtnSetContent() {
		return btnSetContent;
	}

	public void setBtnSetContent(JButton btnSetContent) {
		this.btnSetContent = btnSetContent;
	}

	

}


