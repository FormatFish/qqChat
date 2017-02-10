package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodOffline implements ImessageMethod {
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
		String str = message.getGetter();
		List<User> userList = new ArrayList<User>();
		try {
			DAOFactory.getIUserDAOInstance().Offline(str);
			userList = DAOFactory.getIUserDAOInstance().showFriend(str);
			Iterator<User> iter1 = userList.iterator();
			while(iter1.hasNext()){
				User user1 = iter1.next();
				String id = user1.getUserId();
				if(DAOFactory.getIUserDAOInstance().isonline(id)){//如果好友在线
					User user3 = new User();
					Message message3 = new Message();
					userList = DAOFactory.getIUserDAOInstance().showFriend(id);
					user3.setUserList(userList);
					message3.setContext(user3);
					message3.setMessageType(MessageType.show_succ);
					message3.setGetter("***服务端***");
					message3.setSender(user1.getUserId());
					message3.setSendTime((new Date()).toString());
					msgList.add(message3);
				}
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
