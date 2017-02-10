package com.mq.client.view;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageClientThread;
import com.mq.client.Manage.ManageNoReadMsg;
import com.mq.client.Manage.ManageNotification;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.*;
import com.mq.client.util.IClientView;
import com.mq.client.util.JImagePane;
import com.mq.client.util.MyPane;
import com.mq.client.util.MySelfCell;

public class QQMainFaceView extends JFrame implements ActionListener,MouseListener,IClientView ,WindowListener{

	private JPanel jContentPane = null;
	public JImagePane Main_TopPanel = null;
	public  JImagePane Main_BottomPanel = null;
	private JPanel Tab_FriendPanel = new JPanel();
	private JPanel Tab_FriendPanel2 = new JPanel();
	private JPanel Tab_GroupPanel = new JPanel();
	private JPanel Tab_RecordPanel = new JPanel();

	private JTabbedPane Main_FriendTabbedPane = null;
	private JPanel jPanel = null;
	public  JImagePane HeadImagePanel1 = null;
	private JButton MinBtn = null;
	private JButton MaxBtn = null;
	private JButton ExitBtn = null;
	public static String uid = null;
	static QQMainFaceView thisClass = new QQMainFaceView();
	
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private JPanel Main_StatePanel = null;
	private JLabel UserNameLabel = null;
	private JTextField SignatureText = null;
	private JButton btnSpace = null;
	private JButton btnMicro = null;
	private JButton btninfomation = null;
	private JButton btnAddFriend = null;
	private JButton btnFind = null;
	private JList listFamily = null;
	private JList listColleague =null;
	private JList listGoodFriend = null;
	JMenuBar jBar = new JMenuBar();
	JMenu jMenu;
	JMenuItem[] jMenuItems = new JMenuItem[3];

	private JLabel lblGoodFriend = null;//朋友
	private JScrollPane hyScrollPane = null;
	private JLabel lblFamily = null;//家人
	private JLabel lblColleague = null;//同事
	private JPanel jPanel11 = null;
	private List<User> userList = new ArrayList<User>();
	private JPopupMenu myPopForColleague = new JPopupMenu();
	private JMenuItem del;
	private String del_id;
	private JMenuItem getUserInfo;
	private JMenuItem sendFile;
	private ChooseProtraitView chooseProtrait = null;
	public String Txpath = null;
	private ModifyInfoView mi = null;
	private String filePath;
	private MyFile myfile;
	private boolean isSend = false;
	
