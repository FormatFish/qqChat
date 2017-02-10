package com.mqserver.factory;

import com.mq.client.common.MessageType;
import com.mqserver.message.ImessageMethod;
import com.mqserver.message.method.*;

public class MessageDoFactory {
	
	public static ImessageMethod getMessageMethod(int type){
		ImessageMethod mmd = null;
		switch(type){
			case MessageType.login:
				mmd = new MessageMethodLogin();//��¼����
				break;
			case MessageType.register:
				mmd = new MessageMethodRegiste();//ע�᷽��
				break;
			case MessageType.showFriendList:
				mmd = new MessageMethodShowFriend();//�����б���
				break;
			case MessageType.addFriend:
				mmd = new MessageMethodAddFriend();//�Ӻ��ѷ���
				break;
			case MessageType.searchFriend:
				mmd = new MessageMethodSearchFriend();//�������ѷ���
				break;
			case MessageType.chat:
				mmd = new MessageMethodChat();//���췽��
				break;
			case MessageType.chatRecord:
				mmd = new MessageMethodChatRecord();//�����¼����
				break;
			case MessageType.reponseAdd_ok:
				mmd = new MessageMethodReponseAdd_ok();//�ظ�ͬ����Ӻ��ѷ���
				break;
			case MessageType.responseAdd_refuse:
				mmd = new MessaegMethodResponseAdd_refuse();//�ظ���ͬ����Ӻ��ѷ���
				break;
			case MessageType.del_friends:
				mmd = new MwssageMethodDelFriends();//ɾ�����ѷ���
				break;
			case MessageType.Signature:
				mmd = new MessageMethodSignature();//�޸�ǩ������
				break;
			case MessageType.offLine:
				mmd = new MessageMethodOffline();//���߷���
				break;
			case MessageType.getUserInfo:
				mmd = new MessageMethodGetUserInfo();//�����û����Ϸ���
				break;
			case MessageType.TX_images://�޸�ͷ�񷽷�
				mmd = new MessageMethodTX();
				break;
			case MessageType.skinPath://�޸�Ƥ��
				mmd = new MessageMethodSkinPath();
				break;
			case MessageType.other://�޸ĸ�����Ϣ
				mmd = new MessageMethodupdateUser();
				break;
			case MessageType.isRecv://�����Ƿ�����ļ�
				mmd = new MessageMethodisRecv();
				break;
			case MessageType.agreeRecv://�ظ������ļ�
				mmd = new MessageMethodagreeRecv();
				break;
			case MessageType.refuseRecv://�ܾ������ļ�
				mmd = new MessageMethodrefuseRecv();
				break;
			case MessageType.sendFile://�ܾ������ļ�
				mmd = new MessageMethodsendFile();
				break;
			default:
				System.out.print("����");
				
		}
		return mmd;
	}
}	
