package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Model.Envelope;
import client.myboxapp;
import controllers.logInCon;
import view.forgget_password;
import view.logInGui;

public class forgetPassCon extends AbstractTransfer{
	/**prevCon is log in controller*/
	private logInCon prevCon;
	/**forPassGui is forget password gui window**/
	private forgget_password forPassGui;
	/**userMail saves the user email that he entered**/
	private String userMail;
	/**FPC is forget password controller show*/
	private forgetPassCon FPC;
	private Envelope en;

	/**constructor*/
	public forgetPassCon(forgget_password fp,logInCon lc)
			{
		      FPC=this;
		      prevCon=lc;
		      forPassGui=fp;
		      forPassGui.addOkActionListener(new OkListener());
		      forPassGui.addCancelActionListener(new CancelListener());
			}
	/*******************action listeners************************/
	/**button listener of ok */
	class OkListener implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			userMail=forPassGui.getTextUserMail();
			if(userMail.equals(""))//if the user didn't enter email
			 forPassGui.setWarningMessageVisibleTrue("please enter your mail!");	
			else
			 en=new Envelope(FPC,"forgotPass");
			sendToServer(en);
			myboxapp.clien.setCurrObj(getTempFPC());
	     }
	}
	/**button listener of cancel*/
	class CancelListener implements ActionListener {

		public void actionPerformed(ActionEvent ev)
		{
			forPassGui.dispose();
			prevCon.getLoginG().setVisible(true);		
		}
	 }

	/***********getters and setters************/
	private forgetPassCon getTempFPC() {
		return FPC;
	}
		
	public String getUserMail()
	{
		return userMail;
	}
	public forgget_password getforPassGui() {
		return forPassGui;
	}
	
}
