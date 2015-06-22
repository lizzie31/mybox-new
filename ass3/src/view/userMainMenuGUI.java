package view;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;


import javax.swing.tree.MutableTreeNode;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;






import java.awt.Component;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Model.User;
import Model.directories;
import Model.file;
import controllers.*;

import java.awt.Color;

import javax.swing.UIManager;

import java.io.IOException;
import java.util.ArrayList;


import java.awt.SystemColor;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.ImageIcon;

/**this class is the user main menu window*/
public class userMainMenuGUI extends JFrame {
	/**MainMenu is the pannel that shows user main menu*/
	private JPanel MainMenu;
	/**search is text field that user can enter letters or words in order to find a file*/
	private JTextField search;
	/**btnCreateNewFile is a button that is pressed if a user wants to create a new file*/
	private JButton btnCreateNewFile=null;
	/**btnShowgroups is a button the user can press in order to see the groups he is in */
	private JButton btnShowgroups=null;
	/**btnCreateNewFolder is a button in order to create new folder*/
	private JButton btnCreateNewFolder=null;
	/**btnJionGroup is a button in oreder to join a group*/
	private JButton btnJionGroup=null;
	/**btnSearch is a button in order to search the file the user entered*/
	private JButton btnSearch=null;
	/**buttons on window**/
	private JButton btnLogOut=null;	
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;
	private JButton btnleaveButton=null;
	private JButton btnRestoreFiles=null;
	private JLabel lblSearch;
	private JLabel lblWelcomBack ;
	private JLabel label;
	/**tree is files JTree*/
	private JTree tree=null;
	/**nodes in JTree*/
	private DefaultMutableTreeNode node=null;
	/**@param user is current user information*/
	private User user;


	/**constructor
	 * 
	 * @param user
	 */
	public userMainMenuGUI(User user) {
		this.user=user;
		initialize();
		if (user.getUserName().compareTo("nofar")!=0)
			this.setVisible(true);
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.CYAN);
		setBounds(400, 200, 300, 300);
		this.setSize(600,450);
		this.setTitle("main menu");;
		this.setContentPane(getMainMenu());
	}

	private JPanel getMainMenu(){
	  if(MainMenu==null)
	  {
		MainMenu=new JPanel();
        MainMenu.setBackground(new Color(135, 206, 235));
		MainMenu.setLayout(null);
         
        MainMenu.add(btnRestoreFiles);
        MainMenu.add(getLblwarningMessage());
        MainMenu.add(getwarningIcon());   
        MainMenu.add(getbtnSearch());  
        MainMenu.add(getbtnCreateNewFile());										
		MainMenu.add(getLogOutbtn());
		MainMenu.add(getRestoreFilesbtn());
		MainMenu.add(getShowGroupsbtn());
		MainMenu.add(getCreateFolderbtn());								
		MainMenu.add(getJoinGroupbtn());	
		MainMenu.add(getSearchText());
		MainMenu.add(getSearchLable());
		MainMenu.add(getbtnleaveButton());
		MainMenu.add(getlblWelcomBack());
		MainMenu.add(getlabel());
		setJtree();
		tree.setBounds(42, 133, 205, 218);
		MainMenu.add(tree);
		

		
		}
		return MainMenu;	
	}

	
	/*************************************************buttons action listeners***********************************/
	public void addsearchfiles(ActionListener l) {
		btnSearch.addActionListener(l);
	}

	public void addcreatenewfile(ActionListener l) {
		btnCreateNewFile.addActionListener(l);
	}

	public void addcreatenewfolder(ActionListener l) {
		btnCreateNewFolder.addActionListener(l);
	}
	
	public void addbtnRestoreFiles (ActionListener l) {
		btnRestoreFiles.addActionListener(l);
	}
	public void addjoingruop(ActionListener l) {
		btnJionGroup.addActionListener(l);
	}
	public void addleavegruop(ActionListener l) {
		btnleaveButton.addActionListener(l);
	}


	public void addshowgruops(ActionListener l) {
		btnShowgroups.addActionListener(l);
	}

	public void addLogOut(ActionListener l) {
		btnLogOut.addActionListener(l);	
	}

	
	public void addtreeSelectionListener(TreeSelectionListener TreeSelection)
	{
		tree.addTreeSelectionListener(TreeSelection);
	}
	
