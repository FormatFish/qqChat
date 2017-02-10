package com.mq.client.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

import com.mq.client.Manage.ManageNoReadMsg;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.User;
import com.mq.client.util.MyPane;
import com.mq.client.util.MySelfCell;
public class MessageNotifyView extends JFrame implements MouseListener , WindowListener {
	private JList msgList;
	private JPanel mainPane;
	
	private JPanel centerPane;
	private JScrollPane scroll;
	public MessageNotifyView()
	{
		this.mainPane = new JPanel(new BorderLayout());
		addCenter();
		this.mainPane.setSize(new Dimension(400 , 200));
		this.add(mainPane);
		this.setTitle("消息通知中心");
		this.setSize(300, 300);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void addCenter()
	{
		this.centerPane = new JPanel(new BorderLayout());
		getData();
		this.mainPane.add(this.centerPane , BorderLayout.CENTER);
	}
	public void getData()
	{
		
		this.msgList = new JList();
		DefaultListModel listModel = new DefaultListModel();
		this.msgList.setModel(listModel);
		this.msgList.setCellRenderer(new MySelfCell());//设置自绘cell
		
		//添加数据
		int index = 0;
		Iterator iter = ManageNoReadMsg.noReadChatSet.keySet().iterator();
		
		while(iter.hasNext())
		{
			String strId = iter.next().toString();
			User tempFriend = ManageUserList.queryUser(strId.substring(3, strId.length()));
			Image temp = new ImageIcon(tempFriend.getTxpath()).getImage();
			listModel.addElement(new MyPane(temp , tempFriend.getUserId()+":"+tempFriend.getUserName()+"发来消息"));
			System.out.println("离线消息输出"+strId);
		}
		
		
		this.msgList.addMouseListener(this);
		this.scroll = new JScrollPane(this.msgList);
		this.centerPane.add(this.scroll);
		
	}
	
	public static void main(String[] args)
	{
		new MessageNotifyView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int index = 0;
		String tmp;
		if (e.getClickCount() == 2) {
			tmp = ((MyPane)this.msgList.getSelectedValue()).getStr();
            System.out.println("=========="+tmp.substring(0,tmp.indexOf(":")));
            new ChatView(ManageUserList.user, ManageUserList.queryUser(tmp.substring(0,tmp.indexOf(":"))));
	}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);;
		return;
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);;
		return;
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
