package com.mq.client.Manage;


import java.util.Hashtable;

import com.mq.client.thread.ClientSocketThread;



//管理所有的客户端的线程

public class ManageClientThread {
  public static Hashtable<String,ClientSocketThread> mcThread = new Hashtable<String,ClientSocketThread>();
  
  
  public static void addClientSocketThread(String socketThread,ClientSocketThread clientSocketThread){
	  mcThread.put(socketThread,clientSocketThread);	  
  }

  public static ClientSocketThread getClientSocketThread(String socketThread){
	return  (ClientSocketThread)mcThread.get(socketThread) ;
  }
  public static void removeClientSocketThread(String socketThread)
  {
	  mcThread.remove(socketThread);
	  
  }
}
