package com.mqserver.view;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.mqserver.server.TextServer;

public class ServerGui extends JFrame implements ActionListener{
	private JButton btnClose;
	private Notes notes = new Notes();
	private TextServer ts ;
	private JButton btnStart;
	private JButton btnDaily;
	private JPanel mainPane;
	public ServerGui()
	{
		this.mainPane = new JPanel();
		this.setTitle("QQ服务器");
		this.setSize(400, 100);
		addCenter();
		this.add(this.mainPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private void addCenter()
	{	
		
		Box boxStu = Box.createHorizontalBox();
        boxStu.add(Box.createVerticalStrut(50));
        this.mainPane.add(boxStu, BorderLayout.CENTER);
        
        JPanel pnlTitle = new JPanel();
        this.btnStart = new JButton("开启服务");
        pnlTitle.add(this.btnStart);
        
        JPanel pnlDaily = new JPanel();
        this.btnDaily = new JButton("服务日志");
        pnlDaily.add(this.btnDaily);
        
        JPanel pnlGrade = new JPanel();
        this.btnClose = new JButton("关闭服务");
        pnlGrade.add(this.btnClose);
        
        
        this.btnClose.addActionListener(this);
        this.btnDaily.addActionListener(this);
        this.btnStart.addActionListener(this);
       
        boxStu.add(pnlTitle);
        boxStu.add(pnlDaily);
        boxStu.add(pnlGrade);        
        boxStu.add(Box.createVerticalStrut(50));         //设置BOX的高度
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnStart) {
			
			if (notes.isFlag()) {
				this.btnStart.setEnabled(false);
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy年MM月dd日HH时mm分ss秒");
				File file = new File("server_notes" + File.separator
						+ df.format(date) + "服务器开启日志.txt");
				try {
					file.createNewFile();
					notes.setNotes(new FileWriter(file, true));
					notes.setFlag(false);
					ts = new TextServer(this.notes.getNotes());
					new Thread(ts).start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getSource() == btnDaily){
			ShowServerView ssv = new ShowServerView();
		}else{
			this.btnStart.setEnabled(true);
			try {
				ts.close();
				this.notes.getNotes().close();
				//this.notes.setNotes(null); 
				this.notes.setFlag(true);
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		new ServerGui();
	}

}
class Notes{
	private FileWriter notes;
	private boolean flag=true;
	public FileWriter getNotes() {
		return notes;
	}
	public void setNotes(FileWriter notes) {
		this.notes = notes;
	}
	public boolean isFlag() {
		return this.flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
