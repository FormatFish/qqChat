package com.mqserver.message.method;

import java.util.*;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodShowFriend implements ImessageMethod{
	private Message message;
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
		List<User> userList = new ArrayList<User>();
		User user = new User();
		//User u = null;
		String pro = message.getGetter();//��ȡ��½��id
		try{
			userList = DAOFactory.getIUserDAOInstance().showFriend(pro);
			user.setUserList(userList);			//�����صĺ����б��ظ��ͻ���
			message.setContext(user);
			message.setMessageType(MessageType.show_succ);
			message.setGetter(message.getSender());
			message.setSender(message.getGetter());
			message.setSendTime((new Date()).toString());
			msgList.add(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
