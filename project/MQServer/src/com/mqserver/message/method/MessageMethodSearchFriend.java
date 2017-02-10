package com.mqserver.message.method;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodSearchFriend implements ImessageMethod {
	private Message message;
	private List<Message> msgList = new ArrayList<Message>();

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}
	public void doMethod(){
		List<User> userList = new ArrayList<User>();
		User user = new User();
		String pro = (String)message.getContext();
		try{
			userList = DAOFactory.getIUserDAOInstance().searchFriend(pro);
			user.setUserList(userList);			//将返回的好友列表返回给客户端
			message.setContext(user);
			message.setMessageType(MessageType.searchFriend);
			message.setGetter(message.getSender());
			message.setSender(message.getGetter());
			msgList.add(message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

}
