package view;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
/**
 * this class is Graphic user interface of CCRM Client, which extends AbstractGUI
 *
 */
public class MyBoxClientGUI extends JFrame{
	
	/**labels, fields and buttons*/
	
	private JLabel LabelPort = null;
	private JLabel LabelHost = null;
	private JTextField TextHost = null;
	private JTextField TextPort = null;
	private JButton ButtonOk = null;
	private JButton ButtonCancel = null;
	/**
	 * Constructor
	 * add all labels, fields and buttons on the frame
	 */
	public MyBoxClientGUI() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		
		LabelPort = new JLabel("Port");
		LabelPort.setFont(new Font("Arial Black", Font.PLAIN, 13));
		LabelPort.setBounds(10, 40, 46, 14);
		getContentPane().add(LabelPort);
		
		LabelHost = new JLabel("Host");
		LabelHost.setFont(new Font("Arial Black", Font.PLAIN, 13));
		LabelHost.setBounds(10, 11, 46, 14);
		getContentPane().add(LabelHost);
		
		getContentPane().add(getTextHost());
		getContentPane().add(getTextPort());
		
		ButtonOk = new JButton("OK");
		ButtonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ButtonOk.setBounds(21, 105, 89, 23);
		getContentPane().add(ButtonOk);
		
		ButtonCancel = new JButton("Cancel");
		ButtonCancel.setBounds(139, 105, 89, 23);
		getContentPane().add(ButtonCancel);
		
		this.setTitle("My Box Connection to Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 260, 200);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/*****************************************Getters and Setters of Labels, Buttons,TextFields*****************************/
	public JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(68, 10, 160, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}

	public JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(66, 37, 160, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}
	
	public void clearFields(){
		TextHost.setText("");
		TextPort.setText("");
	}
	
	public String getHost(){
		return TextHost.getText();
	}
	
	public int getPort(){
		if(TextPort.getText().equals(""))
			return 0;
		return Integer.parseInt(TextPort.getText());
	}
	/**
	 * add to button ok Listener
	 * @param listener
	 */
	public void addOKActionListener(ActionListener listener){
		ButtonOk.addActionListener(listener);
	}
	/**
	 *  add to button cancel Listener
	 * @param listener
	 */
	public void addCancelActionListener(ActionListener listener){
		ButtonCancel.addActionListener(listener);
	}
	
	public void displayWarnningMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public JButton getButtonOk() {
		return ButtonOk;
	}

	public void setButtonOk(JButton buttonOk) {
		ButtonOk = buttonOk;
	}
}
