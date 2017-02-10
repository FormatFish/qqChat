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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.util.FillWidth;

public class QueryFriendsforResponseFriendView extends JFrame implements ActionListener {

	
    private JPanel addWhichCase = new JPanel();
	private JComboBox boxState1 = new JComboBox();
	private JLabel lblState = new JLabel("��������ӵ�:");
	private JComboBox boxState = new JComboBox();
	private JButton addFriend = new JButton("��Ӻ���");
	private JButton cancel = new JButton("ȡ��");
	private Message msg;
	private FriendRequestView f;
	public static void main(String[] args) {;
		new QueryFriendsforResponseFriendView(new FriendRequestView(new Message()) , new Message());
	}	
	public QueryFriendsforResponseFriendView(FriendRequestView f , Message msg) {
		this.f = f;
		this.msg = msg;
		setTitle("����û�");
		setSize(300,200);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);	
		init();
		setVisible(true);
	}
	
	private void init(){

		boxState.addItem("���뵽�ҵ�����");
		boxState.addItem("���뵽�ҵļ���");
		boxState.addItem("���뵽�ҵ�ͬ��");
		

		lblState.setFont(new   java.awt.Font("Dialog",   1,   15));   
		
		boxState.setPreferredSize(new Dimension(200,20));//Dimension   java��һ���࣬��װ��һ�������ĸ߶ȺͿ�� 
		
		
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER,15,12));
		pane.setPreferredSize(new Dimension(300,110));
		
		pane.add(lblState);
		pane.add(boxState);
		
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
			System.out.println(this.boxState.getSelectedIndex()+1);
			User userCome = new User();
			userCome = (User)this.msg.getContext();
			
			User userMine = new User();
			userMine.setUserId(this.msg.getGetter());
			userMine.setUserType(this.boxState.getSelectedIndex()+1);
			
			User userTotal = new User();
			List<User> userList = new ArrayList<User>();
			userList.add(userCome);
			userList.add(userMine);
			userTotal.setUserList(userList);
			
			//���
			Message msg = new Message();
			msg.setGetter(this.msg.getSender());
			msg.setSender(this.msg.getGetter());
			msg.setContext(userTotal);
			msg.setMessageType(MessageType.reponseAdd_ok);
			msg.setSendTime((new Date()).toString());
			
			
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//����
			JOptionPane.showMessageDialog(this, "�ȴ��Է�ͬ���С�������");
			this.dispose();
			this.f.dispose();
		
		}
		if(e.getSource()==cancel){
			//dispose();
			//���
			Message msg = new Message();
			msg.setGetter(this.msg.getSender());
			msg.setSender(this.msg.getGetter());
			msg.setContext(this.msg.getSender());
			msg.setMessageType(MessageType.responseAdd_refuse);
			msg.setSendTime((new Date()).toString());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			this.dispose();
			this.f.dispose();
		}
		
		
	}
	
	
	
}