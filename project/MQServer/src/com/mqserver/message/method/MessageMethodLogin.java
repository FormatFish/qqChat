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

public class MessageMethodLogin implements ImessageMethod{
	private Message message;//处理过不要再次使用
	private MessageMethodShowFriend msf = new MessageMethodShowFriend();
	private MessageMethodShowOffline mso = new MessageMethodShowOffline();
	private List<Message> msgList = new ArrayList<Message>();
	private Message messaged = new Message();
	private Message message1 = new Message();
	private Message message2 = new Message();
	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}
	public void doMethod(){
		User user = (User) this.message.getContext();
		try{
			if(!DAOFactory.getIUserDAOInstance().isonline(user.getUserId())){
				if(DAOFactory.getIUserDAOInstance().findLogin(user)){
					User usermessage = new User();
					usermessage = DAOFactory.getIUserDAOInstance().getUser(user.getUserId());
					messaged.setMessageType(MessageType.login_succ);
					messaged.setContext(usermessage);
					messaged.setSender(message.getGetter());
					messaged.setGetter(message.getSender());
					msgList.add(messaged);
					msf.setMessage(message);
					mso.setMessage(message);
					Iterator<Message> iter1 = msf.getMessage().iterator();
					while(iter1.hasNext()){
						message1 = iter1.next();
						List<User> user_friend = ((User) message1.getContext()).getUserList();
						List<User> userList = new ArrayList<User>();
						Iterator<User> iter3 = user_friend.iterator();
						while(iter3.hasNext()){
							User user1 = iter3.next();
							String id = user1.getUserId();
							if(DAOFactory.getIUserDAOInstance().isonline(id)){
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
						msgList.add(message1);
					}
					Iterator<Message> iter2 = mso.getMessage().iterator();
					while(iter2.hasNext()){
						message2 = iter2.next();
						msgList.add(message2);
					}
					
				}else{
					message.setMessageType(MessageType.login_fail);
					message.setSender(message.getGetter());
					message.setGetter(message.getSender());
					message.setContext("用户名或密码错误！");
					System.out.println("失败");
					msgList.add(message);
				}
			}else{
				msgList = null;
			}
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
