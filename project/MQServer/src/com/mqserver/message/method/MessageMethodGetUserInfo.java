package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodGetUserInfo implements ImessageMethod {
	private Message message;
	private Message messaged = new Message();
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
		String str = (String)message.getContext();
		User user = new User();
		try {
			user = DAOFactory.getIUserDAOInstance().getUser(str);
			messaged.setContext(user);
			messaged.setGetter(message.getGetter());
			messaged.setSender(message.getGetter());
			messaged.setMessageType(MessageType.getUserInfo);
			msgList.add(messaged);
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
