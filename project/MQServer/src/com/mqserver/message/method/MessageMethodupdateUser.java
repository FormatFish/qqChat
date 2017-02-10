package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodupdateUser implements ImessageMethod {
	private Message message;
	private Message messaged = new Message();
	private List<Message> msgList = new ArrayList<Message>();
	private User user;
	private User user1;
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		user = (User)message.getContext();
		try {
			DAOFactory.getIUserDAOInstance().updateUser(user);
			user1 = DAOFactory.getIUserDAOInstance().getUser(user.getUserId());
			messaged.setContext(user1);
			messaged.setGetter(user.getUserId());
			messaged.setSender(user.getUserId());
			messaged.setMessageType(MessageType.other);
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
