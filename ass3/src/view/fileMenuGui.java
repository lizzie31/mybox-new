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
	private JButton btndelete;
	private String FileName;
	private JButton btnCancel_1=null;
	private JButton btnRead=null;
	private JButton btnSetContent=null;
	private JButton btnpermission;
	private JButton btnSetChrarcters;




	public fileMenuGui(User u,String FileName) {
		this.FileName=FileName;
		this.user=u;
		initialize();
		this.setVisible(true);
		

	}

	private void initialize() {
		this.setBounds(400,200,300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(524,262);
		this.setContentPane(getCreatePanel());
	}

	private JPanel getCreatePanel(){
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(SystemColor.inactiveCaption);
			panel.setLayout(null);
			this.setTitle("file menu");

			btnRead = new JButton("open");
			btnRead.setBounds(10, 56, 137, 23);
			panel.add(btnRead);

			
			btnSetChrarcters = new JButton("set charecters");
			btnSetChrarcters.setBounds(10, 108, 137, 23);
			panel.add(btnSetChrarcters);

			btndelete = new JButton("delete");
			btndelete.setBounds(169, 56, 137, 23);
			panel.add(btndelete);

			JLabel lblWhatIsThe = new JLabel("what is the action you want to do with the file:"+FileName);
			lblWhatIsThe.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblWhatIsThe.setBounds(28, 11, 411, 34);
			panel.add(lblWhatIsThe);

			btnpermission = new JButton("set permission");
			btnpermission.setBounds(169, 108, 137, 23);
			panel.add(btnpermission);

			btnSetContent = new JButton("set content");
			btnSetContent.setBounds(316, 56, 137, 23);
			panel.add(btnSetContent);

			btnCancel_1 = new JButton("cancel");
			btnCancel_1.setBounds(316, 108, 137, 23);
			panel.add(btnCancel_1);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(fileMenuGui.class.getResource("/view/lottle2.jpg")));
			label.setBounds(0, 0, 508, 223);
			panel.add(label);

		}

		return panel;

	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();

	}

	public void addsetCharacters(ActionListener l) {
		btnSetChrarcters.addActionListener(l);
	}

	public void addpermission(ActionListener l) {
		btnpermission.addActionListener(l);
	}

	public void addcancel(ActionListener l) {
		btnCancel_1.addActionListener(l);
	}

	public void adddelete(ActionListener l) {
		btndelete.addActionListener(l);
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


