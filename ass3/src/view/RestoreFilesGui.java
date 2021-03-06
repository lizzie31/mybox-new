package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import Model.file;

public class RestoreFilesGui extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel=null;
	private JButton btnCancel=null;
	/**@param values is an array that saves all files names*/
	private String[] values = null;
	/**@param file is an array list of all the files*/
	private ArrayList<file> file;
	/**@param filesByName is array list all the files names*/
	private ArrayList<file> filesByName=null;
	/**@param list_1 is a list of all the files*/
	private JList list_1;
	private JLabel label;
	private JButton btnDescription;
	private JButton btnrestore;
	
/** file search gui constructor*/
/**@param f- all the files with the selected string*/
	public RestoreFilesGui(ArrayList f) {
		
		setTitle("Searched files by name");
		setForeground(SystemColor.inactiveCaption);
		this.file=f;
		
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,444);
		this.setContentPane(getCreatePanel());
		
		values=new String[file.size()];
		for(int i=0;i<file.size();i++){
			values[i]=file.get(i).getFileName();
		}
		
	}
	private JPanel getCreatePanel(){
		
		
		if(panel==null)
		{
			panel=new JPanel();
			panel.setBackground(new Color(135, 206, 235));
			panel.setLayout(null);
			
			btnrestore= new JButton("restore");
			btnrestore.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnrestore.setBounds(236, 95, 135, 34);
			panel.add(btnrestore);
			
			JLabel lblfilestorestore = new JLabel("files to restore:");
			lblfilestorestore.setHorizontalAlignment(SwingConstants.CENTER);
			lblfilestorestore.setFont(new Font("Arial Black", Font.PLAIN, 14));
			lblfilestorestore.setBounds(27, 11, 186, 23);
			panel.add(lblfilestorestore);
			
			btnCancel = new JButton("Back to main menu");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnCancel.setBounds(138, 360, 209, 34);
			panel.add(btnCancel);
		
			list_1 = new JList();
			list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			list_1.setBackground(new Color(176, 224, 230));
			list_1.setModel(new AbstractListModel() {
				
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list_1.setBounds(27, 45, 186, 273);
			panel.add(list_1);
			
			btnDescription = new JButton("show description");
			btnDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnDescription.setBounds(236, 45, 135, 34);
			panel.add(btnDescription);
			
			label = new JLabel("");
			label.setIcon(new ImageIcon(fileSearchGui.class.getResource("/view/searchgui.jpg")));
			label.setBounds(0, 0, 484, 405);
			panel.add(label);
		}
		return panel;
	}
	
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addBtnDescription(ActionListener l){
		btnDescription.addActionListener(l);
	}
	
	public void addbtnrestore(ActionListener l){
		btnrestore.addActionListener(l);
	}
	
	public void addListActionListener(ListSelectionListener e)
	{
		list_1.addListSelectionListener(e);
	}
	
	public JList getList_1() {
		return list_1;
	}

	public void setList_1(JList list_1) {
		this.list_1 = list_1;
	}

	/**close() closes the current window*/
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
