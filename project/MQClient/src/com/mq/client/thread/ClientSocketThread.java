package com.mq.client.thread;

import com.mq.client.common.*;
import com.mq.client.factory.MessageFactory;
import com.mq.client.factory.ImessageMethod;

import java.util.*;
import java.net.*;
import java.io.*;

import com.mq.client.model.*;

//接收和发送消息的线程

public class ClientSocketThread extends Thread {

	ClientSocket mc;
	ImessageMethod im =null;
	Message acceptedMessage;
	
	boolean flag = true;
	// 构造函数
	public ClientSocketThread() {
	    this.mc = new ClientSocket();
		this.acceptedMessage = new Message();
	}

	public void run() {	      
	    System.out.println("ClientSocketThread Start Run 循环");	       
		do
		{	// 不停的读取从服务器端发来的消息
			accept();
	
		 }while(flag);
	}
	
	public void accept(){
		
		try {

			System.out.println("接收线程----准备新的消息");
			acceptedMessage = this.mc.receiveMsg();
			System.out.println("    消息类型："+acceptedMessage.getMessageType());
		
			
			im = MessageFactory.getMessageMethod(acceptedMessage.getMessageType());
			im.setMessage(acceptedMessage);		
			im.Deal_Msg();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
     public void send(Message msg) throws Exception
     {
    	 mc.sendMsg(msg);
    	 System.out.println("发送消息"+msg.getMessageType());
    	 
     }
     public void closeSocket() throws Exception
 	 {
 		
 		mc.closeSocket();
 	 }
     public void setFlagTrue(){
    	 flag = true;
    	 
     }
	
	
}
