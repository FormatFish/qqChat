package com.mq.client.thread;

import com.mq.client.common.*;
import com.mq.client.factory.MessageFactory;
import com.mq.client.factory.ImessageMethod;

import java.util.*;
import java.net.*;
import java.io.*;

import com.mq.client.model.*;

//���պͷ�����Ϣ���߳�

public class ClientSocketThread extends Thread {

	ClientSocket mc;
	ImessageMethod im =null;
	Message acceptedMessage;
	
	boolean flag = true;
	// ���캯��
	public ClientSocketThread() {
	    this.mc = new ClientSocket();
		this.acceptedMessage = new Message();
	}

	public void run() {	      
	    System.out.println("ClientSocketThread Start Run ѭ��");	       
		do
		{	// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			accept();
	
		 }while(flag);
	}
	
	public void accept(){
		
		try {

			System.out.println("�����߳�----׼���µ���Ϣ");
			acceptedMessage = this.mc.receiveMsg();
			System.out.println("    ��Ϣ���ͣ�"+acceptedMessage.getMessageType());
		
			
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
    	 System.out.println("������Ϣ"+msg.getMessageType());
    	 
     }
     public void closeSocket() throws Exception
 	 {
 		
 		mc.closeSocket();
 	 }
     public void setFlagTrue(){
    	 flag = true;
    	 
     }
	
	
}
