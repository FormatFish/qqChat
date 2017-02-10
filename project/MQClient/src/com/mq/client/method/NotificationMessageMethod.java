package com.mq.client.method;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageNotification;
import com.mq.client.common.Message;
import com.mq.client.factory.ImessageMethod;
import com.mq.client.util.IClientView;

public class NotificationMessageMethod implements ImessageMethod {

	private Message message;
	
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	@Override
	public Message getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void Deal_Msg() throws Exception {
		
		ManageNotification.addnotification(message);
		//客户端内部消息类型，通知主界面有消息
		IClientView iClientView = ManageAllView.getClientView("QQMainFace");	
		iClientView.procedureMsg(message);

	}

}
