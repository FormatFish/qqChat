package com.mq.client.method;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.common.Message;
import com.mq.client.factory.ImessageMethod;
import com.mq.client.util.IClientView;

public class RegisterMessageMethod implements ImessageMethod {
	private Message message;
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	@Override
	public Message getMessage() {
		return this.message;
	}

	@Override
	public void Deal_Msg() throws Exception {
		
        IClientView iClientView = ManageAllView.getClientView("registerView2");
		iClientView.procedureMsg(getMessage());
	}

}
