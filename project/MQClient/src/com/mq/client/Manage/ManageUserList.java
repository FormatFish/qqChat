package com.mq.client.Manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mq.client.common.Message;
import com.mq.client.common.User;


public class ManageUserList {
	
	public static List<User> userlist = new ArrayList<User>();
	public static User user = new User();
	public static User FriendUser = new User();
	public static List<User> userListForFriend = new ArrayList<User>();
	public static List<User> userListForFamily = new ArrayList<User>();
	public static List<User> userListForColleague = new ArrayList<User>();
	public static List<User> userSummary = new ArrayList<User>();
	
	
	public static User getFriendUser() {
		return FriendUser;
	}

	public static void setFriendUser(User friendUser) {
		FriendUser = friendUser;
	}

	public static void setUser(User user) {
		ManageUserList.user = user;
	}
	
	public static User getUser()
	{
		return ManageUserList.user;
	}

	public static List<User> getUserList() {
		return userlist;
	}
	
	public static void setUserList(List<User> userTemp) {
		System.out.println("进入ManageUserList的setUserList方法");
		ManageUserList.userlist = userTemp;
		System.out.println("准备更新列表=====================");
		for(int i = 0 ; i < userlist.size() ; ++i)
		{
			System.out.println(userlist.get(i).getUserName());
		}
		System.out.println("=========================");
		classfy();
	}
	
	public static User queryUser(String uid)
	{
		User tempUser = null;
		for(int i = 0 ; i < userSummary.size() ; ++i)
		{
			if(userSummary.get(i).getUserId().equals(uid))
			{
				tempUser = userSummary.get(i);
			}
		}
		
		return tempUser;
	}
	private static void classfy()
	{
		//userlist.clear();
		userListForFriend.clear();
		userListForColleague.clear();
		userListForFamily.clear();
		userSummary.clear();
		for(int i = 0 ; i < userlist.size() ; ++i)
		{
			int relation = 0;
			User user = userlist.get(i);
			relation = user.getUserType();
			System.out.println("----------用户类型为:"+relation);
			userSummary.add(user);
			switch(relation)
			{
			case 1:{
				userListForFriend.add(user);
			}break;
			case 2:{
				userListForFamily.add(user);
			}break;
			case 3:{
				userListForColleague.add(user);
			}break;
			default:{
				System.out.println("好友类型不识别");
			}
			}
		}
		
		System.out.println("--------userListForFriend----------");
		for(int i = 0 ; i< userListForFriend.size() ; ++i)
		{
			System.out.println(userListForFriend.get(i).getUserName());
		}
		System.out.print("-----------------------------");
		
		System.out.println("--------userListForFamily----------");
		for(int i = 0 ; i< userListForFamily.size() ; ++i)
		{
			System.out.println(userListForFamily.get(i).getUserName());
		}
		System.out.print("-----------------------------");
		
		System.out.println("--------userListForColleague----------");
		for(int i = 0 ; i< userListForColleague.size() ; ++i)
		{
			System.out.println(userListForColleague.get(i).getUserName());
		}
		System.out.print("-----------------------------");
	}




}
