package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodSignature implements ImessageMethod {
	private Message message;
	private List<Message> msgList = new ArrayList<Message>();
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		String pro = (String)message.getContext();
		String get = message.getGetter();
		try {
			DAOFactory.getIUserDAOInstance().updateSignature(pro, get);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}

}
