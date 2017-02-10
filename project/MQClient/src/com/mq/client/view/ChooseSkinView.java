package com.mq.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.mq.client.Manage.ManageClientThread;
import com.mq.client.Manage.ManageUserList;
import com.mq.client.common.DateTime;
import com.mq.client.common.Message;
import com.mq.client.common.MessageType;
import com.mq.client.common.User;
import com.mq.client.util.JImagePane;

public class ChooseSkinView extends JDialog implements MouseListener,ActionListener{
	private static final AbstractButton lblPhoto = null;
	private JImagePane[] imagePane = new JImagePane[10];
	private JLabel lblBoys = new JLabel("系统皮肤（共10个）");
	
	private JLabel lblViewInfo = new JLabel("预览:");
	private JImagePane lblPhotoView = new JImagePane();
	
	private JButton btnP_Ok = new JButton("确定");
	private JButton btnP_Cancle = new JButton("取消");
	private String path = "headImagse/1.jpg";
	private QQMainFaceView mf = null;
	private boolean flag = false;
	private QQMainFaceView qf = null;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ChooseSkinView(QQMainFaceView qf) {		
		this.qf = qf;
		setTitle("选择皮肤");
		setSize(500,440);
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		btnP_Ok.setSize(80,20);
		btnP_Ok.setLocation(300, 375);
		btnP_Ok.addActionListener(this);
		btnP_Cancle.setSize(80,20);
		btnP_Cancle.setLocation(400, 375);
		btnP_Cancle.addActionListener(this);
		
		
		for(int i = 0 ; i < this.imagePane.length ; ++i)
		{
			this.path = "headImages/"+(i+1)+".jpg";///client6/headImages/1.jpg
			
			this.imagePane[i] = new JImagePane(new ImageIcon(this.path).getImage() , JImagePane.SCALED , this.path);
			this.imagePane[i].setPreferredSize(new Dimension(100,100));
			this.imagePane[i].addMouseListener(this);
			this.imagePane[i].setOpaque(true);
			this.imagePane[i].setBackground(Color.WHITE);
			
		}
		//初始化预览头像
		lblPhotoView.setOpaque(true);
		lblPhotoView.setBackground(Color.WHITE);
		lblPhotoView.setPreferredSize(new Dimension(50,50));
		lblPhotoView.setBorder(new LineBorder(Color.DARK_GRAY));
		lblPhotoView.setBackgroundImage(lblPhotoView.getBackgroundImage());
		lblPhotoView.setImageDisplayMode(JImagePane.SCALED);
		lblPhotoView.setSize(50,50);
		lblPhotoView.setLocation(420, 40);
		lblViewInfo.setSize(60,20);
		lblViewInfo.setLocation(425, 10);
		
		initJLabel(lblBoys);
		
		JPanel paneBoys = getPane(0, 10);
		//JPanel paneGirls = getPane(30, 60);
		//JPanel paneAnimals = getPane(60, 96);
		//JPanel paneOthers = getPane(96, 158);
		
		JPanel panePortrait = new JPanel();
		panePortrait.setPreferredSize(new Dimension(500,200));
		panePortrait.setOpaque(true);
		panePortrait.setBackground(Color.WHITE);
		panePortrait.add(lblBoys);
		panePortrait.add(paneBoys);
		
		JScrollPane sp = new JScrollPane(panePortrait);
		sp.setSize(400,350);
		sp.setLocation(10,5);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		setLayout(null);
		
		JPanel paneAll = new JPanel();
		paneAll.setSize(480,365);
		paneAll.setLocation(5, 0);
		paneAll.setOpaque(true);
		paneAll.setBackground(Color.WHITE);
		paneAll.setBorder(new LineBorder(Color.BLACK));
		paneAll.setLayout(null);
		paneAll.add(sp);
		paneAll.add(lblViewInfo);
		paneAll.add(lblPhotoView);
		
		add(paneAll);
		add(btnP_Ok);
		add(btnP_Cancle);
		
		setVisible(true);
	}
	
	/**
	 * 初始化头像的一些面板
	 */
	private void initJLabel(JLabel pane){
		pane.setOpaque(true);
		pane.setBackground(new Color(226,247,254));
		pane.setPreferredSize(new Dimension(500,25));
		pane.setBorder(new LineBorder(Color.BLACK));
	}
	
	/**
	 * 根据开始和结束获得面板。
	 * @param begin 开始的头像位置。
	 * @param end 结束的头像位置。
	 * @return 添加好头像的面板。
	 */
	private JPanel getPane(int begin,int end){
		JPanel pane = new JPanel();
		pane.setOpaque(true);
		pane.setBackground(Color.WHITE);
		pane.setLayout(new GridLayout(0,7,5,5));
		for(int i = begin;i<end;i++)
			pane.add(this.imagePane[i]);
		
		return pane;
	}
	
	public void actionPerformed(ActionEvent e) {
		//点击确定时更改选择的图像
		if(e.getSource()==btnP_Ok){
			String paths = null;
			System.out.println("皮肤路径"+this.path);
			paths = this.path.substring(path.indexOf("/")+1, path.length());
			
			System.out.println("皮肤路径"+paths);
			this.qf.Main_TopPanel.setBackgroundImage(new ImageIcon("headImages/"+paths).getImage());
			this.qf.Main_TopPanel.setImageDisplayMode(JImagePane.SCALED);
			
			this.qf.Main_BottomPanel.setBackgroundImage(new ImageIcon("bottom/"+paths).getImage());
			this.qf.Main_BottomPanel.setImageDisplayMode(JImagePane.SCALED);
			
			ManageUserList.user.setSkinPath(paths);
			
			//发包
			User user = ManageUserList.user;
			//user.setTxpath(this.HeadImagePanel1.getBackgroundImage().toString());
			
			Message msg = new Message();
			msg.setGetter(user.getUserId());
			msg.setSender(user.getUserId());
			msg.setMessageType(MessageType.skinPath);
			msg.setContext(this.path.substring(this.path.indexOf("/")+1, this.path.length()));
			msg.setSendTime(DateTime.getTimer());
			System.out.println("QQMainFace的皮肤路径为"+this.path);
			
			try {
				ManageClientThread.getClientSocketThread("socketThread")
						.send(msg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dispose();
			return;
		}
		//点击确定时关闭选择图像的窗口
		if(e.getSource()==btnP_Cancle){
			dispose();
		}
		
		
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果是图像的按钮时，显示图像到浏览头像面板上
		int i = -1;
		for(i=0;i<this.imagePane.length;i++){
			if(e.getSource()==this.imagePane[i])
				break;
		}
		if(i<this.imagePane.length){
			lblPhotoView.setBackgroundImage(this.imagePane[i].getBackgroundImage());
			this.path = this.imagePane[i].getPath();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public static void main(String[] args)
	{
		//new ChooseSkin();
	}
}