	// 声音
	private static File file, file2;
	private static URL cb, cb2;
	private static AudioClip aau, aau2;
	/**
	 * This is the default constructor
	 */
	public QQMainFaceView() {
		System.out.println(" QQMainFace"+"    add");
		ManageAllView.addClientView("QQMainFace", this);
		
		initialize();
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		toCenter();
		aau.play();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	ImageIcon rightIcon = new ImageIcon("MainImages/you_Triangle.gif");
	ImageIcon DownIcon = new ImageIcon("MainImages/xia_Triangle.gif");

	private void initialize() {//初始化用户界面
		del = new JMenuItem("删除好友");
		del.addActionListener(this);
		this.getUserInfo = new JMenuItem("查看用户资料");
		this.getUserInfo.addActionListener(this);		
		this.sendFile = new JMenuItem("发送文件");
		this.sendFile.addActionListener(this);
		
		this.myPopForColleague.add(del);
		this.myPopForColleague.add(this.getUserInfo);
		this.myPopForColleague.add(sendFile);
		
		lblColleague = new JLabel();
		lblColleague.setText("同事");
		lblColleague.setIcon(rightIcon);
		lblColleague.setSize(new Dimension(275, 25));
		lblColleague.setLocation(new Point(0, 290));
		lblColleague.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				lblColleague.setIcon(DownIcon);
				lblFamily.setIcon(rightIcon);
				lblGoodFriend.setIcon(rightIcon);
				jPanel11.removeAll();
				getTab_Colleague();
				hyScrollPane.setLocation(new Point(0, 85));
				lblFamily.setLocation(new Point(0, 30));
				lblColleague.setLocation(new Point(0, 55));

			}
		});
		lblFamily = new JLabel();
		lblFamily.setText("家人");
		lblFamily.setIcon(rightIcon);
		lblFamily.setSize(new Dimension(275, 25));
		lblFamily.setLocation(new Point(0, 262));
		lblFamily.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				lblFamily.setIcon(DownIcon);
				lblGoodFriend.setIcon(rightIcon);
				lblColleague.setIcon(rightIcon);
				jPanel11.removeAll();

				getTab_Family();
				hyScrollPane.setLocation(new Point(0, 55));
				lblFamily.setLocation(new Point(0, 30));
				lblColleague.setLocation(new Point(0, 285));
			}
		});

		lblGoodFriend = new JLabel();
		lblGoodFriend.setText("朋友");
		lblGoodFriend.setIcon(DownIcon);
		lblGoodFriend.setSize(new Dimension(278, 25));
		lblGoodFriend.setLocation(new Point(0, 5));
		lblGoodFriend.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				lblGoodFriend.setIcon(DownIcon);
				lblColleague.setIcon(rightIcon);
				lblFamily.setIcon(rightIcon);

				jPanel11.removeAll();

				getTab_GoodFriend();
				hyScrollPane.setLocation(new Point(0, 30));
				lblFamily.setLocation(new Point(0, 260));
				lblColleague.setLocation(new Point(0, 285));

			}
		});
		
		// 消息提示声音
		try{
		file = new File("sounds/1.wav");
		cb = file.toURL();
		aau = Applet.newAudioClip(cb);
		// 上线提示声音
		file2 = new File("sounds/2.wav");
		cb2 = file2.toURL();
		aau2 = Applet.newAudioClip(cb2);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		
		Tab_GroupPanel.setSize(new Dimension());
		Tab_FriendPanel2.setSize(new Dimension());
		Tab_FriendPanel.setLayout(null);
		Tab_FriendPanel.setSize(new Dimension());
		Tab_FriendPanel.add(lblGoodFriend, null);
		Tab_FriendPanel.add(getHyScrollPane(), null);
		
		Tab_FriendPanel.add(lblFamily, null);
		Tab_FriendPanel.add(lblColleague, null);
		Tab_RecordPanel.setLayout(null);
		Tab_RecordPanel.setSize(new Dimension());
		this.setSize(287, 638);
		this.setIconImage(new ImageIcon(ManageUserList.user.getTxpath()).getImage());
		this.setContentPane(getJContentPane());
		this.setTitle("我的QQ2014");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getMain_TopPanel(), null);
			jContentPane.add(getMain_BottomPanel(), null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Main_TopPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMain_TopPanel() {//获取顶部皮肤
		if (Main_TopPanel == null) {
			ImageIcon icon = new ImageIcon("MainImages/xiaoxi.gif");
			this.btninfomation = new JButton(icon);
			this.btninfomation.setOpaque(false);
			this.btninfomation.setText(""+ManageNoReadMsg.msgCnt);// 显示消息数
			this.btninfomation.setIconTextGap(0);
			this.btninfomation.setLocation(new Point(210, 100));
			this.btninfomation.setFont(new Font("Dialog", Font.PLAIN, 12));
			this.btninfomation.setSize(new Dimension(65, 20));
			this.btninfomation.setToolTipText("消息盒子");
			this.btninfomation.addActionListener(this);

			this.btnMicro = new JButton(new ImageIcon(	//换肤
					"MainImages/youxiang.gif"));
			this.btnMicro.setOpaque(false);
			this.btnMicro.setIconTextGap(0);
			this.btnMicro.setLocation(new Point(110, 100));
			this.btnMicro.setFont(new Font("Dialog", Font.PLAIN, 12));
			this.btnMicro.setSize(new Dimension(70, 20));
			this.btnMicro.setToolTipText("修改您的信息");
			this.btnMicro.addActionListener(this);

			this.btnSpace = new JButton(new ImageIcon(
					"MainImages/kongjian.gif"));
			this.btnSpace.setOpaque(false);
			this.btnSpace.setLocation(new Point(10, 100));
			this.btnSpace.setFont(new Font("Dialog", Font.PLAIN, 12));
			this.btnSpace.setSize(new Dimension(70, 20));
			this.btnSpace.addActionListener(this);
			this.btnSpace.setToolTipText("关于我们");

			UserNameLabel = new JLabel(); // 用户昵称
			UserNameLabel.setText(ManageUserList.user.getUserName()+"("+ManageUserList.getUser().getUserId()+")");
			UserNameLabel.setLocation(new Point(130, 30));
			UserNameLabel.setForeground(Color.white);
			UserNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			UserNameLabel.setSize(new Dimension(87, 20));

			Main_TopPanel = new JImagePane(new ImageIcon(
					"headImages/"+ManageUserList.user.getSkinPath()).getImage(),
					JImagePane.SCALED , "headImages/"+ManageUserList.user.getSkinPath());
			Main_TopPanel.setLayout(null);
			Main_TopPanel.setSize(new Dimension(287, 137));
			Main_TopPanel.setLocation(new Point(0, 0));
			Main_TopPanel.add(getHeadImagePanel1(), null);
			Main_TopPanel.add(getMinBtn(), null);
			Main_TopPanel.add(getMaxBtn(), null);
			Main_TopPanel.add(getExitBtn(), null);
			Main_TopPanel.add(getMain_StatePanel(), null);
			Main_TopPanel.add(UserNameLabel, null);
			Main_TopPanel.add(getSignatureText(), null);
			Main_TopPanel.add(this.btnSpace, null);
			Main_TopPanel.add(this.btnMicro, null);
			Main_TopPanel.add(this.btninfomation, null);

			setDragable();
		}
		return Main_TopPanel;
	}

	/**
	 * This method initializes Main_BottomPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMain_BottomPanel() {//底图
		if (Main_BottomPanel == null) {
			this.btnFind = new JButton(new ImageIcon("MainImages/AddF2.gif"));
			this.btnFind.setOpaque(false);
			this.btnFind.setText("3");// 显示消息数
			this.btnFind.setLocation(new Point(40, 20));
			this.btnFind.setFont(new Font("Dialog", Font.PLAIN, 12));
			this.btnFind.setSize(new Dimension(55, 20));
			this.btnFind.setToolTipText("查找好友");
			this.btnFind.addActionListener(this);
			
			this.btnAddFriend = new JButton(new ImageIcon("MainImages/notify.gif"));
			this.btnAddFriend.setOpaque(false);
			this.btnAddFriend.setLocation(new Point(150 , 20));
			this.btnAddFriend.setSize(new Dimension(35 , 20));
			this.btnAddFriend.setToolTipText("好友添加提示");
			this.btnAddFriend.addActionListener(this);
			
			Main_BottomPanel = new JImagePane(new ImageIcon(
					"bottom/"+ManageUserList.user.getSkinPath()).getImage(),
					JImagePane.SCALED , "bottom/"+ManageUserList.user.getSkinPath());
			Main_BottomPanel.setLayout(null);
			Main_BottomPanel.setSize(new Dimension(287, 63));
			Main_BottomPanel.setLocation(new Point(0, 577));
			Main_BottomPanel.add(this.btnFind , null);
			Main_BottomPanel.add(this.btnAddFriend , null);
		}
		return Main_BottomPanel;
	}

	/**
	 * This method initializes Main_FriendTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getMain_FriendTabbedPane() {// 标签容器
		if (Main_FriendTabbedPane == null) {
			Main_FriendTabbedPane = new JTabbedPane(JTabbedPane.TOP,
					JTabbedPane.SCROLL_TAB_LAYOUT);
			ImageIcon icon;
			icon = new ImageIcon("MainImages/Tab_4.png");
			Main_FriendTabbedPane.addTab(null, icon, Tab_FriendPanel);

		}
		return Main_FriendTabbedPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {//获取加载好友、家人、同事的panel
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setLocation(new Point(0, 138));
			jPanel.setSize(new Dimension(287, 445));
			jPanel.add(getMain_FriendTabbedPane(), gridBagConstraints);
		}
		return jPanel;
	}

	public void toCenter() {// 居中显示
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width / 2;
		int screenHeight = screenSize.height / 2;
		int height = this.getHeight();
		int width = this.getWidth();
		this.setLocation(screenWidth - width / 2, screenHeight - height / 2);
	}

	
	  private void setDragable() //下面拖动窗体的方法 
	  {
		  this.addMouseListener(new java.awt.event.MouseAdapter() { 
			  public void mouseReleased(java.awt.event.MouseEvent e) 
			  { 
				  isDragged = false;
				  thisClass.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
		 	} 
			  public void mousePressed(java.awt.event.MouseEvent e) 
			  { 
				  tmp = new Point(e.getX(),e.getY()); 
				  isDragged = true; 
				  thisClass.setCursor(new Cursor(Cursor.MOVE_CURSOR)); 
	         } 
	  });
	 
	
	  this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
		  public void mouseDragged(java.awt.event.MouseEvent e) 
		  { 
			  if (isDragged) {
				  loc = new Point(thisClass.getLocation().x + e.getX() - tmp.x,
						  thisClass.getLocation().y + e.getY() - tmp.y);
				  thisClass.setLocation(loc); 
			  } 
		  } 
		  }); 
    }
	 
	/**
	 * This method initializes HeadImagePanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getHeadImagePanel1() {//获取头像
		if (HeadImagePanel1 == null) {
			HeadImagePanel1 = new JImagePane(new ImageIcon(
					ManageUserList.user.getTxpath()).getImage(), JImagePane.SCALED , "Tx/10.jpg");
			HeadImagePanel1.addMouseListener(this);
			HeadImagePanel1.setLayout(new GridBagLayout());
			HeadImagePanel1.setLocation(new Point(5, 30));
			HeadImagePanel1.setSize(new Dimension(65, 65));
		}
		return HeadImagePanel1;
	}

	/**
	 * This method initializes MinBtn
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getMinBtn() {//自制最小化按钮
		if (MinBtn == null) {
			MinBtn = new JButton();
			MinBtn.setOpaque(false);
			MinBtn.setBackground(new Color(2, 2, 2));
			MinBtn.setSize(new Dimension(30, 20));
			MinBtn.setLocation(new Point(223, 0));
			MinBtn.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					 thisClass.setExtendedState(JFrame.ICONIFIED);
				}
			});
		}
		return MinBtn;
	}

	/**
	 * This method initializes MaxBtn
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getMaxBtn() {//自制皮肤按钮
		if (MaxBtn == null) {
			MaxBtn = new JButton();
			MaxBtn.setOpaque(false);
			MaxBtn.setBackground(new Color(2, 2, 2));
			MaxBtn.setSize(new Dimension(30, 20));
			MaxBtn.setLocation(new Point(196, 0));
			MaxBtn.setToolTipText("换肤");
			MaxBtn.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					 new ChooseSkinView(thisClass);
				}
			});
		}
		return MaxBtn;
	}

	/**
	 * This method initializes ExitBtn
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getExitBtn() {//自制关闭安钮
		if (ExitBtn == null) {
			ExitBtn = new JButton();
			ExitBtn.setOpaque(false);
			ExitBtn.setBackground(new Color(2, 2, 2));
			ExitBtn.setSize(new Dimension(33, 20));
			ExitBtn.setLocation(new Point(252, 0));
			ExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.exit(0);
				}
			});
		}
		return ExitBtn;
	}

	/**
	 * This method initializes Main_StatePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMain_StatePanel() {//显示用户状态---未做
		if (Main_StatePanel == null) {
			Main_StatePanel = new JImagePane(new ImageIcon(
					"MainImages/zt.jpg").getImage(), JImagePane.SCALED , "MainImages/zt.jpg");
			Main_StatePanel.setLayout(null);
			Main_StatePanel.setSize(new Dimension(36, 21));
			Main_StatePanel.setLocation(new Point(80, 30));
			Main_StatePanel.setOpaque(false);
			StateMenu();
			Main_StatePanel.add(jBar, null);
		}
		return Main_StatePanel;
	}

	private void StateMenu() { // 菜单设置
		setLayout(null);
		ImageIcon icmr = new ImageIcon("images/zx.jpg");
		jMenu = new JMenu("  ");
		jMenu.setIcon(icmr);
		jMenu.setOpaque(false);
		String imageStr[] = { "zx.jpg", "ys.jpg", "qw.jpg" };
		String NameStr[] = { "在线", "隐身", "Q我" };
		for (int i = 0; i < 3; i++) {
			ImageIcon ic = new ImageIcon("images/" + imageStr[i]);
			jMenuItems[i] = new JMenuItem(NameStr[i], ic);
			jMenuItems[i].addActionListener(this);
			jMenu.add(jMenuItems[i]);
		}
		jBar.add(jMenu);
		jBar.setBounds(0, 0, 40, 20);
		jBar.setOpaque(false);
		jBar.setBackground(new Color(2, 2, 2));
		jBar.setVisible(true);
		add(jBar);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()== this.btninfomation)
		{
			this.btninfomation.setText("0");
			ManageNoReadMsg.msgCnt = 0;
			
			new MessageNotifyView();
			
		}
		if(e.getSource() == this.sendFile)
		{
			this.isSend = true;
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("选择文件框"); // 标题哦...
			chooser.showDialog(getContentPane(), "选择"); // 这是按钮的名字..

			// 判定是否选择了文件
			if (chooser.getSelectedFile() != null) {
				// 获取路径
				filePath = chooser.getSelectedFile().getPath();
				File file = new File(filePath);
				
				// 文件为空
				
				if (file.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(),
							filePath + "文件为空,不允许发送.");
					return;
				}
			}
			System.out.println("文件名为："+filePath);
			if(filePath == null)
			{
				JOptionPane.showMessageDialog(getContentPane(),
						filePath + "文件为空,不允许发送.");
				return;
			}
			//获取文件
			myfile = new MyFile();
			
			DataInputStream ois = null;
			try {
				ois = new DataInputStream(new FileInputStream(filePath));
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			List<byte[]> list = new ArrayList<byte[]>();
			byte[] buffer = new byte[1024*1024];
			int readByte = -1;
			try {
				while((readByte = ois.read(buffer, 0, 1024*1024)) != -1)
				{
					list.add(buffer);
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			//文件包
			myfile.setFileName(this.filePath);
			myfile.setFileContext(list);
			
			//消息包
			Message msg = new Message();
			msg.setMessageType(MessageType.isRecv);
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(this.del_id);
			
			//发包
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == this.btnMicro)
		{
			if(this.mi == null)
			{
				this.mi = new ModifyInfoView(ManageUserList.user);
			}
			else
				this.mi.setVisible(true);
			
		}
		if(e.getSource() == this.btnSpace)
		{
			new AboutUsView();
		}
		if(e.getSource() == this.del)
		{
			Message msg = new Message();
			msg.setMessageType(MessageType.del_friends);
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(ManageUserList.user.getUserId());
			msg.setContext(this.del_id);
			msg.setSendTime(DateTime.getTimer());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == this.getUserInfo)
		{	
			Message msg = new Message();
			
			msg.setMessageType(MessageType.getUserInfo);
			msg.setContext(this.del_id);
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(ManageUserList.user.getUserId());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == this.SignatureText)
		{
			JOptionPane.showMessageDialog(null, "签名修改成功");
			Message msg = new Message();
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(ManageUserList.user.getUserId());
			msg.setMessageType(MessageType.Signature);
			msg.setContext(this.SignatureText.getText());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}
		if(e.getSource() == this.btnFind)
		{
			new FriendFindView();
		}
		if(e.getSource() == this.btnAddFriend)
		{
			//获取一个通知消息
			
			Message msg = ManageNotification.getOneNotificatoon();
			switch(msg.getMessageType()){
			case MessageType.add_succ:{
				JOptionPane.showMessageDialog(this, "添加好友成功");
				//Update
				ManageUserList.setUserList(((User)msg.getContext()).getUserList());
				
				if(this.lblGoodFriend.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
				{
					System.out.println("this.jLabel----->"+this.lblGoodFriend.getIcon().toString());
					this.getTab_GoodFriend();
				}
				else if(this.lblFamily.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
				{
					System.out.println("this.lblFamily----->"+this.lblFamily.getIcon().toString());
					this.getTab_Family();
				}else if(this.lblColleague.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
				{
					System.out.println("this.lblColleague----->"+this.lblColleague.getIcon().toString());
					this.getTab_Colleague();
				}
				
			}break ;
			case MessageType.add_fail:{
				
				JOptionPane.showMessageDialog(this, msg.getContext().toString());
			}break;
			case MessageType.addFriend:{
				new FriendRequestView(msg);
			}break;
			default:
				JOptionPane.showMessageDialog(this, "没有您的通知消息");
			}
			if(ManageNotification.isEmpty()){				
				this.btnAddFriend.setIcon(new ImageIcon("MainImages/notify.gif"));
			}
			//打开FriendRequestView  好友添加通知  设置btnAddFriend的图片
			
			System.out.println("打开FriendRequestView  好友添加通知");
		}
	}

	/**
	 * This method initializes SignatureText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSignatureText() { //个性签名
		if (SignatureText == null) {
			SignatureText = new JTextField();
			this.SignatureText.addActionListener(this);
			System.out.println(ManageUserList.getUser().getSignature());
			SignatureText.setText(ManageUserList.getUser().getSignature());
			SignatureText.setSize(new Dimension(162, 20));
			SignatureText.setLocation(new Point(80, 55));
			SignatureText.setOpaque(false);
		}
		return SignatureText;
	}

	/**
	 * This method initializes hyScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getHyScrollPane() {//获取SCrollPanel
		if (hyScrollPane == null) {
			hyScrollPane = new JScrollPane();

			hyScrollPane.setLocation(new Point(0, 31));
			hyScrollPane.setViewportView(getJPanel11());
			hyScrollPane.setSize(new Dimension(278, 231));
		}
		return hyScrollPane;
	}

	public void getTab_GoodFriend() { // 初始化好友列表
		hyScrollPane.setViewportView(getJPanel11());

		jPanel11.removeAll();
		this.listGoodFriend = new JList();
		DefaultListModel listModel = new DefaultListModel();
		this.listGoodFriend.setModel(listModel);
		this.listGoodFriend.setCellRenderer(new MySelfCell());//设置自绘cell
		
		List<User> userList = ManageUserList.userListForFriend;
		jPanel11.add(this.listGoodFriend);
		
		String txPath = null;
		for(int i = 0 ; i < userList.size() ; ++i)
		{
			if(userList.get(i).getType() == 1)
				txPath = userList.get(i).getTxpath();
			else
				txPath = "Y"+userList.get(i).getTxpath();
			listModel.addElement(new MyPane(new ImageIcon(txPath).getImage() , userList.get(i).getUserId()+":"+userList.get(i).getUserName()));		
		}
		
		this.listGoodFriend.add(this.myPopForColleague);
		this.listGoodFriend.addMouseListener(this);

		
		jPanel11.setVisible(true);
	}

	private void getTab_Family() { // 初始化家人列表
		hyScrollPane.setViewportView(getJPanel11());
			
		jPanel11.removeAll();
		this.listFamily = new JList();
		DefaultListModel listModel = new DefaultListModel();
		this.listFamily.setModel(listModel);
		this.listFamily.setCellRenderer(new MySelfCell());//设置自绘cell
		
		List<User> userList = ManageUserList.userListForFamily;
		jPanel11.add(this.listFamily);
		
		String txPath = null;
		for(int i = 0 ; i < userList.size() ; ++i)
		{
			System.out.println(userList.get(i).getUserName());
			if(userList.get(i).getType() == 1)
				txPath = userList.get(i).getTxpath();
			else
				txPath = "Y"+userList.get(i).getTxpath();
			listModel.addElement(new MyPane(new ImageIcon(txPath).getImage() , userList.get(i).getUserId()+":"+userList.get(i).getUserName()));		
		}
		
		this.listFamily.addMouseListener(this);
		this.listFamily.add(this.myPopForColleague);
		
		
		jPanel11.setVisible(true);
	}

	private void getTab_Colleague() { // 初始化同事列表
		hyScrollPane.setViewportView(getJPanel11());
		
		jPanel11.removeAll();
		this.listColleague = new JList();
		DefaultListModel listModel = new DefaultListModel();
		this.listColleague.setModel(listModel);
		this.listColleague.setCellRenderer(new MySelfCell());//设置自绘cell
		
		List<User> userList = ManageUserList.userListForColleague;
		jPanel11.add(this.listColleague);
		
		String txPath = null;
		for(int i = 0 ; i < userList.size() ; ++i)
		{
			if(userList.get(i).getType() == 1)
				txPath = userList.get(i).getTxpath();
			else
				txPath = "Y"+userList.get(i).getTxpath();
			listModel.addElement(new MyPane(new ImageIcon(txPath).getImage() , userList.get(i).getUserId()+":"+userList.get(i).getUserName()));		
		}
		
		this.listColleague.add(this.myPopForColleague);
		this.listColleague.addMouseListener(this);
		
		
		jPanel11.setVisible(true);
		//userList.clear();

	}

	/**
	 * This method initializes jPanel11
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel11() {//获取好友panel
		if (jPanel11 == null) {
			jPanel11 = new JPanel(new GridLayout(11, 1, 4, 4));
			getTab_GoodFriend();
		}
		return jPanel11;
	}

	public static void main(String[] args) {
		QQMainFaceView.thisClass.show();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String tmp = null;
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(e.getSource() == this.HeadImagePanel1)
			{
				if(chooseProtrait==null)
					chooseProtrait = new ChooseProtraitView(this , false);
				else
					chooseProtrait.setVisible(true);
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 )
		{
			
			if(e.getSource() == this.listGoodFriend)
			{
				tmp = ((MyPane)this.listGoodFriend.getSelectedValue()).getStr();
				System.out.println("this.listGoodFriend双击"+tmp);
				
			}
			if(e.getSource() == this.listColleague)
			{
				tmp = ((MyPane)this.listColleague.getSelectedValue()).getStr();
			}
			if(e.getSource() == this.listFamily)
			{
				tmp = ((MyPane)this.listFamily.getSelectedValue()).getStr();
			}
			
			String[] friendNo = tmp.split(":");
			this.del_id = friendNo[0];
			String viewName = ManageUserList.user.getUserId().trim()+ManageUserList.queryUser(friendNo[0]).getUserId().trim();
			if(!ManageAllView.isFound(viewName)){
	        ChatView qqChat = new ChatView(ManageUserList.user, ManageUserList.queryUser(friendNo[0]));}
		}else if(e.getButton() == 3)
		{
			if(e.getSource() == this.listGoodFriend)
			{
				if(this.listGoodFriend.getSelectedValue() == null)
				{
					JOptionPane.showMessageDialog(null, "请选中要操作的用户");
					return;
				}
				System.out.println("this.listGoodFriend--"+((MyPane)this.listGoodFriend.getSelectedValue()).getStr());
				String temp = ((MyPane)this.listGoodFriend.getSelectedValue()).getStr();
				this.del_id = temp.substring(0 , temp.indexOf(":"));
				System.out.println("-----"+del_id+"---------");
				this.myPopForColleague.show(this.listGoodFriend, e.getX(), e.getY());
				
			}else if(e.getSource() == this.listColleague)
			{
				if(this.listColleague.getSelectedValue() == null)
				{
					JOptionPane.showMessageDialog(null, "请选中要操作的用户");
					return;
				}
				System.out.println("this.listColleague--"+((MyPane)this.listColleague.getSelectedValue()).getStr());
				String temp = ((MyPane)this.listColleague.getSelectedValue()).getStr();
				this.del_id = temp.substring(0 , temp.indexOf(":"));
				System.out.println("-----"+del_id+"---------");
				this.myPopForColleague.show(this.listColleague, e.getX(), e.getY());
			}else if(e.getSource() == this.listFamily)
			{
				if(this.listFamily.getSelectedValue() == null)
				{
					JOptionPane.showMessageDialog(null, "请选中要操作的用户");
					return;
				}
				System.out.println("this.listFamily--"+((MyPane)this.listFamily.getSelectedValue()).getStr());
				String temp = ((MyPane)this.listFamily.getSelectedValue()).getStr();
				this.del_id = temp.substring(0 , temp.indexOf(":"));
				System.out.println("-----"+del_id+"---------");
				this.myPopForColleague.show(this.listFamily, e.getX(), e.getY());
			}
			
		}
	}

	private void getFriendInfo(String friendUser)//获取好友资料
	{
		Message msg = new Message();
		
		msg.setMessageType(MessageType.getUserInfo);
		msg.setContext(friendUser);
		msg.setGetter(ManageUserList.user.getUserId());
		msg.setSender(ManageUserList.user.getUserId());
		
		try {
			ManageClientThread.getClientSocketThread("socketThread")
			.send(msg);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procedureMsg(Message msg) {
		// TODO Auto-generated method stub
		if(msg.getMessageType()==MessageType.chat){
			
			this.btninfomation.setText(""+ManageNoReadMsg.msgCnt);
		}else if(msg.getMessageType()==MessageType.add_succ){
			this.btnAddFriend.setIcon(new ImageIcon("MainImages/notify2.gif"));
			aau2.play();
			//更新好友列表
			
		}else if(msg.getMessageType()==MessageType.add_fail){
			this.btnAddFriend.setIcon(new ImageIcon("MainImages/notify2.gif"));
			aau2.play();
			
		}else if(msg.getMessageType()==MessageType.addFriend){
			this.btnAddFriend.setIcon(new ImageIcon("MainImages/notify2.gif"));
			aau2.play();
	}else if(msg.getMessageType()==MessageType.show_succ){
		System.out.println("show_succ xxxxxxxxx");
		ManageUserList.setUserList(((User)msg.getContext()).getUserList());
		//System.out.println(jLabel.getIcon().toString());
		if(this.lblGoodFriend.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
		{
			System.out.println("this.lblGoodFriend----->"+this.lblGoodFriend.getIcon().toString());
			this.getTab_GoodFriend();
		}
		else if(this.lblFamily.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
		{
			System.out.println("this.txLabel----->"+this.lblFamily.getIcon().toString());
			this.getTab_Family();
		}else if(this.lblColleague.getIcon().toString().equals("MainImages/xia_Triangle.gif"))
		{
			System.out.println("this.hmdLabel----->"+this.lblColleague.getIcon().toString());
			this.getTab_Colleague();
		}
		
	}else if(msg.getMessageType() == MessageType.Signature)
	{
		this.SignatureText.setText(msg.getContext().toString());
	}
	else if(msg.getMessageType() == MessageType.del_succ)
	{
		JOptionPane.showMessageDialog(this, "删除成功");
	}
	else if(msg.getMessageType() == MessageType.getUserInfo)
	{
		new ShowView(msg);
	}
	else if(msg.getMessageType() == MessageType.TX_images)
	{
		JOptionPane.showMessageDialog(this, "头像修改成功");
	}else if(msg.getMessageType() == MessageType.other)
	{
		JOptionPane.showMessageDialog(this, "信息修改成功");
	}else if(msg.getMessageType() == MessageType.refuseRecv){
		JOptionPane.showMessageDialog(this, msg.getContext().toString());
	}else if(msg.getMessageType() == MessageType.isRecv)
	{
		int res = JOptionPane.showConfirmDialog(this, "是否接收"+msg.getGetter()+"传来的文件？", "提示", JOptionPane.YES_NO_OPTION);
		
		Message rmsg = new Message();
		if(res == JOptionPane.YES_OPTION)
		{
			rmsg.setMessageType(MessageType.agreeRecv);
			rmsg.setGetter(msg.getSender());
			rmsg.setSender(msg.getGetter());
			System.out.println("发送同意包");
		}
		else if(res == JOptionPane.NO_OPTION)
		{
			rmsg.setMessageType(MessageType.refuseRecv);
			rmsg.setGetter(msg.getSender());
			rmsg.setSender(msg.getGetter());
		}
		//发包
		try {
			ManageClientThread.getClientSocketThread("socketThread")
			.send(rmsg);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}else if(msg.getMessageType() == MessageType.agreeRecv)
	{
		//打包文件发出
		Message myMsg = new Message();
		myMsg.setMessageType(MessageType.sendFile);
		myMsg.setGetter(msg.getSender());
		myMsg.setSender(msg.getGetter());
		myMsg.setContext(this.myfile);
		myMsg.setSendTime(DateTime.getTimer());
		
		//发包
		try {
			ManageClientThread.getClientSocketThread("socketThread")
			.send(myMsg);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}else if(msg.getMessageType() == MessageType.sendFile)
	{
		
		MyFile recvFile = (MyFile)msg.getContext();
		//接收文件
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("保存文件框"); // 标题哦...
		chooser.showDialog(getContentPane(), "保存"); // 这是按钮的名字..	
		//chooser.setSelectedFile(new File(recvFile.getFileName()));
		String saveFilePath =chooser.getSelectedFile().toString();//保存路径
		System.out.println("文件保存路径"+saveFilePath);
		
		
		
		//输出
		DataOutputStream oos = null;
		try {
			oos = new DataOutputStream(new FileOutputStream(saveFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ; i < recvFile.getFileContext().size() ; ++i)
		{
			try {
				oos.write((byte[]) recvFile.getFileContext().get(i), 0, 1024*1024);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(this, "文件传输成功！");
		this.isSend = false;
		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(!this.isSend)
		{
			System.exit(0);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "文件正在传输，请稍后");
			return;
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