/**********************************************************getters and setters*****************************************/	
	public JLabel getwarningIcon()
	{
		    warningIcon= new JLabel("");
		    warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
	        warningIcon.setBounds(34, 97, 30, 25);
		    warningIcon.setVisible(false);
		    
		    return warningIcon;
	}
	public JLabel getSearchLable()
	{
		lblSearch = new JLabel("Type the file name:");
		lblSearch.setForeground(new Color(0, 0, 0));
		lblSearch.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblSearch.setBounds(24, 65, 142, 24);
		return lblSearch;
		
	}
	public JLabel getlblWelcomBack()
	{
		lblWelcomBack = new JLabel("Wellcom back "+user.getUserName()+"!!");
		lblWelcomBack.setForeground(new Color(0, 0, 0));
		lblWelcomBack.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblWelcomBack.setBounds(164, 22, 250, 33);
		return lblWelcomBack;
	}
	public JButton getRestoreFilesbtn()
	{
	    btnRestoreFiles = new JButton("restore files");
        btnRestoreFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnRestoreFiles.setBounds(277, 328, 138, 28);
        return btnRestoreFiles;
	}
	public JLabel getlabel()
	{
		label = new JLabel("");
		label.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/Multicolor Grass Books.jpg")));
		label.setBounds(0, 0, 584, 422);
		return this.label;
	}
	public JButton getbtnleaveButton()
	{
		btnleaveButton = new JButton("Leave group");
		btnleaveButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnleaveButton.setBounds(277, 289, 138, 28);
		return btnleaveButton;
		
	}
	public JTextField getSearchText()
	{
		
		search = new JTextField();
		search.setBounds(176, 67, 165, 25);
		search.setColumns(10);
		return search;	
	}
	public JButton getJoinGroupbtn()
	{
		btnJionGroup = new JButton("Join new group");
		btnJionGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnJionGroup.setBackground(UIManager.getColor("SplitPane.background"));
		btnJionGroup.setBounds(277, 250, 138, 28);
		return btnJionGroup;
	}
	public JButton getCreateFolderbtn()
	{
		 btnCreateNewFolder = new JButton("Create new folder");
		    btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
			btnCreateNewFolder.setBounds(277, 172, 138, 28);
			return btnCreateNewFolder;
			
	}
	public JButton getShowGroupsbtn()
	{
		btnShowgroups = new JButton("Show groups");
		btnShowgroups.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowgroups.setBackground(UIManager.getColor("SplitPane.background"));
		btnShowgroups.setBounds(277, 133, 138, 28);
		return btnShowgroups;		
	}
	
	public JButton getbtnSearch()
	{
	    btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSearch.setBackground(SystemColor.menu);
        
        return btnSearch;   
     }

	public JButton getLogOutbtn()
	{
		 btnLogOut = new JButton("Log Out");
		    btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		    btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
			btnLogOut.setBounds(459, 364, 96, 25);
			return btnLogOut;
	}
	public JButton getbtnCreateNewFile()
	{
       btnCreateNewFile = new JButton("Create new file");
       btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
       btnCreateNewFile.setBounds(277, 211, 138, 28);    
       btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
    
       return btnCreateNewFile;
    
	}

/** getTextField() returns the textFeild the user entered*/	
	public String getTextField()
	{
		return search.getText();
	}
	
	public JTree gettree() {
		return this.tree;
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
	lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 12));
	lblwarningMessage.setSize(400, 25);
	lblwarningMessage.setLocation(74, 97);
	warningIcon.setVisible(true);
	lblwarningMessage.setVisible(true);	
	
}
/**undisplayWarningMessage() sets the warning message not visible*/
	public void undisplayWarningMessage() {
		warningIcon.setVisible(false);
	lblwarningMessage.setVisible(false);
	
}
	
	/**close() closes the current gui window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
}

	

