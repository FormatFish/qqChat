package com.mqserver.server;

import java.util.HashMap;
//import java.util.Iterator;
import java.util.Map;



public class MessageClientThread {
	public static Map<String, TextServerThread> hm=new HashMap<String, TextServerThread>();
	public static  void setClientThread(String uid, TextServerThread th){
		
	}
	public static void addClientThread(String uid, TextServerThread th){
		
		hm.put(uid, th);
	}
	public static TextServerThread getTextServerThread(String uid){
		return (TextServerThread) hm.get(uid);
	}
	public static void removeClientThread(String uid){
		hm.remove(uid);
	}
	public static boolean isFound(String send){
		boolean flag = true;
		if(hm.containsKey(send))
			flag = false;
		return flag;
	}

}
