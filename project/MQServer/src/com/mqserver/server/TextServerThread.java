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
	Message message_get = null; // 接收的消息
	Message message_send = null; // 发送的消息
	List<Message> msglist = null;
	ObjectInputStream oin = null; // 接收文件输入流
	ObjectOutputStream out = null; // 文件输出流输出
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
				notes.write("第"+i + "次进入服务总线程==================" + "\r\n");
				oin = new ObjectInputStream(client.getInputStream());
				message_get = (Message) oin.readObject();
				this.setSocketNumber(message_get.getGetter());
				MessageClientThread.addClientThread(message_get.getGetter(),
						this);// 把自身加入到Map中
				notes.write("发送者为："+ message_get.getGetter()
						+ "连接" + "\r\n");
				notes.write("发送消息类型为-------->" + mtm.getString(message_get.getMessageType())+ "\r\n\r\n");
				method = MessageDoFactory.getMessageMethod(message_get
						.getMessageType());// 取得message要使用的方法
				method.setMessage(message_get);
				msglist = method.getMessage();// 得到服务端要发送的消息
				
				
				
				Iterator<Message> iter = msglist.iterator(); 
				while(iter.hasNext()){
					message_send = iter.next();
					
					notes.write("服务器给"+message_send.getSender()+"发送的消息，消息类型为：-------->"+mtm.getString(message_send.getMessageType()) + "\r\n");
					sendMsg(message_send);

				}
				notes.write("第"+i + "次退出服务总线程===================" + "\r\n");
				++i;
			} catch(NullPointerException e1){
				System.out.println("****************");
				Message message_error = new Message();
				message_error.setMessageType(MessageType.login_fail);
				message_error.setContext("你的号已在异地登录");
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
				notes.write("服务器给"+message_send.getSender()+"发送的消息，消息类型为：-------->"+mtm.getString(message_send.getMessageType()) + "\r\n");
			
				sendMsg(message_send);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		MessageClientThread.removeClientThread(this.getSocketNumber());
		try {
			notes.write(this.socketNumber + "的线程结束。。。。。。" + "\r\n\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void sendMsg(Message msg) throws IOException {
		
    	notes.write("1.服务器给客户端发送的消息类型------------->"
				+ mtm.getString(msg.getMessageType()) + "\r\n");
    	notes.write("服务器给客户端发送的消息来源"+ msg.getGetter()
				+ "链接----------------------------" + "\r\n");
    	notes.write("服务器给客户端发送消息的消息接收者"+ msg.getSender()
				+ "链接----------------------------" + "\r\n");
		
		o = MessageClientThread.getTextServerThread(
				msg.getSender()).getOutputStream();
		//上面的对方线程不在线，应给不发包。。。。。。。。。。。。？？？？？？？？？？？？？？？
		notes.write("2.服务器给客户端发送的消息类型------------->"
				+ mtm.getString(msg.getMessageType()) + "\r\n");
		out = new ObjectOutputStream(o); // 取出对应输出客户端线程
		out.writeObject(msg); // 发送消息
		out.flush();
		notes.write("服务器发送完毕"
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
