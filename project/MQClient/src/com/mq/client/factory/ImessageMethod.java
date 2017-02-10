package com.mq.client.factory;

import com.mq.client.common.Message;


public interface ImessageMethod {
	public void setMessage(Message message);
	public Message getMessage();
	public void Deal_Msg()throws Exception;
}
