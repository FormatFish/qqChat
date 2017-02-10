package com.mqserver.message.method;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodChatRecord implements ImessageMethod {
	private Message message;
	private Message messaged = new Message();
	private List<Message> msgList = new ArrayList<Message>();
	private String path;
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}

	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		String get = message.getGetter();
		String send = message.getSender();
		String temp = null;
		String str = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			path = DAOFactory.getIUserDAOInstance().showChatRecord(get, send);
			br = new BufferedReader(new InputStreamReader(
						new FileInputStream(path)));
			while ((temp = br.readLine()) != null) {
					sb.append(temp);
					sb.append(System.getProperty("line.separator"));
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = sb.toString();
		messaged.setContext(str);
		messaged.setGetter(send);
		messaged.setSender(get);
		messaged.setMessageType(MessageType.chatRecord);
		msgList.add(messaged);
	}

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}

}
