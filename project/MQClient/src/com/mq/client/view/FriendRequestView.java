package com.mq.client.view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.mq.client.Manage.ManageClientThread;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.util.FillWidth;

public class FriendRequestView extends JFrame implements ActionListener {

	private Message msg;
	public static void main(String[] args){
		
		FriendRequestView abc = new FriendRequestView(new Message());
		
	}
	
    private JPanel addWhichCase = new JPanel();
	private JLabel lblState ;
	
	
	private JButton addFriend = new JButton("接受请求");
	private JButton cancel = new JButton("拒绝请求");
	
	
	public FriendRequestView(Message msg) {
		this.msg = msg;
		setTitle("添加好友请求");
		setSize(300,125);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);	
		init();
		
		setVisible(true);
	}
	
	private void init(){

		this.lblState = new JLabel("系统消息:来自"+this.msg.getGetter()+"的添加好友请求");
		lblState.setFont(new   java.awt.Font("Dialog",   1,   15));   
		
		
		
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER,15,12));
		pane.setPreferredSize(new Dimension(300,50));	
		pane.add(lblState);
		
		setLayout(new FlowLayout(FlowLayout.CENTER,5,0));	
		add(new FillWidth(100,8));
		add(pane);
		add(new FillWidth(300,8));
		add(new FillWidth(65,20));
		add(addFriend);
		add(cancel);
		add(new FillWidth(300,8));


		addFriend.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==addFriend){
			
			QueryFriendsforResponseFriendView abc = new QueryFriendsforResponseFriendView(this , this.msg);
			
		
		}
		if(e.getSource()==cancel){
			
			Message msgRefuse = new Message();
			msgRefuse.setMessageType(MessageType.responseAdd_refuse);
			msgRefuse.setGetter(this.msg.getSender());
			msgRefuse.setSender(this.msg.getGetter());
			msgRefuse.setContext(this.msg.getSender());
			
			//发包
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msgRefuse);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}
		
		
	}
	
	
	
}