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
		//�ͻ����ڲ���Ϣ���ͣ�֪ͨ����������Ϣ
		IClientView iClientView = ManageAllView.getClientView("QQMainFace");	
		iClientView.procedureMsg(message);

	}

}
