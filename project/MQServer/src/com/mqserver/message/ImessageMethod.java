package com.mqserver.message;

import com.mq.client.common.Message;
import java.util.*;

public interface ImessageMethod {
	public void setMessage(Message message);
	public void doMethod();
	public List<Message> getMessage();
}
