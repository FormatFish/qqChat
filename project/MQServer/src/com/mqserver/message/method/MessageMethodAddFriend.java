package com.mqserver.message.method;


import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodAddFriend implements ImessageMethod {
	private Message message;
	private Message messaged = new Message();
	private List<Message> msgList = new ArrayList<Message>();

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}

	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		User user;
		user = (User)message.getContext();
		String get = message.getGetter();
		String send = user.getUserId();
		try {
			if(DAOFactory.getIUserDAOInstance().isFriend(get, send)){
				if(DAOFactory.getIUserDAOInstance().isonline(user.getUserId())){
					messaged.setGetter(message.getGetter());
					messaged.setSender(user.getUserId());
					messaged.setContext(user);
					messaged.setMessageType(MessageType.addFriend);
				}else{
					messaged.setMessageType(MessageType.add_fail);
					messaged.setGetter(message.getGetter());
					messaged.setSender(message.getGetter());
					messaged.setContext("对方不在线，请对方在线时再添加。。。");
				}
				
			}else{
				messaged.setMessageType(MessageType.add_fail);
				messaged.setGetter(message.getGetter());
				messaged.setSender(message.getGetter());
				messaged.setContext("已添加对方为好友、、、");
			}
			msgList.add(messaged);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
