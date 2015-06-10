package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class deleteGroupGUI extends JFrame{

	private JFrame frame;
	private JButton btnOk;
	private JComboBox comboBox;
	private JLabel lblChooseAGroup;
	private JButton btnCancel;
	private JPanel panel;
	
	public deleteGroupGUI() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 345, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setContentPane(getCreatePanel());
		
		JLabel lblGroupName = new JLabel("group name:");
		lblGroupName.setBounds(25, 43, 104, 16);
		comboBox = new JComboBox();
		comboBox.setBounds(185, 41, 123, 22);
		panel.add(comboBox);
		
		lblChooseAGroup = new JLabel("choose a group");
		lblChooseAGroup.setBounds(24, 44, 108, 16);
		panel.add(lblChooseAGroup);
		
		btnCancel = new JButton("cancel");
		btnCancel.setBounds(43, 171, 97, 25);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("ok");
		btnOk.setBounds(269, 171, 97, 25);
		panel.add(btnOk);
	}
	private JPanel getCreatePanel(){	
		if(panel==null)
		{
			panel=new JPanel();
			panel.setLayout(null);
			this.setTitle("deleteGroup");
		}
		return panel;
	}
	public void addcancel(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
}
