package com.mqserver.view;


import java.awt.BorderLayout;
import java.awt.Color;
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

public class ShowServerLogView extends JFrame implements ActionListener{
	
	
	public static void main(String[] args)
	{
		ShowServerLogView abc = new ShowServerLogView("你好");
	}
	
	private JTextPane txtMessage = new JTextPane();
	private String str ;
	private JPanel paneFriendInfo = new JPanel();
	private JPanel paneTools = new JPanel();
	private JPanel paneBtn = new JPanel();
	private JButton btnClose = new JButton("关闭");
	private Color bgColor = new Color(169,213,244);

	public ShowServerLogView(String str) {
		this.str = str;
		
		
		setSize(494,500);
		
		
		try {
			
		} catch (Exception e) {
			System.out.println("错误"+e.getMessage());
		}
		setResizable(false);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		init();
		
		btnClose.addActionListener(this);
		

		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * 初始化面板。
	 */
	private void init(){

		btnClose.setFocusPainted(false);
		btnClose.setPreferredSize(new Dimension(60,20));
		btnClose.setMargin(new Insets(0,5,0,5));
     	 
		JLabel lblInfo = new JLabel("服务器日志");
	
		paneFriendInfo.setOpaque(true);
		paneFriendInfo.setBackground(new Color(205,237,255));
		paneFriendInfo.setPreferredSize(new Dimension(337,25));
		paneFriendInfo.setLayout(new FlowLayout(FlowLayout.LEFT,5,2));
		paneFriendInfo.add(lblInfo);
		
		paneTools.setOpaque(true);
		paneTools.setBackground(new Color(205,237,255));
		paneTools.setPreferredSize(new Dimension(337,25));
		paneTools.setLayout(new FlowLayout(FlowLayout.LEFT,2,5));

		
		paneBtn.setPreferredSize(new Dimension(337,30));
		paneBtn.setOpaque(true);
		paneBtn.setBackground(bgColor);
		paneBtn.setLayout(new FlowLayout(FlowLayout.RIGHT,0,5));
		paneBtn.add(btnClose);
		paneBtn.add(new FillWidth(5,20,bgColor));

		paneBtn.add(new FillWidth(5,20,bgColor));
		
		JScrollPane spMessage = new JScrollPane(txtMessage);
		txtMessage.setText(str);
		spMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spMessage.setBorder(new EmptyBorder(0,0,0,0));
		
		JPanel paneMessage = new JPanel();
		paneMessage.setLayout(new BorderLayout());
		paneMessage.add(paneFriendInfo,BorderLayout.NORTH);
		paneMessage.add(spMessage,BorderLayout.CENTER);
		
		
		
		JPanel paneWrite = new JPanel();
		paneWrite.setLayout(new BorderLayout());
		paneWrite.add(paneTools,BorderLayout.NORTH);
		paneWrite.add(paneBtn,BorderLayout.SOUTH);
		
		JPanel paneCenter = new JPanel();
		paneCenter.setLayout(new BorderLayout());
		paneCenter.setBorder(new LineBorder(new Color(118,171,211)));
		paneCenter.setOpaque(true);
		paneCenter.setBackground(bgColor);
		paneCenter.add(paneMessage,BorderLayout.CENTER);
		paneCenter.add(paneWrite,BorderLayout.SOUTH);
		
		JPanel paneAll = new JPanel();
		paneAll.setLayout(new BorderLayout());
		paneAll.setOpaque(true);
		paneAll.setBackground(bgColor);
		paneAll.add(paneCenter,BorderLayout.CENTER);
		paneAll.add(paneBtn,BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(new FillWidth(6,407,bgColor),BorderLayout.WEST);

		add(paneAll,BorderLayout.CENTER);
		add(new FillWidth(494,5,bgColor),BorderLayout.SOUTH);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose){
			this.dispose();
			return;
		}
		
		}
	


}