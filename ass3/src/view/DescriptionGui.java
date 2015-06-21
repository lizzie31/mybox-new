package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.fileSearchController;
import controllers.logInCon;
import Model.file;

import javax.swing.ImageIcon;

public class DescriptionGui extends JFrame{
	

	private logInCon l=null;
    private file file=null;
    private JPanel Panel=null;
    private fileSearchController prevCon;
    private DescriptionGui thisGui=this;
    

	public DescriptionGui(file f, fileSearchController prevCon) {
		this.file=f;
		this.prevCon=prevCon;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 300, 300);
		
		this.setSize(400,200);
		this.setContentPane(getFirstPanel());
		this.setTitle(""+file.getFileName()+" description");
		this.setVisible(true);
		}
	
 
 	private JPanel getFirstPanel() {
		if (Panel == null) {
			Panel = new JPanel();
			Panel.setBackground(SystemColor.inactiveCaption);
			Panel.setForeground(SystemColor.activeCaption);
			Panel.setLayout(null);
			
			JButton btnBack = new JButton("back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getThisGui().close();
					prevCon.getSearchG().setVisible(true);
					
				}
			});
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnBack.setBounds(140, 127, 89, 23);
			Panel.add(btnBack);
			
			JLabel lblDes_1 = new JLabel("");
			lblDes_1.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblDes_1.setBounds(10, 62, 46, 14);
			Panel.add(lblDes_1);
			
			JLabel lblDes = new JLabel(""+file.getDescription());
			lblDes.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblDes.setBounds(10, 37, 289, 14);
			Panel.add(lblDes);
			
			JLabel lblFileDescription = new JLabel(""+file.getFileName()+" description:");
			lblFileDescription.setFont(new Font("Arial Black", Font.PLAIN, 13));
			lblFileDescription.setBounds(79, 11, 179, 20);
			Panel.add(lblFileDescription);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(DescriptionGui.class.getResource("/view/little.jpg")));
			lblNewLabel.setBounds(0, 0, 384, 161);
			Panel.add(lblNewLabel);
			
		}
		return Panel; 
		
	}
 	
 	public DescriptionGui getThisGui() {
		return thisGui;
	}

	public void setThisGui(DescriptionGui thisGui) {
		this.thisGui = thisGui;
	}

 	/**close() closes the current window*/
 	public void close(){
 		this.setVisible(false);
 		dispose();
 	}

}
