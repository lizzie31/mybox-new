package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

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
	private JTextField textField;
	private JButton btnCreateNewFile=null;
	private JButton btnShowgroups=null;
	private JButton btnCreateNewFolder=null;
	private JButton btnAddleaveAGroup=null;
	private JButton btncratenewgroup=null;
	private JButton btnLogOut=null;
	private User user;
	private JButton btnrequests = null;
	private ArrayList<file> userfiles=null;
	private JButton btnNewButton_2;
	private int arraysize;
	private String[] values;
	private JButton deleteGroupButton=null;
	JList list=null;
	
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

		userfiles=user.getFilesInDB();
        arraysize=user.getFilesInDB().size();
        values = new String[arraysize];
        for(int i=0;i<userfiles.size();i++)
		{
			values[i]=userfiles.get(i).getFileName();
		}
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
		
		textField = new JTextField();
		textField.setBounds(198, 46, 146, 20);
		MainMenu.add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setBounds(105, 46, 104, 17);
		MainMenu.add(lblSearch);
		

	}
	
	private JPanel getMainMenu(){
		if(MainMenu==null)
		{MainMenu=new JPanel();
MainMenu.setBackground(new Color(152, 251, 152));
		MainMenu.setLayout(null);

		list = new JList();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(54, 140, 168, 132);
		MainMenu.add(list);
		
		JLabel lblHelloSystemAdministrtor = new JLabel("hello system administrtor");
		lblHelloSystemAdministrtor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHelloSystemAdministrtor.setBounds(111, 11, 187, 24);
		MainMenu.add(lblHelloSystemAdministrtor);
		
		btncratenewgroup = new JButton("create group");
		btncratenewgroup.setBackground(UIManager.getColor("SplitPane.shadow"));
		btncratenewgroup.setBounds(338, 77, 101, 23);
		MainMenu.add(btncratenewgroup);
		
		deleteGroupButton = new JButton("delete group");
		deleteGroupButton.setBackground(UIManager.getColor("SplitPane.shadow"));
		deleteGroupButton.setBounds(338, 109, 101, 23);
		MainMenu.add(deleteGroupButton);
		
		btnNewButton_2 = new JButton("edit group");
		btnNewButton_2.setBackground(UIManager.getColor("SplitPane.shadow"));
		btnNewButton_2.setBounds(338, 143, 101, 23);
		MainMenu.add(btnNewButton_2);
		
		btnrequests = new JButton("requests");
		btnrequests.setBounds(338, 177, 101, 23);
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
	public void close() {
		this.setVisible(false);
		dispose();
	}
	}


