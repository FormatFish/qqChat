package com.mq.client.Manage;

import java.util.*;

import com.mq.client.common.*;


//未测试
//管理所用的未读取的了的主界面通知消息

public class ManageNotification {

	public static Vector<Message> notification = new Vector<Message>();

	public static void addnotification(Message msg) {
		notification.add(msg);
	}

	public static Message getOneNotificatoon(){
		  Message message = new Message();
		if(isEmpty()){
			  message.setMessageType(MessageType.other);
			  
		  }else{
		      message = notification.get(0);
             notification.remove(0);
		  }
		return   message;
	
	  }

	public static boolean isEmpty() {
		boolean flag = true;
		if(!notification.isEmpty()){
			flag = false;
		}
     return flag;
	}
}
