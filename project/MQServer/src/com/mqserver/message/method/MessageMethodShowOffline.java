package com.mqserver.message.method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodShowOffline implements ImessageMethod {
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
		Message message1;
		
		String path;
		
		HashMap<String, String> strMap = new HashMap<String, String>();
		try {
			strMap = DAOFactory.getIUserDAOInstance().getOfflineChat(
					message.getGetter());
			Set<String> keys = strMap.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				BufferedReader br = null;
				String temp = null;
				String str = null;
				message1 = new Message();
				String ss = iter.next();
				message1.setMessageType(MessageType.chat);
				message1.setGetter(ss);
				message1.setSender(message.getGetter());
				path = strMap.get(ss);
				StringBuilder sb = new StringBuilder();
				try {
					br = new BufferedReader(new InputStreamReader(
							new FileInputStream(path)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
						sb.append(System.getProperty("line.separator"));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch blocks
					e.printStackTrace();
				}
				br.close();
				str = sb.toString();
				message1.setContext(str);
				msgList.add(message1);
				File file = new File(path);
				if(file.exists()){
					file.delete();
				}
				
				
			}
			DAOFactory.getIUserDAOInstance().deleteOffline(message.getGetter());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return msgList;
	}

}
