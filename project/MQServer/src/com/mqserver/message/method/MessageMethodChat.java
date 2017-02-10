package com.mqserver.message.method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mq.client.common.Message;
import com.mqserver.factory.DAOFactory;
import com.mqserver.message.ImessageMethod;

public class MessageMethodChat implements ImessageMethod {
	private Message message;
	private List<Message> msgList = new ArrayList<Message>();

	@Override
	public List<Message> getMessage() {
		// TODO Auto-generated method stub
		return this.msgList;
	}
	@Override
	public void setMessage(Message message) {
		// TODO Auto-generated method stub
		this.message = message;
		this.doMethod();
	}
	@Override
	public void doMethod() {
		// TODO Auto-generated method stub
		String str = message.getSendTime() + "：  " + (String) message.getContext() + "\r\n";
		
		//message.setContext(str);
		FileWriter raf = null;
		String fileName1 = message.getGetter() + "_" + message.getSender() + ".txt";
		String path1 = "message_notes" + File.separator + fileName1;
		String fileName2 = message.getSender() + "_" + message.getGetter() + ".txt";
		String path2 = "message_notes" + File.separator + fileName2;
		File file1 = new File(path1);
		File file2 = new File(path2);
		if(file1.exists()){
			try {
				raf = new FileWriter(file1, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(file2.exists()){
			try {
				raf = new FileWriter(file2, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				file1.createNewFile();
				raf = new FileWriter(file1, true);
				try{
					DAOFactory.getIUserDAOInstance().addChat(message.getGetter(), message.getSender(), path1);
					DAOFactory.getIUserDAOInstance().addChat(message.getSender(), message.getGetter(), path1);
				}catch(Exception e){
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try{	
			raf.write(str);
			raf.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		msgList.add(message);
		try {
			if(!DAOFactory.getIUserDAOInstance().isonline(message.getSender())){	//对方不在线的话
				msgList.remove(message);
				String filename = message.getGetter() + "_" + message.getSender() +"_offoline.txt";
				String path = "user_info" + File.separator + message.getGetter() + File.separator + filename;
				File fileoffline = new File(path);
				fileoffline.createNewFile();
				DAOFactory.getIUserDAOInstance().addOfflineChat(message.getGetter(), message.getSender(), path);
				FileWriter fw = new FileWriter(fileoffline, true);
				fw.write(str);
				fw.close();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
