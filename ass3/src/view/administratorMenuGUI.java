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
	private JLabel warningIcon=null;
	private JLabel lblwarningMessage=null;
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
	private JTree tree=null;
	
	public administratorMenuGUI(User user) {
	
		this.user=user;
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
				warningIcon.setBounds(27, 464, 30, 25);
				warningIcon.setVisible(false);
				MainMenu.add(warningIcon);
				
				searchField = new JTextField();
				searchField.setBounds(236, 62, 146, 20);
				MainMenu.add(searchField);
				searchField.setColumns(10);
				
				MainMenu.add(getLblwarningMessage());
				
				JLabel lblSearch = new JLabel("search");
				lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblSearch.setBounds(120, 62, 104, 17);
				MainMenu.add(lblSearch);
				
				btnCreateNewFolder = new JButton("create new folder");
				btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
				btnCreateNewFolder.setBounds(346, 308, 138, 25);
				MainMenu.add(btnCreateNewFolder);
				
				btnLogOut = new JButton("log out");
				btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
				btnLogOut.setBounds(425, 466, 128, 23);
				MainMenu.add(btnLogOut);
		
		
				btnCreateNewFile = new JButton("create new file");
				btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnCreateNewFile.setBounds(346, 363, 138, 23);    
				btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
				MainMenu.add(btnCreateNewFile);

			
		
		JLabel lblHelloSystemAdministrtor = new JLabel("hello system administrtor!!");
		lblHelloSystemAdministrtor.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblHelloSystemAdministrtor.setBounds(129, 27, 233, 24);
		MainMenu.add(lblHelloSystemAdministrtor);
		
		btncratenewgroup = new JButton("create group");
		btncratenewgroup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btncratenewgroup.setBackground(UIManager.getColor("SplitPane.shadow"));
		btncratenewgroup.setBounds(346, 121, 138, 23);
		MainMenu.add(btncratenewgroup);
		
		deleteGroupButton = new JButton("delete group");
		deleteGroupButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteGroupButton.setBackground(UIManager.getColor("SplitPane.shadow"));
		deleteGroupButton.setBounds(346, 170, 138, 23);
		MainMenu.add(deleteGroupButton);
		
		btnNewButton_2 = new JButton("edit group");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(UIManager.getColor("SplitPane.shadow"));
		btnNewButton_2.setBounds(346, 212, 138, 23);
		MainMenu.add(btnNewButton_2);
		
		btnrequests = new JButton("requests");
		btnrequests.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnrequests.setBounds(346, 259, 138, 23);
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
					  
		  DefaultMutableTreeNode root= new DefaultMutableTreeNode(""+user.getUserName()+"'s files") ;
		  for(int i=0;i<user.getuserItems().size();i++)
		  {
			 if(user.getuserItems().get(i) instanceof directories)
			 {
			   root.add(adddirectorynode("from user",i,node,user));
			 }
			 else root.add(new DefaultMutableTreeNode(((file) ( user.getuserItems().get(i))).getFileName()));
		  }
			tree = new JTree(root);
			tree.setBackground(new Color(173, 216, 230));
	        tree.setFont(new Font("Arial Black", Font.PLAIN, 14));
	 }

					
	 public DefaultMutableTreeNode adddirectorynode(String str,int i,DefaultMutableTreeNode node,Object type)
	 {
		 int num=i;
		 directories dir=null;
		 DefaultMutableTreeNode node1=null;
		 if(str.equals("from user"))
		 {
			dir=((directories) user.getuserItems().get(num));
		    node1=new DefaultMutableTreeNode(""+(dir.getDirectoryName()));
		    if(dir.getfiles()!=null)
		    {
			 int arraysize=dir.getfiles().size();
			 for(int j=0;j<arraysize;j++)
			 {
			   if(dir.getfiles().get(j) instanceof directories)
				{
				   node1.add(adddirectorynode("from dir",j,node1,(directories)dir.getfiles().get(j)));
			    }
			   else
				 node1.add(new DefaultMutableTreeNode(((file)(dir.getfiles().get(j))).getFileName()));
							
			 }
		    }
		 }
		else
		 {
			dir=(directories)(type);
			node1=new DefaultMutableTreeNode(""+(dir.getDirectoryName()));
			if(dir.getfiles()!=null)
			{
			  int arraysize=dir.getfiles().size();
			  for(int j=0;j<arraysize;j++)
			  {
			    if(dir.getfiles().get(j) instanceof directories)
				  {
				    node1.add(adddirectorynode("from dir",j,node,dir));
				   }
			   else
					      node1.add(new DefaultMutableTreeNode(((file)(dir.getfiles().get(j))).getFileName()));
							
			  }
		    }
				     
		  }
		 return (node1); 
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
	
	public void addlogout(ActionListener l) {
		btnLogOut.addActionListener(l);
	}
	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public JLabel getLblwarningMessage() {
		if(lblwarningMessage == null){
			lblwarningMessage = new JLabel("");
			lblwarningMessage.setForeground(new Color(255, 0, 0));
			lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 13));
			lblwarningMessage.setSize(298, 25);
			lblwarningMessage.setLocation(67, 464);
			lblwarningMessage.setVisible(false);
		}
		return lblwarningMessage;
	}
	public void setWarningMessageVisibleTrue(String st) {
		lblwarningMessage.setText(st);
		lblwarningMessage.setForeground(Color.RED);
		lblwarningMessage.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblwarningMessage.setSize(303, 25);
		lblwarningMessage.setLocation(52, 83);
		warningIcon.setVisible(true);
		lblwarningMessage.setVisible(true);	
		
	}
	}


