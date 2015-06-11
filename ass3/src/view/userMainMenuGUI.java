package view;

import java.awt.AWTEvent;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.PopupMenu;




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

/**this class is the user main menu window*/
public class userMainMenuGUI extends JFrame {
	/**main menu*/
	private JPanel MainMenu;
	/**search text field*/
	private JTextField search;
	/**window buttons*/
	private JButton btnCreateNewFile=null;
	private JButton btnShowgroups=null;
	private JButton btnCreateNewFolder=null;
	private JButton btnAddleaveAGroup=null;
	private JButton btnSearch=null;
	private JButton open=null;
	private JButton btnLogOut=null;	
	/**files JTree*/
	private JTree tree=null;
	/** nodes in JTree*/
	private DefaultMutableTreeNode node=null;
	/**current user information*/
	private User user;

	/**constructor*/
	/*@param user*/
	public userMainMenuGUI(User user) {
		this.user=user;
		this.setSize(500, 500);
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

		btnAddleaveAGroup = new JButton("join new group");
		btnAddleaveAGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddleaveAGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddleaveAGroup.setBackground(UIManager.getColor("SplitPane.background"));
		btnAddleaveAGroup.setBounds(307, 228, 138, 23);
		MainMenu.add(btnAddleaveAGroup);

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
	}

	private JPanel getMainMenu(){
		if(MainMenu==null)
		{MainMenu=new JPanel();
        MainMenu.setBackground(new Color(135, 206, 235));
		MainMenu.setLayout(null);
		
		tree = new JTree();
		tree.setBackground(new Color(0, 191, 255));
		tree.setFont(new Font("Arial Black", Font.PLAIN, 14));
		setJtree();
		tree.setBounds(35, 124, 240, 194);
		MainMenu.add(tree);
		
		JButton btnNewButton = new JButton("leave group");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(307, 262, 138, 23);
		MainMenu.add(btnNewButton);
		
		JLabel lblWelcomBack = new JLabel("welcom back "+user.getUserName()+"!!");
		lblWelcomBack.setForeground(new Color(0, 0, 255));
		lblWelcomBack.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblWelcomBack.setBounds(134, 21, 240, 23);
		MainMenu.add(lblWelcomBack);
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

	public void addleaveEntergruop(ActionListener l) {
		btnAddleaveAGroup.addActionListener(l);
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

	public void close() {
		this.setVisible(false);
		dispose();
	}

	
	public String getTextField()
	{
		return search.getText();
	}

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

	public JTree gettree() {
		return this.tree;
	}
}

	

