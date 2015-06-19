package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controllers.createNewFileController;
import Model.User;
import Model.directories;

public class NewFileLocationGui extends JFrame{
	private JPanel panel;
	private JButton btnCancel=null;
	private JLabel label=null;
	private JLabel lblNewLabel=null;
	private JButton btnOk=null;
	private User user=null;
	private JTree tree=null;
	/**root of JTree*/
	private DefaultMutableTreeNode root=null;
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;
	private createNewFileController prevCon=null;



	public NewFileLocationGui(createNewFileController prevcon,User us) {
		this.user=us;
		this.prevCon=prevcon;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,442);
		this.setContentPane(getCreatePanel());
		
		btnOk = new JButton("ok");
		btnOk.setBounds(165, 300, 89, 23);
		panel.add(btnOk);
		
        panel.add(getLblwarningMessage());
        panel.add(getwarningIcon());
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(68, 300, 89, 23);
		panel.add(btnCancel);
		setJtree();
		panel.add(tree);
		
		label = new JLabel();
		label.setIcon(new ImageIcon(createNewFolderGUI.class.getResource("/view/long.jpg")));
		label.setBounds(0, 0, 384, 403);
		panel.add(label);
		
	}
	private JPanel getCreatePanel(){
		
	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			
			JLabel lblPleaseSelectThe = new JLabel("please select the file location:");
			lblPleaseSelectThe.setFont(new Font("Arial Black", Font.PLAIN, 13));
			lblPleaseSelectThe.setBounds(41, 11, 243, 14);
			panel.add(lblPleaseSelectThe);
		}
		return panel;
	}
	public void addbtnCancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addOk(ActionListener l) {
		btnOk.addActionListener(l);
	}
	
	public void addtreeSelectionListener(TreeSelectionListener TreeSelection)
	{
		tree.addTreeSelectionListener(TreeSelection);
	}

	
/**setJtree() sets the files and folders of the user*/
	public void setJtree()
	{
		directories Dir=user.getuserItems();
		root=adddirectorynode(Dir);
		tree = new JTree(root);
		tree.setBounds(68, 36, 186, 234);
		tree.setBackground(new Color(173, 216, 230));
        tree.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}

	
 public DefaultMutableTreeNode adddirectorynode(directories di)
 {
	 directories dir=null;
	 DefaultMutableTreeNode node1=null;
     dir=di;
	 node1=new DefaultMutableTreeNode(""+(dir.getDirectoryName()));
	 if(dir.getfiles()!=null)
	 {
	 int arraysize=dir.getfiles().size();
     for(int j=0;j<arraysize;j++)
		{
    	  if(dir.getfiles().get(j) instanceof directories)
    		  node1.add(adddirectorynode((directories)dir.getfiles().get(j)));
		}
     
	 }
	 return (node1); 
	 
	 
}
 
 public JTree gettree() {
		return this.tree;
	}
 
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
	
	
	public JLabel getwarningIcon()
	{
		    warningIcon= new JLabel("");
		    warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
	        warningIcon.setBounds(17, 367, 30, 25);
		    warningIcon.setVisible(false);
		    
		    return warningIcon;
	}
	
	/**getLblwarningMessage() returns the warning message*/
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblwarningMessage.setSize(327, 25);
		lblwarningMessage.setLocation(57, 367);
		warningIcon.setVisible(true);
		lblwarningMessage.setVisible(true);	
		
	}
	/**undisplayWarningMessage() sets the warning message not visible*/
		public void undisplayWarningMessage() {
			warningIcon.setVisible(false);
		lblwarningMessage.setVisible(false);
		
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}

}
