package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.BorderLayout;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Model.User;
import Model.directories;
import Model.file;
import Model.interestGroups;
import controllers.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.JComboBox;
public class administratorMenuGUI extends JFrame {

	private JPanel MainMenu;
	/**@param searchField is the search text field*/
	private JTextField searchField;
	private JButton btnCreateNewFile=null;
	private JButton btnShowgroups=null;
	private JButton btnCreateNewFolder=null;
	private JButton btnAddleaveAGroup=null;
	private JButton btncratenewgroup=null;
	private JButton btnLogOut=null;
	private JLabel warningIcon=null;
	private User user;
	private JButton btnrequests = null;
	private ArrayList<interestGroups> allinterestgroups;
	/**@param userfiles is array list of all the user files*/
	private ArrayList<file> userfiles=null;
	private JButton editButton;
	private int arraysize;
	/**@param values is an array that saves all groups names*/
	private String[] values;
	private JButton deleteGroupButton=null;
	private DefaultMutableTreeNode node=null;

	private JComboBox comboBox;
	private JLabel warningmessege;

	private JTree tree=null;
	private JButton btnSearch;

	
	public administratorMenuGUI(User user,ArrayList<interestGroups> allinterestgroups ) {
	
		this.user=user;
		this.allinterestgroups=allinterestgroups;
		initialize();
		this.setVisible(true);
		

	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.CYAN);
		setBounds(400, 100, 300, 300);
		this.setSize(600,550);
		this.setTitle("main menu");;
		this.setContentPane(getMainMenu());
		

	}
	
	private JPanel getMainMenu(){
		if(MainMenu==null)
		{MainMenu=new JPanel();
        MainMenu.setBackground(new Color(102, 205, 170));
		MainMenu.setLayout(null);
		setJtree();
		tree.setBounds(42, 133, 205, 218);
		MainMenu.add(tree);
				
				warningIcon= new JLabel("");
				warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
				warningIcon.setBounds(38, 447, 30, 25);
				warningIcon.setVisible(false);
				
				comboBox = new JComboBox();
				comboBox.setBounds(43, 391, 128, 20);
				comboBox.addItem(" ");
				for (int i=0;i<allinterestgroups.size();i++)
				{
					comboBox.addItem(allinterestgroups.get(i).getGroupName());
				}
				
				warningmessege = new JLabel("3333333333333333333333333333");
				warningmessege.setForeground(new Color(255, 0, 0));
				warningmessege.setFont(new Font("Arial Black", Font.PLAIN, 14));
				warningmessege.setBounds(78, 447, 281, 25);
				warningmessege.setVisible(false);
				MainMenu.add(warningmessege);
				
				JLabel lblNewLabel = new JLabel("Intersts Groups List:");
				lblNewLabel.setForeground(new Color(0, 0, 205));
				lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
				lblNewLabel.setBounds(52, 355, 182, 25);
				MainMenu.add(lblNewLabel);
				MainMenu.add(comboBox);
				MainMenu.add(warningIcon);
				
				btnSearch = new JButton("search");
				btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnSearch.setBounds(388, 59, 97, 25);
				MainMenu.add(btnSearch);
				
				searchField = new JTextField();
				searchField.setBounds(213, 62, 146, 20);
				MainMenu.add(searchField);
				searchField.setColumns(10);
				
				JLabel lblSearch = new JLabel("search");
				lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblSearch.setBounds(99, 62, 104, 17);
				MainMenu.add(lblSearch);
				
				btnCreateNewFolder = new JButton("create new folder");
				btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
				btnCreateNewFolder.setBounds(376, 244, 138, 25);
				MainMenu.add(btnCreateNewFolder);
				
				btnLogOut = new JButton("log out");
				btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
				btnLogOut.setBounds(425, 466, 128, 23);
				MainMenu.add(btnLogOut);
		
		
				btnCreateNewFile = new JButton("create new file");
				btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnCreateNewFile.setBounds(376, 290, 138, 23);    
				btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
				MainMenu.add(btnCreateNewFile);

			
		
		JLabel lblHelloSystemAdministrtor = new JLabel("hello system administrtor!!");
		lblHelloSystemAdministrtor.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblHelloSystemAdministrtor.setBounds(129, 27, 233, 24);
		MainMenu.add(lblHelloSystemAdministrtor);
		
		btncratenewgroup = new JButton("create group");
		btncratenewgroup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btncratenewgroup.setBackground(UIManager.getColor("SplitPane.shadow"));
		btncratenewgroup.setBounds(376, 121, 138, 23);
		MainMenu.add(btncratenewgroup);
		
		deleteGroupButton = new JButton("delete group");
		deleteGroupButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteGroupButton.setBackground(UIManager.getColor("SplitPane.shadow"));
		deleteGroupButton.setBounds(376, 169, 138, 23);
		MainMenu.add(deleteGroupButton);
		
		editButton = new JButton("edit group");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editButton.setBackground(UIManager.getColor("SplitPane.shadow"));
		editButton.setBounds(208, 390, 138, 23);
		MainMenu.add(editButton);
		
		btnrequests = new JButton("requests");
		btnrequests.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnrequests.setBounds(376, 210, 138, 23);
		MainMenu.add(btnrequests);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(administratorMenuGUI.class.getResource("/view/2085-springlike-green-nature-butterfly-abstract-background.jpg")));
		label.setBounds(0, -12, 584, 574);
		MainMenu.add(label);
		
		
		}
		return MainMenu;
		
	}
	
	
	/**setJtree() sets the files and folders of the user*/
	public void setJtree()
	{
		DefaultMutableTreeNode root=adddirectorynode("from user",0,user.getuserItems());
		tree = new JTree(root);
		tree.setBackground(new Color(173, 216, 230));
        tree.setFont(new Font("Arial Black", Font.PLAIN, 14));
	  
		  
			

	}

	
 public DefaultMutableTreeNode adddirectorynode(String str,int i,directories di)
 {
	 int num=i;
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
    	  {
    		  node1.add(adddirectorynode("from dir",j,(directories)dir.getfiles().get(j)));
    	  }
    	  else
	      node1.add(new DefaultMutableTreeNode(((file)(dir.getfiles().get(j))).getFileName()));
		}
     
	    }
	 return (node1); 
	 
	 
}
  public void addeditgroup(ActionListener l) {
		editButton.addActionListener(l);
	}	
   public void addsearchfiles(ActionListener l) {
		btnSearch.addActionListener(l);
	}
	public void addDeletegroup(ActionListener l) {
		deleteGroupButton.addActionListener(l);
	}
	public void addcreatenewgroup(ActionListener l) {
		btncratenewgroup.addActionListener(l);
	}
	public void addrequests(ActionListener l) {
		btnrequests.addActionListener(l);
	}
	
	public void addcreatenewfile2(ActionListener l) {
		btnCreateNewFile.addActionListener(l);
	}
	public void addcreatenewfolder(ActionListener l) {
		btnCreateNewFolder.addActionListener(l);
	}
	
	public void addlogout(ActionListener l) {
		btnLogOut.addActionListener(l);
	}
	
	public String getTextField()
	{
		return searchField.getText();
	}
	/**addSelectGroup() combobox action listennet*/
	public void addSelectGroup(ActionListener e)
	{
		comboBox.addActionListener(e);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public void setWarningMessageVisibleTrue(String st) {
		warningmessege.setText(st);
		warningmessege.setForeground(Color.RED);
		warningmessege.setFont(new Font("Arial Black", Font.PLAIN, 14));
		warningmessege.setSize(400, 40);
		warningmessege.setBounds(81, 459, 281, 25);
		warningIcon.setVisible(true);
		warningmessege.setVisible(true);	
		
	}

	public JComboBox getComboBox() {
		return comboBox;
	}
	public User getUser() {
		return user;
	}
	

	
	public void undisplayWarningMessage() {
		warningIcon.setVisible(false);
		warningmessege.setVisible(false);
    }
}



