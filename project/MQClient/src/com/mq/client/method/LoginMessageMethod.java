package com.mq.client.method;

import com.mq.client.common.Message;


import com.mq.client.factory.ImessageMethod;
import com.mq.client.util.IClientView;
import com.mq.client.view.*;
import com.mq.client.Manage.*;
public class LoginMessageMethod implements ImessageMethod {
	private Message message;
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
	}
	

	public void Deal_Msg() throws Exception
	{
		
		if(ManageAllView.isFound("loginView1")){
		IClientView iClientView = ManageAllView.getClientView("loginView1");
		
		iClientView.procedureMsg(getMessage());}
		else{
			IClientView iClientView = ManageAllView.getClientView("QQMainFace");
			iClientView.procedureMsg(getMessage());
		}
		
		

		
	}
	@Override
	public Message getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	
}
