package com.mq.client.Manage;

import java.util.Hashtable;

import com.mq.client.util.IClientView;


//管理所有已经打开的界面
public class ManageAllView {

	 public static Hashtable<String,IClientView> mcClientView = new Hashtable<String,IClientView>();
	  
	  
	  public static void addClientView(String viewName,IClientView ClientView){
		  mcClientView.put(viewName,ClientView);	  
	  }

	  public static IClientView getClientView(String viewName){
		return  (IClientView)mcClientView.get(viewName) ;
	  }

	  public static void removeClientView(String viewName)
	  {
		  mcClientView.remove(viewName);
		  
	  }
	  
	  public static boolean isFound(String viewName)
	  {
		  boolean flag = false;
		  if(mcClientView.containsKey(viewName))
			  flag = true;
		  
		  return flag;
		  
	  }
}
