package com.mqserver.message.method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.User;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodRegiste implements ImessageMethod{
	private Message message;
	private List<Message> msgList = new ArrayList<Message>();

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}
	public void doMethod(){
		User user = (User) this.message.getContext();
		String userId = null;
		try{
			userId = DAOFactory.getIUserDAOInstance().register(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		File file = new File("user_info" + File.separator + userId);
		file.mkdir();//建立用户自己的文件夹。。。
		message.setMessageType(8);
		message.setContext(userId);
		message.setSender(message.getGetter());
		message.setGetter(message.getSender());
		msgList.add(message);
	}
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

}
