package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.BorderLayout;

import javax.swing.AbstractListModel;
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
import Model.file;
import controllers.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.UIManager;
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
	private User user;
	private JButton btnrequests = null;
	/**@param userfiles is array list of all the user files*/
	private ArrayList<file> userfiles=null;
	private JButton btnNewButton_2;
	private int arraysize;
	/**@param values is an array that saves all groups names*/
	private String[] values;
	private JButton deleteGroupButton=null;
	private DefaultMutableTreeNode node=null;
	
	public administratorMenuGUI(User user) {
		this.setSize(500, 500);
		this.user=user;
		initialize();
		this.setVisible(true);
		

	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.CYAN);
		setBounds(100, 100, 450, 300);
		this.setSize(500,400);
		this.setTitle("main menu");;
		this.setContentPane(getMainMenu());


		btnCreateNewFile = new JButton("create new file");
		btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNewFile.setBounds(307, 250, 132, 23);    
		btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
		MainMenu.add(btnCreateNewFile);
		
		btnLogOut = new JButton("log out");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
		btnLogOut.setBounds(307, 284, 89, 23);
		MainMenu.add(btnLogOut);
		
		btnCreateNewFolder = new JButton("create new folder");
		btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
		btnCreateNewFolder.setBounds(307, 214, 138, 25);
		MainMenu.add(btnCreateNewFolder);
		
		searchField = new JTextField();
		searchField.setBounds(198, 46, 146, 20);
		MainMenu.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblSearch = new JLabel("search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setBounds(105, 46, 104, 17);
		MainMenu.add(lblSearch);
		

	}
	
	private JPanel getMainMenu(){
		if(MainMenu==null)
		{MainMenu=new JPanel();
MainMenu.setBackground(new Color(102, 205, 170));
		MainMenu.setLayout(null);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(""+user.getUserName()+"files") {
				{
					for(int i=0;i<user.getuserDirectories().size();i++)
					{
						node=new DefaultMutableTreeNode(""+user.getuserDirectories().get(i).getDirectoryName());
						for(int j=0;j<user.getuserDirectories().get(i).getfiles().size();j++)
						{
							node.add(new DefaultMutableTreeNode(""+user.getuserDirectories().get(i).getfiles().get(j).getFileName()));
				
						}
						add(node);
					}
				}
			}
		));
		tree.setBounds(29, 113, 190, 194);
		MainMenu.add(tree);
		
		JLabel lblHelloSystemAdministrtor = new JLabel("hello system administrtor");
		lblHelloSystemAdministrtor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHelloSystemAdministrtor.setBounds(111, 11, 187, 24);
		MainMenu.add(lblHelloSystemAdministrtor);
		
		btncratenewgroup = new JButton("create group");
		btncratenewgroup.setBackground(UIManager.getColor("SplitPane.shadow"));
		btncratenewgroup.setBounds(326, 77, 113, 23);
		MainMenu.add(btncratenewgroup);
		
		deleteGroupButton = new JButton("delete group");
		deleteGroupButton.setBackground(UIManager.getColor("SplitPane.shadow"));
		deleteGroupButton.setBounds(326, 109, 113, 23);
		MainMenu.add(deleteGroupButton);
		
		btnNewButton_2 = new JButton("edit group");
		btnNewButton_2.setBackground(UIManager.getColor("SplitPane.shadow"));
		btnNewButton_2.setBounds(326, 143, 113, 23);
		MainMenu.add(btnNewButton_2);
		
		btnrequests = new JButton("requests");
		btnrequests.setBounds(326, 177, 113, 23);
		MainMenu.add(btnrequests);
		
		}
		return MainMenu;
		
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
	
	public void addcreatenewfile(ActionListener l) {
		btnCreateNewFile.addActionListener(l);
	}
	public void addcreatenewfolder(ActionListener l) {
		btnCreateNewFolder.addActionListener(l);
	}
	public void addleaveEntergruop(ActionListener l) {
		btnAddleaveAGroup.addActionListener(l);
	}
	public void addlogout(ActionListener l) {
		btnLogOut.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	}


