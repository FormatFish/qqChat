package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessaegMethodResponseAdd_refuse implements ImessageMethod {
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
		try {
			if(DAOFactory.getIUserDAOInstance().isonline(message.getSender())){
				message.setMessageType(MessageType.add_fail);
				message.setContext("对方不同意。。。");
				msgList.add(message);
			}else{
				messaged.setContext("对方不在线，没有加好友。。。");
				messaged.setGetter(message.getSender());
				messaged.setSender(message.getGetter());
				messaged.setMessageType(MessageType.add_fail);
				msgList.add(messaged);
			}
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
