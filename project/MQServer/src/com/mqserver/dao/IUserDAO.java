package com.mqserver.dao ;

import java.util.HashMap;
import java.util.List;

import com.mq.client.common.User;

public interface IUserDAO {
	// ������ɵ��ǵ�½��֤����ô��½����ֻ�����ַ��ؽ��
	public boolean findLogin(User user) throws Exception ;				//�жϵ�¼�ɹ�
	public String register(User user) throws Exception;					//ע��
	public List<User> searchFriend(String pro) throws Exception;		//��������
	public List<User> showFriend(String pro) throws Exception;			//��ʾ�����б�
	public void addChat(String get, String send, String path) throws Exception;//�������
	public boolean isonline(String pro) throws Exception;				//�ж��Ƿ�����
	public void addOfflineChat(String get, String send, String path) throws Exception;//���������Ϣ
	public HashMap<String,String> getOfflineChat(String pro) throws Exception;	//�õ�������Ϣ·��
	public void deleteOffline(String pro) throws Exception;				//ɾ��������Ϣ·��
	public String showChatRecord(String get, String send) throws Exception;//��ʾ�����¼
	public User getUser(String pro) throws Exception;					//�õ��û���Ϣ
	public void addFriend(String get, String send,int type) throws Exception;//��Ӻ���
	public void delFriend(String get, String send) throws Exception;		//ɾ������
	public void Offline(String pro) throws Exception;						//���߲���
	public void updateSignature(String pro,String get) throws Exception;				//�޸�ǩ��
	public void updateTX_image(String get, String im) throws Exception;					//�޸�ǩ��
	public boolean isFriend(String get, String send) throws Exception;					//�ж��Ƿ�Ϊ����
	public void updateSkinPath(String get, String im) throws Exception;					//�޸�Ƥ��
	public void updateUser(User user) throws Exception;						//�޸ĸ������� 
} 