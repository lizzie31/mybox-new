package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class warningGui extends JFrame {
/**
 * @param contentPane is the panel of warning message
 */
	private JPanel contentPane;
	private JButton btnOk = new JButton("ok");

	/**
	 * Create the application.
	 */
	public warningGui() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param lblErrorPleaseTry is a label that tells the user there was error and to try again
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErrorPleaseTry = new JLabel("error! please try again.");
		lblErrorPleaseTry.setBounds(144, 73, 167, 14);
		contentPane.add(lblErrorPleaseTry);
		
		btnOk.setBounds(156, 112, 89, 23);
		contentPane.add(btnOk);
	}
	
	public void close(){
 		this.setVisible(false);
 		dispose();
 	}
	public JButton getbtnOk()
	{
		return btnOk;
	}

}
