package com.mqserver.server;


import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mqserver.factory.MessageDoFactory;
import com.mqserver.message.ImessageMethod;

public class TextServerThread implements Runnable {
	MessageTypeMap mtm = new MessageTypeMap();
	FileWriter notes = null;
	private Socket client = null;
	private ImessageMethod method;
	Message message_get = null; // ���յ���Ϣ
	Message message_send = null; // ���͵���Ϣ
	List<Message> msglist = null;
	ObjectInputStream oin = null; // �����ļ�������
	ObjectOutputStream out = null; // �ļ���������
	OutputStream o = null;
	static int i = 1;
	private String socketNumber;

	public String getSocketNumber() {
		return socketNumber;
	}

	public void setSocketNumber(String socketNumber) {
		this.socketNumber = socketNumber;
	}

	public TextServerThread(Socket client, FileWriter notes) {
		this.notes = notes;
		this.client = client;
	}

	public void run() {
		while (true) {
			try {
				notes.write("��"+i + "�ν���������߳�==================" + "\r\n");
				oin = new ObjectInputStream(client.getInputStream());
				message_get = (Message) oin.readObject();
				this.setSocketNumber(message_get.getGetter());
				MessageClientThread.addClientThread(message_get.getGetter(),
						this);// ��������뵽Map��
				notes.write("������Ϊ��"+ message_get.getGetter()
						+ "����" + "\r\n");
				notes.write("������Ϣ����Ϊ-------->" + mtm.getString(message_get.getMessageType())+ "\r\n\r\n");
				method = MessageDoFactory.getMessageMethod(message_get
						.getMessageType());// ȡ��messageҪʹ�õķ���
				method.setMessage(message_get);
				msglist = method.getMessage();// �õ������Ҫ���͵���Ϣ
				
				
				
				Iterator<Message> iter = msglist.iterator(); 
				while(iter.hasNext()){
					message_send = iter.next();
					
					notes.write("��������"+message_send.getSender()+"���͵���Ϣ����Ϣ����Ϊ��-------->"+mtm.getString(message_send.getMessageType()) + "\r\n");
					sendMsg(message_send);

				}
				notes.write("��"+i + "���˳��������߳�===================" + "\r\n");
				++i;
			} catch(NullPointerException e1){
				System.out.println("****************");
				Message message_error = new Message();
				message_error.setMessageType(MessageType.login_fail);
				message_error.setContext("��ĺ�������ص�¼");
				message_error.setSender(this.getSocketNumber());
				try {
					sendMsg(message_error);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch (Exception e) {
				this.offline();
				break;
			}
			
		}

	}
	public void offline(){
		Message messageOffline = new Message();
		messageOffline.setGetter(this.getSocketNumber());
		messageOffline.setMessageType(MessageType.offLine);
		method = MessageDoFactory.getMessageMethod(messageOffline.getMessageType());
		method.setMessage(messageOffline);
		msglist = method.getMessage();
		Iterator<Message> iter = msglist.iterator(); 
		while(iter.hasNext()){
			message_send = iter.next();
			try {
				notes.write("��������"+message_send.getSender()+"���͵���Ϣ����Ϣ����Ϊ��-------->"+mtm.getString(message_send.getMessageType()) + "\r\n");
			
				sendMsg(message_send);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		MessageClientThread.removeClientThread(this.getSocketNumber());
		try {
			notes.write(this.socketNumber + "���߳̽���������������" + "\r\n\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void sendMsg(Message msg) throws IOException {
		
    	notes.write("1.���������ͻ��˷��͵���Ϣ����------------->"
				+ mtm.getString(msg.getMessageType()) + "\r\n");
    	notes.write("���������ͻ��˷��͵���Ϣ��Դ"+ msg.getGetter()
				+ "����----------------------------" + "\r\n");
    	notes.write("���������ͻ��˷�����Ϣ����Ϣ������"+ msg.getSender()
				+ "����----------------------------" + "\r\n");
		
		o = MessageClientThread.getTextServerThread(
				msg.getSender()).getOutputStream();
		//����ĶԷ��̲߳����ߣ�Ӧ��������������������������������������������������������������
		notes.write("2.���������ͻ��˷��͵���Ϣ����------------->"
				+ mtm.getString(msg.getMessageType()) + "\r\n");
		out = new ObjectOutputStream(o); // ȡ����Ӧ����ͻ����߳�
		out.writeObject(msg); // ������Ϣ
		out.flush();
		notes.write("�������������"
				+ msg.getMessageType()+"\r\n\r\n");
		
    }

	public OutputStream getOutputStream() {
		try {
			return this.client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
