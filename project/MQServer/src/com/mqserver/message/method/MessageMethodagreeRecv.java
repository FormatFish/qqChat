package com.mqserver.message.method;

import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mqserver.message.ImessageMethod;

public class MessageMethodagreeRecv implements ImessageMethod {
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
		msgList.add(message);
	}

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}

}
