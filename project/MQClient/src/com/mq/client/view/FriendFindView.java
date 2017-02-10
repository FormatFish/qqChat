package com.mq.client.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.mq.client.Manage.ManageAllView;
import com.mq.client.Manage.ManageClientThread;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.util.FillWidth;
import com.mq.client.util.IClientView;


public class FriendFindView extends JDialog implements ActionListener,IClientView,
WindowListener{
	
	
	public static void main(String[] args)
	{
		FriendFindView ac = new FriendFindView();
	}
	
	
	private JTabbedPane paneFindWay = new JTabbedPane();
	
	private JPanel paneBtn = new JPanel();
	private JButton btnFind = new JButton("查找");
	private JButton btnClose = new JButton("关闭");
	
	private JButton btnAddFriend = new JButton("加为好友");
	
	private JPanel paneBaseFind = new JPanel();
	
	
	//private JPanel addWhichCase = new JPanel();
	
	private CardLayout card = new CardLayout();
	
	private JPanel paneBaseFirst = new JPanel();
	private JPanel paneBaseSecond = new JPanel();
	
	private JLabel lblInfo = new JLabel("在此，您可以设置精确的查询条件来查找用户。");
	private JLabel btnNameExactFind = new JLabel("按昵称或QQ号查询");
	private JTextField txtName = new JTextField();
	//private JTextField txtQQ = new JTextField();
	
	//private JLabel  btnQQExactFind = new JLabel("按QQ号查询");
	private JLabel lblExactFind = new JLabel();
	
	private JLabel lblInfo2 = new JLabel("以下是QQ为您查找到的用户。");

	
	
	
	
	public JTable tableUser = null;
	

	
	private Color bgColor = new Color(252,254,252);
	private Message msg = new Message();
	
	public FriendFindView() {
		
		ManageAllView.addClientView("queryFriends2",this);
		this.addWindowListener(this);
		
		setTitle("QQ   查找/添加好友");
		setSize(400,325);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		init();
		
		setVisible(true);
		

	}
	
	private void init(){
		
		paneBtn.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		
		paneBtn.add(btnFind);
		
		paneBtn.add(btnClose);
		
		
		lblInfo.setPreferredSize(new Dimension(350,34));
		lblInfo.setHorizontalAlignment(SwingConstants.LEFT);
	
	
		
		btnNameExactFind.setBackground(bgColor);
		btnNameExactFind.setPreferredSize(new Dimension(218,20));
		txtName.setPreferredSize(new Dimension(218,20));
			
		paneBaseFirst.setLayout(new FlowLayout(FlowLayout.CENTER,0,3));
		paneBaseFirst.setOpaque(true);
		paneBaseFirst.setBackground(bgColor);
		paneBaseFirst.add(lblInfo);
		paneBaseFirst.add(btnNameExactFind);
		paneBaseFirst.add(txtName);
		paneBaseFirst.add(lblExactFind);
	
		
		lblInfo2.setPreferredSize(new Dimension(360,34));
		lblInfo2.setOpaque(true);
		lblInfo2.setBackground(bgColor);
		
		paneBaseSecond.setOpaque(true);
		paneBaseSecond.setBackground(new Color(252,254,252));
		paneBaseSecond.add(lblInfo2);
		
		
		paneBaseFind.setLayout(card);
		paneBaseFind.add("first", paneBaseFirst);
		paneBaseFind.add("second", paneBaseSecond);
		
		paneFindWay.add("基本查找",paneBaseFind);
		
		add(new FillWidth(5,5),BorderLayout.NORTH);
		
		add(paneFindWay,BorderLayout.CENTER);
		add(new FillWidth(5,5),BorderLayout.EAST);
		add(new FillWidth(5,5),BorderLayout.WEST);
		add(paneBtn,BorderLayout.SOUTH);
		
		btnFind.addActionListener(this);
		btnClose.addActionListener(this);
		btnAddFriend.addActionListener(this);
	
	}
	
	public void getData()
	{
		String[] columnNames = {"账号","昵称"};
		List<User> userList = new ArrayList<User>();
		userList = ((User)this.msg.getContext()).getUserList();
		Object[][] data = new Object[userList.size()][2];
		for(int i = 0 ; i < userList.size() ; ++i)
		{
			data[i][0] = userList.get(i).getUserId();
			data[i][1] = userList.get(i).getUserName();
		}
		
		
		this.tableUser = new JTable(data, columnNames);
		paneBaseSecond.add(new JScrollPane(this.tableUser));
		this.tableUser.setPreferredScrollableViewportSize(new Dimension(360, 160));
		
	
		
		this.tableUser.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		this.tableUser.setPreferredScrollableViewportSize(new Dimension(360,160));

	}
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(e.getSource()==btnFind){
			
			if(this.txtName.getText().equals(ManageUserList.user.getUserId()))
			{
				JOptionPane.showMessageDialog(this, "对不起，您不能添加自己为好友");
				return;
			}
			Message msg = new Message();
			msg.setGetter(ManageUserList.user.getUserId());
			msg.setSender(ManageUserList.user.getUserId());
			msg.setSendTime((new Date()).toString());
			msg.setMessageType(MessageType.searchFriend);
			msg.setContext(this.txtName.getText());
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
				.send(msg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		
			}
	
		if(e.getSource()==btnClose){
			dispose();
			return;
		}
		
		if(e.getSource()==btnAddFriend){
			
		     int row = -1;
		     System.out.println(this.tableUser.getRowCount());
		     for(int index = 0 ; index < this.tableUser.getRowCount() ; ++index)
		     {
		    	 if(this.tableUser.isRowSelected(index))
		    	 {
		    		 row = index;
		    	 }
		     }
		    
			if(row==-1){
				JOptionPane.showMessageDialog(this, "请选定一个用户!");
				return;
		  }
			
			new FriendResponseView(this , this.tableUser.getValueAt(this.tableUser.getSelectedRow() ,  0).toString());
			
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ManageAllView.removeClientView("QueryFriends2");
		System.out.println("QueryFriends2" + "      remove");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ManageAllView.removeClientView("QueryFriends2");
		System.out.println("QueryFriends2" + "      remove");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void procedureMsg(Message msg) {
		// TODO Auto-generated method stub
		if(msg.getMessageType()==MessageType.searchFriend){
			
			btnFind.removeActionListener(this);
			paneBtn.remove(btnFind);
		
			this.msg = msg;
			this.getData();
			
			paneBtn.add(btnAddFriend,1);
			
			paneBtn.validate();
			paneBtn.repaint();
			card.next(paneBaseFind);
			
			
		}
	}
	
}
	