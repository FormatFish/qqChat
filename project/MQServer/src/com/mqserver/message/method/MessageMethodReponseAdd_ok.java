package com.mqserver.message.method;

import java.util.ArrayList;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodReponseAdd_ok implements ImessageMethod {
	private Message message;
	private User userTotal1 = new User();
	private User userTotal2 = new User();
	private Message messaged1 = new Message();
	private Message messaged2 = new Message();
	private List<Message> msgList = new ArrayList<Message>();
	private List<User> userList = new ArrayList<User>();
	private List<User> userFriend = new ArrayList<User>();
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
			User user ;
			User userget ;
			user = (User)message.getContext();
			userList = user.getUserList();
			Iterator<User> iter = userList.iterator();
			while(iter.hasNext()){
				userget = iter.next();
				if(userget.getUserId() == message.getGetter()){
					DAOFactory.getIUserDAOInstance().addFriend(message.getSender(), userget.getUserId(), userget.getUserType());
				}else{
					DAOFactory.getIUserDAOInstance().addFriend(message.getGetter(), userget.getUserId(), userget.getUserType());
				}
			}
			if(DAOFactory.getIUserDAOInstance().isonline(message.getSender())){
				messaged1.setGetter(message.getGetter());
				messaged1.setSender(message.getSender());
				messaged1.setMessageType(MessageType.add_succ);
				
				
				String pro1 = messaged1.getSender();//��ȡ�Է���id
				userFriend = DAOFactory.getIUserDAOInstance().showFriend(pro1);
				userTotal1.setUserList(userFriend);			//�����صĺ����б��ظ��ͻ���
				messaged1.setContext(userTotal1);
				msgList.add(messaged1);
				
			}
			
			messaged2.setGetter(message.getSender());
			messaged2.setSender(message.getGetter());
			messaged2.setMessageType(MessageType.add_succ);
			String pro2 = messaged2.getSender();//��ȡ�ҷ���id
			userFriend = DAOFactory.getIUserDAOInstance().showFriend(pro2);
			userTotal2.setUserList(userFriend);			//�����صĺ����б��ظ��ͻ���
			messaged2.setContext(userTotal2);
			
			msgList.add(messaged2);
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
