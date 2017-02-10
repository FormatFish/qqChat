package com.mq.client.view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GridBagLayout;
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
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageClientThread;
import com.mq.client.Manage.ManageNoReadMsg;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.DateTime;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.util.FillWidth;
import com.mq.client.util.IClientView;
import com.mq.client.util.JImagePane;

public class ChatView extends JFrame implements ActionListener, IClientView,
		WindowListener {

	public static void main(String[] args) {
		ChatView abc = new ChatView(new User(), new User());
	}

	private JLabel lblTop = new JLabel();
	private JLabel lblFriendInfo = new JLabel();
	private JLabel lblPhoto = new JLabel();
	private JTextPane txtMessage = new JTextPane();
	private JTextPane txtWrite = new JTextPane();
	private JLabel lblName = new JLabel();

	private JPanel paneFriendInfo = new JPanel();
	private JPanel paneTools = new JPanel();
	private JPanel paneBtn = new JPanel();

	private JButton btnSend = new JButton("发送(Ctrl+Enter)");
	private JButton btnClose = new JButton("关闭");
	private JButton btnRecord = new JButton("聊天记录");

	private Color bgColor = new Color(169, 213, 244);

	private ObjectOutputStream message = null;

	private User friendUser = null;
	private User selfUser = null;
	private String viewName = null;
	private JButton btnExit = null;
	private JButton btnMax = null;
	private JButton btnMin = null;
	private ChatView cf = null;
	private JImagePane friendTx = null;
	private boolean isDragged = false;
	Point loc = null;
	Point tmp = null;

	private static File file;
	private static URL cb;
	private static AudioClip aau;
	// private String noReadMsg = null;

	private Message lastMsg = new Message();

	/**
	 * 窗体的构造函数。
	 * 
	 * @param oos
	 *            输出对象流。
	 * @param friendUser
	 *            FriendUser好友对象。
	 * @param selfUser
	 *            FriendUser自己对象。
	 * @param show
	 *            是否显示。
	 */
	public ChatView(User selfUser, User friendUser) {
		// 提示音
		file = new File("sounds/2.wav");
		try {
			cb = file.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		aau = Applet.newAudioClip(cb);

		this.cf = this;
		this.friendUser = friendUser;
		this.selfUser = selfUser;

		System.out.println("ChatView2中的友方ID"
				+ this.friendUser.getUserId().trim());
		this.viewName = this.selfUser.getUserId().trim()
				+ this.friendUser.getUserId().trim();
		System.out.println("构造聊天窗口，窗口名字是(我的id+好友id)：" + viewName);
		ManageAllView.addClientView(viewName, this);
		this.addWindowListener(this);
		// 获取未读取的信息
		String temp = "";
		while (ManageNoReadMsg.isFound(viewName)) {
			for (int i = 0; i < ManageNoReadMsg.getnoReadChat(viewName).size(); ++i) {
				if (i != 0) {
					temp += "\n";
				}
				temp += (ManageNoReadMsg.getnoReadChat(viewName)).get(i)
						.getContext().toString();

			}

			ManageNoReadMsg.removenoReadChat(viewName);
		}

		System.out.println("未打开读取的消息内容：" + temp);
		if (!temp.trim().equals("")) {
			temp = this.friendUser.getUserId() + " :\n\n" + temp;
		}
		lastMsg.setGetter(this.friendUser.getUserId());
		lastMsg.setContext(temp);
		lastMsg.setSendTime("before:" + DateTime.getTimer());

		setSize(555, 500);

		try {

		} catch (Exception e) {
			System.out.println("错误" + e.getMessage());
		}
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width - getSize().width) / 2,
				(tk.getScreenSize().height - getSize().height) / 2);

		init();

		btnClose.addActionListener(this);
		btnSend.addActionListener(this);
		btnRecord.addActionListener(this);

		this.setUndecorated(true);
		paneBtn.getRootPane().setDefaultButton(btnSend);
		setVisible(true);

	}

	/**
	 * 初始化面板。
	 */
	private void init() {
		lblTop.setPreferredSize(new Dimension(500, 61));
		lblTop.setVerticalAlignment(SwingConstants.TOP);
		lblTop.setIcon(new ImageIcon("chat/" + this.selfUser.getSkinPath()));
		lblTop.setOpaque(true);
		lblTop.setBackground(bgColor);
		lblFriendInfo.setVerticalAlignment(SwingConstants.TOP);
		lblFriendInfo.setPreferredSize(new Dimension(151, 437));
		lblFriendInfo.setIcon(new ImageIcon("images/4.jpg"));
		lblFriendInfo.setOpaque(true);
		lblFriendInfo.setBackground(bgColor);

		btnClose.setFocusPainted(false);
		btnClose.setPreferredSize(new Dimension(60, 20));
		btnClose.setMargin(new Insets(0, 5, 0, 5));
		btnSend.setFocusPainted(false);
		btnSend.setPreferredSize(new Dimension(150, 20));
		btnSend.setMargin(new Insets(0, 5, 0, 5));

		lblPhoto.setPreferredSize(new Dimension(20, 20));
		lblPhoto.setIcon(new ImageIcon("images/2.jpg"));

		JLabel lblInfo = new JLabel(this.friendUser.getUserName() + "("
				+ this.friendUser.getUserId() + ")");

		lblInfo.setPreferredSize(new Dimension(290, 20));
		lblInfo.setForeground(Color.BLUE);

		paneFriendInfo.setOpaque(true);
		paneFriendInfo.setBackground(new Color(205, 237, 255));
		paneFriendInfo.setPreferredSize(new Dimension(337, 25));
		paneFriendInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		paneFriendInfo.add(lblPhoto);
		paneFriendInfo.add(lblInfo);

		paneTools.setOpaque(true);
		paneTools.setBackground(new Color(205, 237, 255));
		paneTools.setPreferredSize(new Dimension(337, 25));
		paneTools.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 5));

		paneBtn.setPreferredSize(new Dimension(337, 30));
		paneBtn.setOpaque(true);
		paneBtn.setBackground(bgColor);
		paneBtn.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
		paneBtn.add(btnClose);
		paneBtn.add(new FillWidth(5, 20, bgColor));
		paneBtn.add(btnSend);
		paneBtn.add(new FillWidth(5, 20, bgColor));
		paneBtn.add(btnRecord);

		JScrollPane spMessage = new JScrollPane(txtMessage);
		spMessage
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spMessage.setBorder(new EmptyBorder(0, 0, 0, 0));

		JScrollPane spWrite = new JScrollPane(txtWrite);
		spWrite.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spWrite.setBorder(new EmptyBorder(0, 0, 0, 0));

		JPanel paneMessage = new JPanel();
		paneMessage.setLayout(new BorderLayout());
		paneMessage.add(paneFriendInfo, BorderLayout.NORTH);
		paneMessage.add(spMessage, BorderLayout.CENTER);

		JPanel paneWrite = new JPanel();
		paneWrite.setPreferredSize(new Dimension(337, 96));
		paneWrite.setLayout(new BorderLayout());
		paneWrite.add(paneTools, BorderLayout.NORTH);
		paneWrite.add(spWrite, BorderLayout.CENTER);
		paneWrite.add(paneBtn, BorderLayout.SOUTH);

		JPanel paneCenter = new JPanel();
		paneCenter.setLayout(new BorderLayout());
		paneCenter.setBorder(new LineBorder(new Color(118, 171, 211)));
		paneCenter.setOpaque(true);
		paneCenter.setBackground(bgColor);
		paneCenter.add(paneMessage, BorderLayout.CENTER);
		paneCenter.add(paneWrite, BorderLayout.SOUTH);

		JPanel paneAll = new JPanel();
		paneAll.setLayout(new BorderLayout());
		paneAll.setOpaque(true);
		paneAll.setBackground(bgColor);
		paneAll.add(paneCenter, BorderLayout.CENTER);
		paneAll.add(paneBtn, BorderLayout.SOUTH);

		setLayout(new BorderLayout());

		add(this.getTx());

		add(lblTop, BorderLayout.NORTH);
		add(this.getExit());
		add(this.getMaxBtn());
		add(this.getMinBtn());
		add(new FillWidth(6, 407, bgColor), BorderLayout.WEST);
		add(lblFriendInfo, BorderLayout.EAST);
		add(paneAll, BorderLayout.CENTER);
		add(new FillWidth(494, 5, bgColor), BorderLayout.SOUTH);
		setDragable();

		paneBtn.getRootPane().setDefaultButton(this.btnSend);
		// 初始化输入框
		txtMessage.setText("\n你可以和" + friendUser.getUserName() + "聊天了" + "\n"+DateTime.getTimer()+"\n");
		// 有未读取的信息时候更新
		if (!lastMsg.getContext().toString().trim().equals("")) {
			String oldTxt = txtMessage.getText();
			String addTxt = "   " + lastMsg.getSendTime() + "\n";

			addTxt += (String) lastMsg.getContext();
			String newTxt = oldTxt + "\n" + addTxt;
			txtMessage.setText(newTxt);
		}
		txtWrite.setText("");

	}

	// 获取用户信息

	// 头像
	private JImagePane getTx() {
		if (this.friendTx == null) {
			this.friendTx = new JImagePane(new ImageIcon(
					this.friendUser.getTxpath()).getImage(), JImagePane.SCALED,
					ManageUserList.FriendUser.getTxpath());
			this.friendTx.setLayout(new GridBagLayout());
			this.friendTx.setLocation(new Point(10, 10));
			this.friendTx.setSize(new Dimension(40, 40));

		}
		return this.friendTx;
	}

	private JButton getExit() {
		if (this.btnExit == null) {
			this.btnExit = new JButton();
			this.btnExit.setOpaque(false);
			this.btnExit.setBackground(new Color(2, 2, 2));
			this.btnExit.setSize(new Dimension(30, 20));
			this.btnExit.setLocation(new Point(525, 0));
			this.btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// System.out.println("mouseClicked()"); // TODO
					// Auto-generated Event stub mouseClicked()
					cf.dispose();
					// System.exit(0);
				}
			});

		}
		return this.btnExit;
	}

	private JButton getMaxBtn() {
		if (this.btnMax == null) {
			this.btnMax = new JButton();
			this.btnMax.setOpaque(false);
			this.btnMax.setBackground(new Color(2, 2, 2));
			this.btnMax.setSize(new Dimension(30, 20));
			this.btnMax.setLocation(new Point(495, 0));
			this.btnMax.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// cf.setExtendedState(JFrame.MAXIMIZED_BOTH);

				}
			});
		}
		return this.btnMax;
	}

	private JButton getMinBtn() {
		if (this.btnMin == null) {
			this.btnMin = new JButton();
			this.btnMin.setOpaque(false);
			this.btnMin.setBackground(new Color(2, 2, 2));
			this.btnMin.setSize(new Dimension(30, 20));
			this.btnMin.setLocation(new Point(465, 0));
			this.btnMin.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					cf.setExtendedState(JFrame.ICONIFIED);

				}
			});
		}
		return this.btnMin;
	}

	private void setDragable() // 下面拖动窗体的方法
	{
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
				cf.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
				cf.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});

		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
					loc = new Point(cf.getLocation().x + e.getX() - tmp.x, cf
							.getLocation().y + e.getY() - tmp.y);
					cf.setLocation(loc);
				}
			}
		});
	}

	/**
	 * 关闭、发送、设置、等事件。
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			this.dispose();
			return;
		}
		if (e.getSource() == this.btnRecord) {
			Message msg = new Message();
			msg.setMessageType(MessageType.chatRecord);
			msg.setGetter(this.selfUser.getUserId());
			msg.setSender(this.friendUser.getUserId());
			msg.setContext(this.selfUser.getUserId());
			try {
				ManageClientThread.getClientSocketThread("socketThread").send(
						msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == btnSend) {
			String content = DateTime.getTimer()+"\n"+this.selfUser.getUserName()+"("+this.friendUser.getUserId()+"):\n\t"+txtWrite.getText();
			if(content.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入聊天内容");
				return;
			}

			// 打包自己输入的文字，发送者 接受者 时间等。。。。发送
			Message m = new Message();
			m.setGetter(this.selfUser.getUserId());
			m.setSender(this.friendUser.getUserId());
			m.setContext(content);
			m.setMessageType(MessageType.chat);
			m.setSendTime(DateTime.getTimer());

			try {
				ManageClientThread.getClientSocketThread("socketThread")
						.send(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			updateTxtMessage(m);

			txtWrite.setText("");

		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		ManageAllView.removeClientView(viewName);
		System.out.println(viewName + "      remove");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		ManageAllView.removeClientView(viewName);
		System.out.println(viewName + "      remove");
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

	@Override
	public void procedureMsg(Message msg) {
		// TODO Auto-generated method stub
		if (msg.getMessageType() == MessageType.chat) {
			aau.play();
			updateTxtMessage(msg);
		} else if (msg.getMessageType() == MessageType.chatRecord) {
			new ChatRecordView(msg);

		}
	}

	/*
	 * public void updateTxtMessage(Message msg) { if
	 * (!msg.getContext().toString().trim().equals("")) { String oldTxt =
	 * txtMessage.getText();
	 * 
	 * String addTxt = ""; if (!msg.getGetter().equals(lastMsg.getGetter()))
	 * addTxt = "	" + msg.getSendTime() + "\n";
	 * 
	 * 
	 * if (!msg.getGetter().trim().equals(selfUser)) { addTxt += msg.getGetter()
	 * + " :	" + (String) msg.getContext(); } else { addTxt += "	" + (String)
	 * msg.getContext() + "		: " + msg.getGetter(); }
	 * 
	 * 
	 * String newTxt = oldTxt + "\n" + addTxt; txtMessage.setText(newTxt);
	 * lastMsg = msg; } }
	 */
	public void updateTxtMessage(Message msg) {
		if (!msg.getContext().toString().trim().equals("")) {
			String oldTxt = txtMessage.getText();

			String addTxt = "";
/*
			if (!msg.getGetter().equals(lastMsg.getGetter())) {
				addTxt = "\n		" + msg.getSendTime() + "\n\n";

				if (!msg.getGetter().trim().equals(selfUser.getUserId())) {
					addTxt += msg.getGetter() + " :	        \n\n";
				} else {
					addTxt += "                                                                                                               : "
							+ msg.getGetter() + "\n\n";
				}
			}*/

			addTxt =  (String) msg.getContext();

			String newTxt = oldTxt + "\n" + addTxt;
			txtMessage.setText(newTxt);
			lastMsg = msg;
		}
	}

}