package com.mqserver.dao ;

import java.util.HashMap;
import java.util.List;

import com.mq.client.common.User;

public interface IUserDAO {
	// 现在完成的是登陆验证，那么登陆操作只有两种返回结果
	public boolean findLogin(User user) throws Exception ;				//判断登录成功
	public String register(User user) throws Exception;					//注册
	public List<User> searchFriend(String pro) throws Exception;		//搜索好友
	public List<User> showFriend(String pro) throws Exception;			//显示好友列表
	public void addChat(String get, String send, String path) throws Exception;//添加聊天
	public boolean isonline(String pro) throws Exception;				//判断是否在线
	public void addOfflineChat(String get, String send, String path) throws Exception;//添加离线消息
	public HashMap<String,String> getOfflineChat(String pro) throws Exception;	//得到离线消息路径
	public void deleteOffline(String pro) throws Exception;				//删除离线消息路径
	public String showChatRecord(String get, String send) throws Exception;//显示聊天记录
	public User getUser(String pro) throws Exception;					//得到用户信息
	public void addFriend(String get, String send,int type) throws Exception;//添加好友
	public void delFriend(String get, String send) throws Exception;		//删除好友
	public void Offline(String pro) throws Exception;						//下线操作
	public void updateSignature(String pro,String get) throws Exception;				//修改签名
	public void updateTX_image(String get, String im) throws Exception;					//修改签名
	public boolean isFriend(String get, String send) throws Exception;					//判断是否为好友
	public void updateSkinPath(String get, String im) throws Exception;					//修改皮肤
	public void updateUser(User user) throws Exception;						//修改个人资料 
} 