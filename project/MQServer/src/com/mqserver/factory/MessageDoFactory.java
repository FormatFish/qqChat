package com.mqserver.factory;

import com.mq.client.common.MessageType;
import com.mqserver.message.ImessageMethod;
import com.mqserver.message.method.*;

public class MessageDoFactory {
	
	public static ImessageMethod getMessageMethod(int type){
		ImessageMethod mmd = null;
		switch(type){
			case MessageType.login:
				mmd = new MessageMethodLogin();//登录方法
				break;
			case MessageType.register:
				mmd = new MessageMethodRegiste();//注册方法
				break;
			case MessageType.showFriendList:
				mmd = new MessageMethodShowFriend();//好友列表方法
				break;
			case MessageType.addFriend:
				mmd = new MessageMethodAddFriend();//加好友方法
				break;
			case MessageType.searchFriend:
				mmd = new MessageMethodSearchFriend();//搜索好友方法
				break;
			case MessageType.chat:
				mmd = new MessageMethodChat();//聊天方法
				break;
			case MessageType.chatRecord:
				mmd = new MessageMethodChatRecord();//聊天记录方法
				break;
			case MessageType.reponseAdd_ok:
				mmd = new MessageMethodReponseAdd_ok();//回复同意添加好友方法
				break;
			case MessageType.responseAdd_refuse:
				mmd = new MessaegMethodResponseAdd_refuse();//回复不同意添加好友方法
				break;
			case MessageType.del_friends:
				mmd = new MwssageMethodDelFriends();//删除好友方法
				break;
			case MessageType.Signature:
				mmd = new MessageMethodSignature();//修改签名方法
				break;
			case MessageType.offLine:
				mmd = new MessageMethodOffline();//下线方法
				break;
			case MessageType.getUserInfo:
				mmd = new MessageMethodGetUserInfo();//查找用户资料方法
				break;
			case MessageType.TX_images://修改头像方法
				mmd = new MessageMethodTX();
				break;
			case MessageType.skinPath://修改皮肤
				mmd = new MessageMethodSkinPath();
				break;
			case MessageType.other://修改个人信息
				mmd = new MessageMethodupdateUser();
				break;
			case MessageType.isRecv://请求是否接受文件
				mmd = new MessageMethodisRecv();
				break;
			case MessageType.agreeRecv://回复接受文件
				mmd = new MessageMethodagreeRecv();
				break;
			case MessageType.refuseRecv://拒绝接受文件
				mmd = new MessageMethodrefuseRecv();
				break;
			case MessageType.sendFile://拒绝接受文件
				mmd = new MessageMethodsendFile();
				break;
			default:
				System.out.print("出错");
				
		}
		return mmd;
	}
}	
