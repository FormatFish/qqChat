package com.mqserver.server;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TextServer implements Runnable{
	public FileWriter notes;
	ServerSocket server = null ;
	Socket client = null ;	// ��ʾ�� ����
	public TextServer(FileWriter notes){
		this.notes = notes;
	}
	public void run() {
		
		try {
			server = new ServerSocket(8888) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	// ��������8888�˿��ϼ���
		boolean f = true ;	// ��������λ
		while(f){
 			try {
				notes.write("���������У��ȴ��ͻ������ӡ�" + "\r\n") ;
				client = server.accept() ;		// �õ����ӣ�������뵽����״̬
				new Thread(new TextServerThread(client,notes)).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				break;
			}
 			
		}
	}
	public void close() throws IOException{
		server.close();
	}
}
