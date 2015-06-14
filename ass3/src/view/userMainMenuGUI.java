package view;

import java.awt.AWTEvent;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.PopupMenu;





import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;






import java.awt.BorderLayout;






import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;






import java.awt.Font;






import javax.swing.JPanel;
import javax.swing.JPopupMenu;






import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;






import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;






import Model.User;
import Model.file;
import controllers.*;

import java.awt.Color;






import javax.swing.UIManager;






import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;






import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.AbstractListModel;

import java.awt.SystemColor;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.ImageIcon;

/**this class is the user main menu window*/
public class userMainMenuGUI extends JFrame {
	/**@param MainMenu is the pannel that shows user main menu*/
	private JPanel MainMenu;
	/**@param search is text field that user can enter letters or words in order to find a file*/
	private JTextField search;
	/**@param btnCreateNewFile is a button that is pressed if a user wants to create a new file*/
	private JButton btnCreateNewFile=null;
	/**@param btnShowgroups is a button the user can press in order to see the groups he is in */
	private JButton btnShowgroups=null;
	/**@param btnCreateNewFolder is a button in order to create new folder*/
	private JButton btnCreateNewFolder=null;
	/**@param btnJionGroup is a button in oreder to join a group*/
	private JButton btnJionGroup=null;
	/**@param btnSearch is a button in order to search the file the user entered*/
	private JButton btnSearch=null;
	private JButton open=null;
	private JButton btnLogOut=null;	
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;
	/**@param tree is files JTree*/
	private JTree tree=null;
	/**@param node is nodes in JTree*/
	private DefaultMutableTreeNode node=null;
	/**@param user is current user information*/
	private User user;

	/**constructor*/
	/**@param user is the current user information*/
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
		this.setSize(500,400);
		this.setTitle("main menu");;
		this.setContentPane(getMainMenu());
		

	}

	private JPanel getMainMenu(){
		if(MainMenu==null)
		{MainMenu=new JPanel();
        MainMenu.setBackground(new Color(135, 206, 235));
		MainMenu.setLayout(null);
		
		tree = new JTree();
		tree.setBackground(new Color(0, 206, 209));
		tree.setFont(new Font("Arial Black", Font.PLAIN, 14));
		setJtree();
						
								btnJionGroup = new JButton("join new group");
								btnJionGroup.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
									}
								});
        
        warningIcon= new JLabel("");
        warningIcon.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/warning.gif")));
        warningIcon.setBounds(23, 83, 30, 25);
        warningIcon.setVisible(false);
        MainMenu.add(warningIcon);
        
        MainMenu.add(getLblwarningMessage());
												
        btnCreateNewFile = new JButton("create new file");
        btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCreateNewFile.setBounds(307, 194, 138, 23);    
        btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
        MainMenu.add(btnCreateNewFile);		
										
												btnLogOut = new JButton("log out");
												btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
												btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
												btnLogOut.setBounds(385, 327, 89, 23);
												MainMenu.add(btnLogOut);
										
										btnShowgroups = new JButton("show Groups");
										btnShowgroups.setFont(new Font("Tahoma", Font.BOLD, 11));
										btnShowgroups.setBackground(UIManager.getColor("SplitPane.background"));
										btnShowgroups.setBounds(307, 122, 138, 25);
										MainMenu.add(btnShowgroups);		
								
										btnCreateNewFolder = new JButton("create new folder");
										btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
										btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
										btnCreateNewFolder.setBounds(307, 158, 138, 25);
										MainMenu.add(btnCreateNewFolder);
								btnJionGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
								btnJionGroup.setBackground(UIManager.getColor("SplitPane.background"));
								btnJionGroup.setBounds(307, 228, 138, 23);
								MainMenu.add(btnJionGroup);
				
						search = new JTextField();
						search.setBounds(162, 65, 166, 20);
						MainMenu.add(search);
						search.setColumns(10);	
		
				JLabel lblSearch = new JLabel("type the file name:");
				lblSearch.setFont(new Font("Arial Black", Font.PLAIN, 12));
				lblSearch.setBounds(23, 62, 139, 24);
				MainMenu.add(lblSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBackground(SystemColor.menu);
		btnSearch.setBounds(336, 63, 138, 25);
		MainMenu.add(btnSearch);
		tree.setBounds(35, 124, 205, 194);
		MainMenu.add(tree);
		
		JButton btnNewButton = new JButton("leave group");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(307, 262, 138, 23);
		MainMenu.add(btnNewButton);
		
		JLabel lblWelcomBack = new JLabel("welcom back "+user.getUserName()+"!!");
		lblWelcomBack.setForeground(new Color(0, 0, 0));
		lblWelcomBack.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblWelcomBack.setBounds(139, 21, 240, 23);
		MainMenu.add(lblWelcomBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(userMainMenuGUI.class.getResource("/view/Computer-Background-Wallpapers-Ideas-Creative-Digital.jpg")));
		label.setBounds(0, 0, 484, 361);
		MainMenu.add(label);
		}
		return MainMenu;	
	}
	
	public void addsearchfiles(ActionListener l) {
		btnSearch.addActionListener(l);
	}

	public void addcreatenewfile(ActionListener l) {
		btnCreateNewFile.addActionListener(l);
	}

	public void addcreatenewfolder(ActionListener l) {
		btnCreateNewFolder.addActionListener(l);
	}

	public void addjoingruop(ActionListener l) {
		btnJionGroup.addActionListener(l);
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
/**close() closes the current gui window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}

/** getTextField() returns the textFeild the user entered*/	
	public String getTextField()
	{
		return search.getText();
	}
/**setJtree() sets the files and folders of the user*/
	public void setJtree()
	{
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode(""+user.getUserName()+"'s files") {
					{
						for(int i=0;i<user.getuserDirectories().size();i++)
						{
							node=new DefaultMutableTreeNode(""+user.getuserDirectories().get(i).getDirectoryName());
							for(int j=0;j<user.getuserDirectories().get(i).getfiles().size();j++)
							{
								node.add(new DefaultMutableTreeNode(user.getuserDirectories().get(i).getfiles().get(j).getFileName()));
					
							}
						  add(node);
						}
					}
				}
			));
	}
	

/**getLblwarningMessage() returns the warning message*/
public JLabel getLblwarningMessage() {
	if(lblwarningMessage == null){
		lblwarningMessage = new JLabel("");
		lblwarningMessage.setForeground(new Color(255, 0, 0));
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblwarningMessage.setSize(327, 25);
		lblwarningMessage.setLocation(52, 83);
		lblwarningMessage.setVisible(false);
	}
	return lblwarningMessage;
}
/**setWarningMessageVisibleTrue(String st) sets the warning message st visible*/
public void setWarningMessageVisibleTrue(String st) {
	lblwarningMessage.setText(st);
	lblwarningMessage.setForeground(Color.RED);
	lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 11));
	lblwarningMessage.setSize(303, 25);
	lblwarningMessage.setLocation(52, 83);
	warningIcon.setVisible(true);
	lblwarningMessage.setVisible(true);	
	
}
/**undisplayWarningMessage() sets the warning message not visible*/
	public void undisplayWarningMessage() {
		warningIcon.setVisible(false);
	lblwarningMessage.setVisible(false);
	
}

	public JTree gettree() {
		return this.tree;
	}
}

	

