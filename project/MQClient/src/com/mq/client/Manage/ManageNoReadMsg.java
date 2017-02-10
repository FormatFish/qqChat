package com.mq.client.Manage;

import java.util.*;
import com.mq.client.common.*;
//管理所用的未读取的聊天信息
public class ManageNoReadMsg {
	public static Hashtable<String,Vector<Message> > noReadChatSet = new Hashtable<String,Vector<Message> >();
	 public static int msgCnt =0;
	  
	  public static void addnoReadChat(String viewName,Message message){
		 if(noReadChatSet.containsKey(viewName))
		  {
			 getnoReadChat(viewName).add(message);			  
		  }
		 else
		 {
			 noReadChatSet.put(viewName, new Vector<Message>());
			 getnoReadChat(viewName).add(message);
		 }
		 ++msgCnt;		 
	  }
	  
	  public static void setMsg(String viewName,Message message){
		  
		  getnoReadChat(viewName).add(message);
	  }

	  public static Vector<Message> getnoReadChat(String viewName){
		return  noReadChatSet.get(viewName) ;
	  }

	  public static void removenoReadChat(String viewName)
	  {
		  noReadChatSet.remove(viewName);
		  
	  }
	  
	  public static boolean isFound(String viewName)
	  {
		  boolean flag = false;
		  if(noReadChatSet.containsKey(viewName))
			  flag = true;
		  
		  return flag;
		  
	  }
}
