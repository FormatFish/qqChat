package com.mqserver.server;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TextServer implements Runnable{
	public FileWriter notes;
	ServerSocket server = null ;
	Socket client = null ;	// 表示客 户端
	public TextServer(FileWriter notes){
		this.notes = notes;
	}
	public void run() {
		
		try {
			server = new ServerSocket(8888) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	// 服务器在8888端口上监听
		boolean f = true ;	// 定义个标记位
		while(f){
 			try {
				notes.write("服务器运行，等待客户端连接。" + "\r\n") ;
				client = server.accept() ;		// 得到连接，程序进入到阻塞状态
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
