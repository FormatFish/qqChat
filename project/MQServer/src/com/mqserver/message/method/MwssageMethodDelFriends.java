package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MwssageMethodDelFriends implements ImessageMethod {
	private Message message;
	private Message message1 = new Message();
	private Message message2 = new Message();
	private Message messaged1 = new Message();
	private List<Message> msgList = new ArrayList<Message>();
	private MessageMethodShowFriend mmsf = new MessageMethodShowFriend();
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		try{
			String get = message.getGetter();
			String send = (String) message.getContext();
			DAOFactory.getIUserDAOInstance().delFriend(get, send);
			DAOFactory.getIUserDAOInstance().delFriend(send, get);
			messaged1.setContext("É¾³ý³É¹¦");
			messaged1.setGetter(message.getGetter());
			messaged1.setSender(message.getGetter());
			messaged1.setMessageType(MessageType.del_succ);
			msgList.add(messaged1);
			mmsf.setMessage(message);
			Iterator<Message> iter1 = mmsf.getMessage().iterator();
			while(iter1.hasNext()){
				message1 = iter1.next();
				msgList.add(message1);
			}
			if(DAOFactory.getIUserDAOInstance().isonline(send)){
				Message message_send = new Message();
				message_send.setSender(send);
				message_send.setGetter(send);
				mmsf.setMessage(message_send);
				Iterator<Message> iter2 = mmsf.getMessage().iterator();
				while(iter2.hasNext()){
					message2 = iter2.next();
					msgList.add(message2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}

}
