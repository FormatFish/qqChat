package com.mq.client.model;

import java.io.*;
import java.net.*;

import javax.swing.*;

import com.mq.client.common.*;
import com.mq.client.factory.MessageFactory;
//��������ͨѶ��
public class ClientSocket {
	Socket s;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public ClientSocket() {

		try {
			this.s = new Socket("127.0.0.1", 8888);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "������δ����");
		}
	}

	public void sendMsg(Message msg) throws Exception {
		oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(msg);
		oos.flush();
		System.out.print("socket send");
	}

	public Message receiveMsg() throws Exception {
		// Socket s = connect();]
		ois = new ObjectInputStream(s.getInputStream());
		Message ms = (Message) ois.readObject();
		System.out.print("socket ���յ�����Ϣ");
		return ms;
	}

	public void closeSocket() throws Exception {
		oos.close();
		ois.close();
		s.close();

	}
}
