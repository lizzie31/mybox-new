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
	
	private logInCon prevCon;
	private forgget_password forPassGui;
	private String userMail;
	private forgetPassCon FPC;
	private Envelope en;

	public forgetPassCon(forgget_password fp,logInCon lc)
			{
		      FPC=this;
		      prevCon=lc;
		      forPassGui=fp;
		      forPassGui.addOkActionListener(new OkListener());
		      forPassGui.addCancelActionListener(new CancelListener());
			}
	
	class OkListener implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			userMail=forPassGui.getTextUserMail();
			if(userMail.equals(""))
			{
			 forPassGui.setWarningMessageVisibleTrue("please enter your mail!");	
			}
			else
			 en=new Envelope(FPC,"forgotPass");
			 sendToServer(en);
			 myboxapp.clien.setCurrObj(getTempFPC());
	     }
	}
		
	class CancelListener implements ActionListener {

		public void actionPerformed(ActionEvent ev)
		{
			forPassGui.dispose();
			prevCon.getLoginG().setVisible(true);		
		}
	 }

		private forgetPassCon getTempFPC() {
			// TODO Auto-generated method stub
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
