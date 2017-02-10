package com.mq.client.factory;

import com.mq.client.common.MessageType;
import com.mq.client.method.ChatMethod;
import com.mq.client.method.LoginMessageMethod;
import com.mq.client.method.NotificationMessageMethod;
import com.mq.client.method.QueryFriendMessageMethed;
import com.mq.client.method.RegisterMessageMethod;

public class MessageFactory {
	private static ImessageMethod Imessage;

	public static ImessageMethod getMessageMethod(int type) {
		Imessage = null;
		switch (type) {
		//登陆界面的消息
		case MessageType.login_succ:
		case MessageType.login_fail: 
		case MessageType.show_succ:
		case MessageType.getUserInfo:
		case MessageType.del_succ:
		case MessageType.other:
		case MessageType.refuseRecv:
		case MessageType.isRecv:
		case MessageType.agreeRecv:
		case MessageType.sendFile:
		{
			Imessage = new LoginMessageMethod();	
			//System.out.println("LoginMessageMethodxx");
		}    
			break;	
		//聊天界面的消息	
		case MessageType.chat:
		case MessageType.chatRecord:
			Imessage = new ChatMethod();
			break;
			
		//注册界面的消息
		case MessageType.register_fail:
		case MessageType.register_succ:
			Imessage = new RegisterMessageMethod();
			break;
			
		case MessageType.searchFriend:
			Imessage = new QueryFriendMessageMethed();
			break;
		//主界面的消息
		case MessageType.add_succ:
		case MessageType.add_fail:
		case MessageType.addFriend:
		
		     Imessage = new NotificationMessageMethod();
		     break;
		 //未知的消息    
		default:
			System.out.println("消息不可用");
		}
		return Imessage;
	}
}
