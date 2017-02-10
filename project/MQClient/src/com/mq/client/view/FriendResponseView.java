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
import java.util.Date;

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

public class FriendResponseView extends JFrame implements ActionListener {

	
    private JPanel addWhichCase = new JPanel();
	private JComboBox boxState1 = new JComboBox();
	private JLabel lblState = new JLabel("将好友添加到:");
	private JComboBox boxState = new JComboBox();
	private JButton addFriend = new JButton("添加好友");
	private JButton cancel = new JButton("取消");
	private String uid;
	private FriendFindView qf;
	public static void main(String[] args) {
		new FriendResponseView(new FriendFindView() ,"001");
	}	
	public FriendResponseView(FriendFindView qf ,String uid) {
		this.qf = qf;
		this.uid = uid;
		setTitle("添加用户");
		setSize(300,200);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);	
		init();
		
		setVisible(true);
	}
	
	private void init(){

		boxState.addItem("加入到我的朋友");
		boxState.addItem("加入到我的家人");
		boxState.addItem("加入到我的同事");
		

		lblState.setFont(new   java.awt.Font("Dialog",   1,   15));   
		
		boxState.setPreferredSize(new Dimension(200,20));//Dimension   java的一个类，封装了一个构件的高度和宽度 
		
		
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
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==addFriend){
			System.out.println(this.boxState.getSelectedIndex()+1);
			User user = new User();
			user.setUserId(this.uid);
			user.setUserType(this.boxState.getSelectedIndex()+1);
			
			
			Message msg = new Message();
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(ManageUserList.user.getUserId());
			msg.setContext(user);
			msg.setMessageType(MessageType.addFriend);
			msg.setSendTime((new Date()).toString());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//发包
			JOptionPane.showMessageDialog(this, "等待对方同意中。。。。");
			this.dispose();
			this.qf.dispose();
			
			
			//JOptionPane.showMessageDialog(this,"添加好友成功"+boxState.getSelectedItem());
			//boxState.getSelectedItem();选中事件
			
			
			
		
		}
		if(e.getSource()==cancel){
			dispose();
			this.qf.dispose();
		}
		
		
	}
	
	
	
}